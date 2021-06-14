package controller.employee;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import model.DAO.EmployeeDAO;
import model.DTO.EmployeeDTO;

public class EmployeeModifyPage {
	public void empModify(HttpServletRequest request) throws SQLException {
		EmployeeDTO dto=new EmployeeDTO();
		dto.setEmployeeId(request.getParameter("employeeId"));
		dto.setEmail(request.getParameter("email"));
		dto.setEmpAddr(request.getParameter("empAddr"));
		dto.setJobId(request.getParameter("jobId"));
		dto.setOfficeNumber(request.getParameter("officeNumber"));
		dto.setPhNumber(request.getParameter("phNumber"));
		EmployeeDAO dao = new EmployeeDAO();
		dao.empUpdate(dto);
	}
}
