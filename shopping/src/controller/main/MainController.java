package controller.main;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.goods.GoodsListPage;

public class MainController extends HttpServlet implements Servlet{
	//메시지를 받을 때
	
	public void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		if(command.equals("/main.sj")) {
			GoodsListPage action = new GoodsListPage();
			action.goodsList(request);
			RequestDispatcher dispatcher = request.getRequestDispatcher("main/home.jsp");
			dispatcher.forward(request, response);
		}
		else if(command.equals("/login.sj")) {
			LoginPage action = new LoginPage();
			action.login(request);
			response.sendRedirect("main.sj");
		}else if(command.equals("/logout.sj")) {
			HttpSession session = request.getSession();
			session.invalidate();
			response.sendRedirect("main.sj");
		}
		
		
		
		
		
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(req, resp);
	}
}
