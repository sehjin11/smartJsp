package model.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.DTO.CartDTO;
import model.DTO.OrderListDTO;
import model.DTO.PaymentDTO;
import model.DTO.ProdReviewDTO;
import model.DTO.ProductCartDTO;
import model.DTO.ProductDTO;
import model.DTO.ProductReviewDTO;
import model.DTO.PurchaseDTO;

public class GoodsDAO extends DataBaseInfo{
	final String COLUMNS ="PROD_NUM, PROD_NAME, PROD_PRICE, PROD_IMAGE, PROD_DETAIL, PROD_CAPACITY, PRUD_SUPPLYER, PROD_DEL_FEE, RECOMMEND, EMPLOYEE_ID, CTGR"; 	
	
	
	public List <ProdReviewDTO> prodReveiwSelect(String prodNum) {
		List <ProdReviewDTO> list = new ArrayList<ProdReviewDTO>();
		sql = "select rpad(substr(p.mem_id,1,3), length(p.mem_id), '*') mem_id, review_content, review_img, review_date " + 
				"from purchase p, review r " + 
				"where p.purchase_num=r.purchase_num " + 
				"and r.prod_num=?";
		getConnect();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, prodNum);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				ProdReviewDTO dto = new ProdReviewDTO();
				dto.setMemId(rs.getString("mem_id"));
				dto.setReviewContent(rs.getString("review_content"));
				dto.setReviewDate(rs.getDate("review_date"));
				dto.setReviewImg(rs.getString("review_img"));
				list.add(dto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}
	
	
	public void reviewUpdate(ProductReviewDTO dto) {
		sql = " update review "
			+ " set REVIEW_CONTENT = ?"
			+ " where PURCHASE_NUM = ? and PROD_NUM = ?";
		getConnect();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getReviewContent());
			pstmt.setString(2, dto.getPurchaseNum());
			pstmt.setString(3, dto.getProdNum());
			int i = pstmt.executeUpdate();
			System.out.println(i + "개가 수정되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		
	}
	
