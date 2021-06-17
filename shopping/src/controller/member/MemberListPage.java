package controller.member;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.DAO.MemberDAO;
import model.DTO.MemberDTO;


public class MemberListPage {
	
	public void memList(HttpServletRequest request) {
		MemberDAO dao = new MemberDAO();
		List <MemberDTO> list = dao.getMemList();
		request.setAttribute("memList", list); //생성한 list를 저장
	}
}
