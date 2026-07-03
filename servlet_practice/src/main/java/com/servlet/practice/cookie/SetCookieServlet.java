package com.servlet.practice.cookie;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 쿠키 생성
 * 
 * 쿠키는 서버가 만들어서 클라이언트(브라우저)에게 전달하고, 브라우저는 이후 요청마다
 * 해당 쿠키를 자동으로 함께 실어서 서버로 보낸다. 즉 "데이터가 클라이언트 쪽에 저장" 된다.
 */
@WebServlet("/setCookie")
public class SetCookieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 쿠키 생성 : (key, value)형태
		Cookie cookie = new Cookie("username", "ilhyeon");
		cookie.setMaxAge(60 * 60);	// 쿠키의 유효 시간 (초), 60 * 60 = 1시간. MaxAge설정 안한 경우 브라우저 종료 시 삭제됨.
		cookie.setPath("/"); 	// 해당 경로 이하 모든 요청에서 쿠키가 함께 전송되도록 설정
		
		response.addCookie(cookie);
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("쿠키 저장 완료");
	}

}
