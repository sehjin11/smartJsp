package model.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.DTO.ClientSaleDTO;
import model.DTO.CustomerTotalDTO;
import model.DTO.DeliveryDTO;

public class SalesDAO extends DataBaseInfo{
	
	public void deliveryCreate(DeliveryDTO dto) {
		String delFree=" select sum(prod_del_fee) from purchase_list pl, products pr " + 
				" where pl.prod_num=pr.prod_num and pl.purchase_num= ? ";
		sql="merge into delivery d" + 
				"   using (select purchase_num from purchase " + 
				"           where purchase_num = ? ) p " + 
				"   on (d.purchase_num = p.purchase_num) " + 
				"   When MATCHED THEN " + 
				"   update set DELIVERY_COM = ? ,DELIVERY_NUM=?, " + 
				"               DELIVERY_EXP_DATE = ?, ARRIVAL_EXP_DATE=?, " + 
				"               DELIVERY_DEL_FREE = (" + delFree + ") " + 
				"   When not MATCHED THEN " + 
				"   insert (DELIVERY_COM,DELIVERY_NUM, DELIVERY_EXP_DATE, " + 
				"           ARRIVAL_EXP_DATE,DELIVERY_DEL_FREE, " + 
				"         PURCHASE_NUM) " + 
				"   values(?,?,?,?,("+ delFree + "),?) ";
		System.out.println(sql);
		getConnect();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, dto.getPurchaseNum());
			pstmt.setString(2, dto.getDeliveryCom());
			pstmt.setString(3, dto.getDeliveryNum());
			pstmt.setString(4, dto.getDeliveryExpDate());
			pstmt.setString(5, dto.getArrivalExpDate());
			pstmt.setString(6, dto.getPurchaseNum());
			pstmt.setString(7, dto.getDeliveryCom());
			pstmt.setString(8, dto.getDeliveryNum());
			pstmt.setString(9, dto.getDeliveryExpDate());
			pstmt.setString(10, dto.getArrivalExpDate());
			pstmt.setString(11, dto.getPurchaseNum());
			pstmt.setString(12, dto.getPurchaseNum());
			int i = pstmt.executeUpdate();
			System.out.println(i+"개가 입력되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
	}
	
	public DeliveryDTO deliverySelect(String purchaseNum) {
		DeliveryDTO dto = null;
		sql = "select purchase_num, delivery_com, delivery_num, delivery_exp_date, arrival_exp_date, delivery_del_free from delivery where purchase_num=?";
		getConnect();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, purchaseNum);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				dto = new DeliveryDTO();
				dto.setArrivalExpDate(rs.getString(5));
				dto.setDeliveryCom(rs.getString(2));
				dto.setDeliveryDelFree(rs.getString(6));
				dto.setDeliveryExpDate(rs.getString(4));
				dto.setDeliveryNum(rs.getString(3));
				dto.setPurchaseNum(rs.getString(1));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return dto;
	}
	
	
	public List<CustomerTotalDTO> customerTotal(){
		List<CustomerTotalDTO> list = new ArrayList<CustomerTotalDTO>();
		sql="select m.mem_id, mem_name, sum(pu.purchase_tot_price), count(*) , avg(purchase_tot_price) " + 
				" from member m, purchase pu " + 
				" where m.mem_id=pu.mem_id " + 
				" group by m.mem_id, m.mem_name";
		getConnect();
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				CustomerTotalDTO dto = new CustomerTotalDTO();
				dto.setCount(rs.getString(4));
				dto.setMemId(rs.getString(1));
				dto.setMemName(rs.getString(2));
				dto.setSumPrice(rs.getString(3));
				dto.setAvg(rs.getString(5));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}
	
	public List<ClientSaleDTO> salesList(String memId) {
		List<ClientSaleDTO> list = new ArrayList<ClientSaleDTO>();
		
		sql="select m.mem_id, mem_name, mem_phone, prod_name, pr.prod_num, pu.purchase_num, " + 
				"        purchase_date, purchase_addr, receiver_name, receiver_phone, " + 
				"        purchase_qty, purchase_price, delivery_num " + 
				"        from member m, products pr, purchase pu, purchase_list pl, delivery d " + 
				"        where m.mem_id=pu.mem_id and pu.purchase_num=pl.purchase_num and pl.prod_num=pr.prod_num "
						+ " and pu.purchase_num = d.purchase_num(+)";
		if(memId!=null) {
			sql +=" and m.mem_id=?";
		}
		getConnect();
		try {
			pstmt=conn.prepareStatement(sql);
			if(memId!=null) {
				pstmt.setString(1, memId);
			}
			rs=pstmt.executeQuery();
			while(rs.next()) {
				ClientSaleDTO dto = new ClientSaleDTO();
				dto.setMemId(rs.getString("mem_id"));
				dto.setMemName(rs.getString("mem_name"));
				dto.setMemPhone(rs.getString("mem_phone"));
				dto.setProdNum(rs.getString("prod_num"));
				dto.setProdName(rs.getString("prod_name"));
				dto.setPurchaseAddr(rs.getString("purchase_addr"));
				dto.setPurchaseDate(rs.getDate("purchase_date"));
				dto.setPurchaseNum(rs.getString("purchase_num"));
				dto.setPurchasePrice(rs.getString("purchase_price"));
				dto.setPurchaseQty(rs.getString("purchase_qty"));
				dto.setReceiverName(rs.getString("receiver_name"));
				dto.setReceiverPhone(rs.getString("receiver_phone"));
				dto.setDeliveryNum(rs.getString("delivery_num"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {close();}
		return list;
	}
}
