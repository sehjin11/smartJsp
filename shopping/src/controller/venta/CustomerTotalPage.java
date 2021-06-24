package controller.venta;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.DAO.SalesDAO;
import model.DTO.CustomerTotalDTO;

public class CustomerTotalPage {
	public void customerTotal(HttpServletRequest request) {
		SalesDAO dao = new SalesDAO();
		List<CustomerTotalDTO> list = dao.customerTotal();
		request.setAttribute("list", list);
		String googleList="[['아이디/이름','총구매금액','총횟수','평균금액']";
		for(CustomerTotalDTO dto : list) {
			googleList+=",['"+dto.getMemId()+"/"+dto.getMemName()+"',"+dto.getSumPrice()+","+dto.getCount()+","+dto.getAvg()+"]";
		}
		googleList += "]";
		request.setAttribute("googleList", googleList);
		request.setAttribute("list", list);
	}
}
