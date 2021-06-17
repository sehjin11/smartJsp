package controller.goods;

import javax.servlet.http.HttpServletRequest;

import model.DAO.GoodsDAO;

public class GoodsNumberPage {
	public void seqNum(HttpServletRequest request) {
		GoodsDAO dao=new GoodsDAO();
		String goodsNum=dao.goodsNum();
		request.setAttribute("goodsNum", goodsNum);
	}
}
