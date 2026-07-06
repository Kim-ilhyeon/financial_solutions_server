package com.kh.mvc.controller;

import java.io.IOException;

import com.kh.mvc.model.member.MemberDAO;
import com.kh.mvc.model.member.MemberDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 회원 등록
 * 
 * insertForm.html에서 post로 회원가입을 요청함
 * -> 전달받은 파라미터를 정리함. (데이터를 여러 개이거나 DB에 있는거면 DTO로 담아주면 됨.)
 * -> DAO에게 저장을 위임
 * -> 처리가 끝나면 Redirect로 목록 화면으로 이동.
 * 
 * Redirect를 사용하는 이유 -> forward로 바로 결과화면을 보여주면 사용자가 새로고침 시 POST요청이 전송되면서 insert가 중복적으로 발생함
 * 						  Redirect를 사용하면 아예 다른 요청(화면목록을 보여주는)을 통해서 깔끔하게 화면목록을 보여줌.
 */
@WebServlet("/member/insert")
public class MemberInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberDAO memberDao = new MemberDAO();


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// getParameter()의 결과는 무조건 String 이므로 사용하는 값에 따라서 변환이 필요.
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		int age = Integer.parseInt(request.getParameter("age"));
		
		MemberDTO dto = new MemberDTO(name, email, age);
		
		memberDao.insert(dto);
		
		// request.getContextPath() : 프로젝트 경로를 가져옴
		response.sendRedirect(request.getContextPath() + "/member/list");
	}

}
