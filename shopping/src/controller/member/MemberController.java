package controller.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.employee.EmployeeListPage;


public class MemberController extends HttpServlet implements Servlet {
	
//클라이언트로부터 페이지주소 받아와서 해당페이지로 보내기
	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());
	
		if(command.equals("/memAgree.mem")) {
			RequestDispatcher dispatcher=request.getRequestDispatcher("member/agree.jsp");
			dispatcher.forward(request, response);
		}
		else if(command.equals("/memRegist.mem")) {
			RequestDispatcher dispatcher=request.getRequestDispatcher("member/memberForm.jsp");
			dispatcher.forward(request, response);			
		}
		else if(command.equals("/memJoin.mem")) {
			MemberJoinPage action = new MemberJoinPage();
			action.memInsert(request);
			response.sendRedirect("main.sj");
		}
		else if(command.equals("/memList.mem")) {
			MemberListPage action = new MemberListPage();
			action.memList(request);
			RequestDispatcher dispatcher = request.getRequestDispatcher("member/memberList.jsp");
			dispatcher.forward(request, response);
			//include : 같은 페이지에 불러옴
			//forward : 새로운 페이지에 불러오지만 주소는 그대로
		}
		else if(command.equals("/memInfo.mem")) {
			MemberInfoPage action = new MemberInfoPage();
			action.memInfo(request);
			RequestDispatcher dispatcher = request.getRequestDispatcher("member/memberInfo.jsp");
			dispatcher.forward(request, response);
		}
		else if(command.equals("/memMod.mem")) {
			MemberInfoPage action = new MemberInfoPage();
			action.memInfo(request);
			RequestDispatcher dispatcher = request.getRequestDispatcher("member/memberModify.jsp");
			dispatcher.forward(request, response);
		}
		else if(command.equals("/memModifyOk.mem")) {
			MemberModifyPage action = new MemberModifyPage();
			action.memUpdate(request);
			response.sendRedirect("memList.mem");
		}
		else if(command.equals("/memDel.mem")) {
			MemberDeletePage action = new MemberDeletePage();
			action.memDel(request);
			response.sendRedirect("memList.mem");
		}
		else if(command.equals("/myPage.mem")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("member/memMyPage.jsp");
			dispatcher.forward(request, response);
		}
		else if(command.equals("/memDetail.mem")) {
			MemberDetailPage action = new MemberDetailPage();
			action.memberDetail(request);
			RequestDispatcher dispatcher = request.getRequestDispatcher("member/memDetail.jsp");
			dispatcher.forward(request, response);
		}
		else if(command.equals("/memSujung.mem")) {
			MemberDetailPage action = new MemberDetailPage();
			action.memberDetail(request);
			RequestDispatcher dispatcher = request.getRequestDispatcher("member/memSujung.jsp");
			dispatcher.forward(request, response);
		}
		else if(command.equals("/memSujunOk.mem")) {
			MemberUpdatePage action = new MemberUpdatePage();
			int i = action.memberUpdate(request);
			if(i == 1) {
				response.sendRedirect("memDetail.mem");
			}
			else if(i == 2) {
				response.sendRedirect("memSujung.mem");
			}
		}
		else if(command.equals("/memOut.mem")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("member/outPw.jsp");
			dispatcher.forward(request, response);
			
		}
		else if(command.equals("/memOutOk.mem")) {
			MemberOutPage action = new MemberOutPage();
			
			//암호 입력 맞는지 확인하여 보내기
			int i=action.memOut(request);
			if(i==1) {
				response.sendRedirect("main.sj");				
			}
			else {
				response.sendRedirect("memOut.mem");				
			}
		}
		else if(command.equals("/memPwChange.mem")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("member/pwChange.jsp");
			dispatcher.forward(request, response);
		}
		else if(command.equals("/pwChangeOk.mem")) {
			MemberPwConfirmPage action = new MemberPwConfirmPage();
			String path = action.pwConfirm(request);
			RequestDispatcher dispatcher = request.getRequestDispatcher(path);
			dispatcher.forward(request, response);				
		}
		else if(command.equals("/ChangePw.mem")) {
			MemberPwChangePage action = new MemberPwChangePage();
			int i=action.pwChange(request);
			if(i==1) {
				response.sendRedirect("myPage.mem");
			}else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("member/pwChange.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
}
