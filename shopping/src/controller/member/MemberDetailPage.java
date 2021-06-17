package controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.DAO.MemberDAO;
import model.DTO.AuthInfo;
import model.DTO.MemberDTO;

public class MemberDetailPage {
	
	public void memberDetail(HttpServletRequest request) {
		HttpSession session = request.getSession();
		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo"); //세션의 정보 가져오기(AuthInfo는 DTO)
		String memId = authInfo.getUserId(); //세션의 정보 중 아이디
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = dao.memDetail(memId); //해당아이디의 dto 가져오는 dao
		request.setAttribute("dto", dto); //request에 저장
	}
}
