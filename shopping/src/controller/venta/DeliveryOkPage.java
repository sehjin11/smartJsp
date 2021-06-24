package controller.venta;

import javax.servlet.http.HttpServletRequest;

import model.DAO.SalesDAO;
import model.DTO.DeliveryDTO;

public class DeliveryOkPage {
	public void execute(HttpServletRequest request) {
		DeliveryDTO dto = new DeliveryDTO();
		dto.setArrivalExpDate(request.getParameter("arrivalExpDate"));
		dto.setDeliveryCom(request.getParameter("deliveryCom"));
		dto.setDeliveryExpDate(request.getParameter("deliveryExpDate"));
		dto.setDeliveryNum(request.getParameter("deliveryNum"));
		dto.setPurchaseNum(request.getParameter("purchaseNum"));
		SalesDAO dao = new SalesDAO();
		dao.deliveryCreate(dto);
	}
}
