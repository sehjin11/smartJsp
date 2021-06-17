package controller.employee;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.DAO.EmployeeDAO;
import model.DTO.AuthInfo;

public class EmployeeOutPage {

	public int empOut(HttpServletRequest request) {
		HttpSession session = request.getSession();
		AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
		if(request.getParameter("empPw").equals(authInfo.getUserPw())) {
			EmployeeDAO dao = new EmployeeDAO();
			dao.empDelete2(authInfo.getUserId());
			session.invalidate();
			return 1;
		}else {
			session.setAttribute("pwFail", "비밀번호가 틀렸습니다.");
			return 2;
		}
	}
}