	public void reviewSelect(ProductReviewDTO dto) {
		sql = " select PURCHASE_NUM, PROD_NUM, REVIEW_DATE, "
			+ " REVIEW_CONTENT, REVIEW_IMG "
			+ " from review "
			+ " where PURCHASE_NUM = ? and PROD_NUM = ?";
		getConnect();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getPurchaseNum());
			pstmt.setString(2, dto.getProdNum());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto.setProdNum(rs.getString("prod_Num"));
				dto.setPurchaseNum(rs.getString("purchase_Num"));
				dto.setReviewContent(rs.getString("review_Content"));
				dto.setReviewDate(rs.getString("review_Date"));
				dto.setReviewImg(rs.getString("review_Img"));
				System.out.println(rs.getString("review_Content"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
	}
	
	public void reviewInsert(ProductReviewDTO dto) {
		sql = "insert into review(PURCHASE_NUM, PROD_NUM,REVIEW_DATE,"
				+ " REVIEW_CONTENT, REVIEW_IMG ) "
			+ " values(?,?,sysdate,?,?)";
		getConnect();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getPurchaseNum());
			pstmt.setString(2, dto.getProdNum());
			pstmt.setString(3, dto.getReviewContent());
			pstmt.setString(4, dto.getReviewImg());
			int i = pstmt.executeUpdate();
			System.out.println(i + "개가 등록되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
	}
	
	public void payment(PaymentDTO dto) {
		String num = "select to_char(sysdate,'yyyymmdd') || nvl2(max(PAYMENT_APPR_NUM), substr(max(PAYMENT_APPR_NUM),-6),100000) + 1 from payment where substr(PAYMENT_APPR_NUM, 1, 8)= to_char(sysdate,'yyyymmdd')";
		sql="insert into payment (PURCHASE_NUM,MEM_ID,PAYMENT_METHOD,PAYMENT_APPR_PRICE,PAYMENT_APPR_NUM,PAYMENT_APPR_DATE, PAYMENT_NUMBER ) values (?, ?, ?, ?, ( "+ num +" ), sysdate, ?  )";
		getConnect();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getPurchaseNum());
			pstmt.setString(2, dto.getMemId());
			pstmt.setString(3, dto.getPaymentMethod());
			pstmt.setString(4, dto.getPaymentApprPrice());
			pstmt.setString(5, dto.getPaymentNumber());
			int i = pstmt.executeUpdate();
			System.out.println(i + "개가 저장되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
	}
	
	
	public List<OrderListDTO> orderList(String memId) {
		List<OrderListDTO> list = new ArrayList<OrderListDTO>();
		sql ="select p2.PURCHASE_DATE, p4.PAYMENT_APPR_NUM , p1.prod_num, " + 
				"       p2.PURCHASE_NUM, p1.prod_name, p1.PRUD_SUPPLYER, " + 
				"       p2.PURCHASE_TOT_PRICE, p1.prod_image ,review_content " + 
				"from products p1, purchase p2, purchase_list p3, payment p4, review r " + 
				"where p3.prod_num = p1.prod_num " + 
				"  and p3.PURCHASE_NUM = p2.PURCHASE_NUM " + 
				"  and p3.PURCHASE_NUM = r.PURCHASE_NUM(+) " + 
				"  and p3.prod_num = r.prod_num(+) " + 
				"  and p2.PURCHASE_NUM = p4.PURCHASE_NUM(+) " + 
				"  and p2.mem_id = ? " + 
				" order by PURCHASE_NUM desc";
		getConnect();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				OrderListDTO dto = new OrderListDTO();
				dto.setPaymentApprNum(rs.getString("payment_appr_num"));
				dto.setProdImage(rs.getString("prod_image"));
				dto.setProdName(rs.getString("prod_name"));
				dto.setProdNum(rs.getString("prod_num"));
				dto.setProdSupplyer(rs.getString("prud_supplyer"));
				dto.setPurchaseDate(rs.getString("purchase_date"));
				dto.setPurchaseNum(rs.getString("purchase_num"));
				dto.setPurchaseTotPrice(rs.getString("purchase_tot_price"));
				dto.setReviewContent(rs.getString("review_Content"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {close();}
		return list;
	}
	
	public void CartDel(String prodNum, String memId) {
		sql = "delete from cart where mem_id=? and prod_num=?";
		getConnect();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			pstmt.setString(2, prodNum);
			int i = pstmt.executeUpdate();
			System.out.println(i+"개가 삭제되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}  finally {close();}
		
	}
	
	
	public void purchaseListInsert(String purchaseNum, String prodNum, String memId) {
		sql = "insert into purchase_list (PURCHASE_NUM, PROD_NUM, PURCHASE_QTY, PURCHASE_PRICE) "
			+ "SELECT ?, PROD_NUM, CART_QTY, CART_PRICE  from cart where PROD_NUM=? AND mem_id = ?";
		getConnect();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, purchaseNum);
			pstmt.setString(2, prodNum);
			pstmt.setString(3, memId);
			int i = pstmt.executeUpdate();
			System.out.println(i+"개가 저장되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {close();}
		
	}
	
	public void purchaseInsert(PurchaseDTO dto) {
		sql = "insert into purchase (PURCHASE_NUM, MEM_ID, PURCHASE_TOT_PRICE, PURCHASE_ADDR, PURCHASE_METHOD, PURCAHSE_REQUEST, RECEIVER_NAME, RECEIVER_PHONE, PURCHASE_DATE) values(?,?,?,?,?,?,?,?,sysdate)";
		getConnect();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, dto.getPurchaseNum());
			pstmt.setString(2, dto.getMemId());
			pstmt.setString(3, dto.getPurchaseTotPrice());
			pstmt.setString(4, dto.getPurchaseAddr());
			pstmt.setString(5, dto.getPurchaseMethod());
			pstmt.setString(6, dto.getPurchseRequest());
			pstmt.setString(7, dto.getReceiverName());
			pstmt.setString(8, dto.getReceiverPhone());
			int i = pstmt.executeUpdate();
			System.out.println(i+"개가 저장되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {close();}
		
	}
	
	
	public ProductCartDTO prodCart(String prodNum, String memId) {
		ProductCartDTO dto=null;
		sql="select p.PROD_NUM, PROD_NAME, PROD_PRICE, PRUD_SUPPLYER, PROD_DEL_FEE, PROD_IMAGE, MEM_ID, CART_QTY, CART_PRICE from products p, cart c where p.Prod_num=c.prod_num and mem_id=? and c.prod_Num=?";
		getConnect();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			pstmt.setString(2, prodNum);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				dto = new ProductCartDTO();
				dto.setCartDTO(new CartDTO());
				dto.setProductDTO(new ProductDTO());
				dto.getProductDTO().setProdNum(rs.getString("prod_num"));
				dto.getCartDTO().setCartPrice(rs.getString("CART_PRICE"));
				dto.getCartDTO().setCartQty(rs.getString("cart_qty"));
				dto.getProductDTO().setProdDelFee(rs.getString("prod_del_fee"));
				dto.getProductDTO().setProdImage(rs.getString("prod_image"));
				dto.getProductDTO().setProdName(rs.getString("prod_name"));
				dto.getProductDTO().setProdPrice(rs.getInt("prod_price"));
				dto.getProductDTO().setProdSupplyer(rs.getString("prud_supplyer"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {close();}
		
		return dto;
	}
	
	public void CartProdDel(CartDTO dto) {
		sql = "delete from cart where MEM_ID=? and PROD_NUM =?";
		getConnect();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, dto.getMemId());
			pstmt.setString(2, dto.getProdNum());
			int i = pstmt.executeUpdate();
			System.out.println(i+"개가 삭제되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {close();}
	}
	
	public void cartQtyDown(CartDTO dto) {
		sql="update cart set CART_QTY=CART_QTY-?, CART_PRICE=CART_PRICE-? where MEM_ID=? and PROD_NUM=?";
		getConnect();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, 1);
			pstmt.setString(2, dto.getCartPrice());
			pstmt.setString(3, dto.getMemId());
			pstmt.setString(4, dto.getProdNum());
			int i = pstmt.executeUpdate();
			System.out.println(i+"개가 수정되었습니다.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {close();}
		
	}
	
	
	
	
	public List<ProductCartDTO> cartList(String memId) {
		List<ProductCartDTO> list = new ArrayList<ProductCartDTO>();
		sql="select prud_supplyer, p.prod_num, prod_del_fee, prod_image, prod_name, prod_price, cart_price, cart_qty " + 
				" from products p, cart c " + 
				"where p.prod_num=c.prod_num and c.mem_id=?";
		getConnect();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				ProductCartDTO dto = new ProductCartDTO();
				dto.setCartDTO(new CartDTO());
				dto.setProductDTO(new ProductDTO());
				dto.getProductDTO().setProdNum(rs.getString("prod_num"));
				dto.getCartDTO().setCartPrice(rs.getString("CART_PRICE"));
				dto.getCartDTO().setCartQty(rs.getString("cart_qty"));
				dto.getProductDTO().setProdDelFee(rs.getString("prod_del_fee"));
				dto.getProductDTO().setProdImage(rs.getString("prod_image"));
				dto.getProductDTO().setProdName(rs.getString("prod_name"));
				dto.getProductDTO().setProdPrice(rs.getInt("prod_price"));
				dto.getProductDTO().setProdSupplyer(rs.getString("prud_supplyer"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {close();}
		
		return list;
	}
	
	public void cartInsert(CartDTO dto) {
		sql=" merge into cart c "
				+ " using (select prod_num "
				+ "        from products where prod_num = ?) p "
				+ " on (c.prod_num = p.prod_num and c.mem_id = ? ) "
				+ " When MATCHED THEN "
				+ " 	update set CART_QTY = CART_QTY + ? ,"
				+ "                CART_PRICE = CART_PRICE + ? "
				+ " When not MATCHED THEN  "
				+ " insert (c.MEM_ID,c.PROD_NUM,c.CART_QTY,"
										+ "c.CART_PRICE) values(?,?,?,?)";
				
				
//				" merge into cart c using (select prod_num from products where prod_num=?) p "
//				+ " on (c.prod_num=p.prod_num and c.mem_id = ? ) when matched then "
//				+ " update set cart_qty = cart_qty+?, cart_price=cart_price+? "
//				+ " when not matched then insert (c.MEM_ID, c.PROD_NUM, c.CART_QTY, c.CART_PRICE) values(?,?,?,?)";
		getConnect();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, dto.getProdNum());
			pstmt.setString(2, dto.getMemId());
			pstmt.setString(3, dto.getCartQty());
			pstmt.setString(4, dto.getCartPrice());
			pstmt.setString(5, dto.getMemId());
			pstmt.setString(6, dto.getProdNum());
			pstmt.setString(7, dto.getCartQty());
			pstmt.setString(8, dto.getCartPrice());
			int i = pstmt.executeUpdate();
			System.out.println(i+"개가 저장되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {close();}
	}
	
	public void prodDel(String prodNum) {
		sql="delete from products where prod_num=?";
		getConnect();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, prodNum);
			int i = pstmt.executeUpdate();
			System.out.println(i+"개가 삭제되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {close();}	
	}
	
	
	public void goodsUpdate(ProductDTO dto) {
		sql = "update products set prod_name=?, prod_price=?, prod_detail=?, prod_capacity=?, prud_supplyer=?, prod_del_fee=?, recommend=? where prod_num=?";
		getConnect();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, dto.getProdName());
			pstmt.setInt(2, dto.getProdPrice());
			pstmt.setString(3, dto.getProdDetail());
			pstmt.setString(4, dto.getProdCapacity());
			pstmt.setString(5, dto.getProdSupplyer());
			pstmt.setString(6, dto.getProdDelFee());
			pstmt.setString(7, dto.getRecommend());
			pstmt.setString(8, dto.getProdNum());
			int i = pstmt.executeUpdate();
			System.out.println(i+"개가 수정되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {close();}
		
	}
	
	public ProductDTO GoodsOne(String prodNum) {
		ProductDTO dto = null;
		sql="select "+COLUMNS+", case CTGR when 'wear' then '의류' "
				+ "						  when 'cosmetic' then '화장품'"
				+ "						  when 'food' then '식품'"
				+ "						  when 'car' then '자동차용품' "
				+ "						  end CTGR1 from products where PROD_NUM = ?";
		getConnect();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, prodNum);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				dto = new ProductDTO();
				dto.setCtgr(rs.getString("CTGR1"));
				dto.setEmployeeId(rs.getString("employee_id"));
				dto.setProdCapacity(rs.getString("prod_Capacity"));
				dto.setProdDelFee(rs.getString("prod_Del_Fee"));
				dto.setProdDetail(rs.getString("prod_Detail"));
				dto.setProdImage(rs.getString("prod_Image"));
				dto.setProdName(rs.getString("prod_name"));
				dto.setProdNum(rs.getString("prod_num"));
				dto.setProdPrice(rs.getInt("prod_Price"));
				dto.setProdSupplyer(rs.getString("PRUD_SUPPLYER"));
				dto.setRecommend(rs.getString("recommend"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {close();}
		return dto;
	}
	
	
	public String goodsNum() {
		String prodNum=null;
		sql="select prod_seq.nextval from dual";
		getConnect();
		try {
			pstmt = conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			rs.next();
			prodNum=rs.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { close(); }
		return prodNum;
	}
	
	public void prodInsert(ProductDTO dto) {
		sql = "insert into products ("+COLUMNS+") values(?,?,?,?,?,?,?,?,?,?,?)";
		getConnect();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, dto.getProdNum());
			pstmt.setString(2, dto.getProdName());
			pstmt.setInt(3, dto.getProdPrice());
			pstmt.setString(4, dto.getProdImage());
			pstmt.setString(5, dto.getProdDetail());
			pstmt.setString(6, dto.getProdCapacity());
			pstmt.setString(7, dto.getProdSupplyer());
			pstmt.setString(8, dto.getProdDelFee());
			pstmt.setString(9, dto.getRecommend());
			pstmt.setString(10, dto.getEmployeeId());
			pstmt.setString(11, dto.getCtgr());
			int i = pstmt.executeUpdate();
			System.out.println(i+"개가 입력되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {close();}
		
	}
	
	public List<ProductDTO> goodsList() {
		List<ProductDTO> list = new ArrayList<ProductDTO>();
		sql="select "+COLUMNS+", case CTGR when 'wear' then '의류' "
				+ "						  when 'cosmetic' then '화장품'"
				+ "						  when 'food' then '식품'"
				+ "						  when 'car' then '자동차용품' "
				+ "						  end CTGR1 from products";
		getConnect();
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				ProductDTO dto = new ProductDTO();
				dto.setCtgr(rs.getString("CTGR1"));
				dto.setEmployeeId(rs.getString("employee_id"));
				dto.setProdCapacity(rs.getString("prod_Capacity"));
				dto.setProdDelFee(rs.getString("prod_Del_Fee"));
				dto.setProdDetail(rs.getString("prod_Detail"));
				dto.setProdImage(rs.getString("prod_Image"));
				dto.setProdName(rs.getString("prod_name"));
				dto.setProdNum(rs.getString("prod_num"));
				dto.setProdPrice(rs.getInt("prod_Price"));
				dto.setProdSupplyer(rs.getString("PRUD_SUPPLYER"));
				dto.setRecommend(rs.getString("recommend"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { close(); }
		return list;
	}
}
