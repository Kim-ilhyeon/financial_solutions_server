package com.kh.mvc.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.kh.mvc.model.member.MemberDAO;

/**
 * list.jsp로부터 삭제링크로 url?id=숫자 GET요청으로 ID전달함
 * -> id를 가져와서 삭제 후 member/list화면으로 전달
 */
@WebServlet("/member/delete")
public class MemberDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberDAO memberDao = new MemberDAO();
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		memberDao.delete(id);
		
		// request.getContextPath() : 프로젝트 경로를 가져옴
		response.sendRedirect(request.getContextPath() + "/member/list");
	}


}
