package com.servlet.practice;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @단어 : 어노테이션 - 컴파일러에게 전달하는 주석으로, 어노테이션마다 컴파일러가 특정 기능을 수행해 줌
 * 
 * 기본적인 Servlet 구성
 * - @WebServlet(url) : 이 서블릿이 어떤 URL요청을 처리할 것인가 매핑
 * ex) @WebServlet("/hello") -> 브라우저에서 http://localhost:8080/프로젝트path/hello로 접속 시 이 서블릿이 실행됨
 * - HttpServlet을 상속받아야 서블릿 컨테이너가 이 클래스를 서블릿으로 인식.
 */
@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public HelloServlet() {
        super();
    }


    // get방식 요청이 왔을 경우에 실행되는 메소드
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * HttpServletRequest : 요청 시에 전달된 내용들이 담겨있는 객체 (사용자가 입력한 값, 요청 방식, 요청IP, url. . .등에 대한 정보가 들어있음)
		 * HttpServletResponse : 요청에 대한 처리 후 응답할 때 사용하는 객체 (어떤 타입으로 응답할지, 어떤 값을 응답할지. . .등에 대한 정보가 들어있음)
		 * */
		
		// 응답의 컨텐츠 타입
		// 이 설정을 하지 않으면 무조건 텍스트로 응답이 됨.
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		out.println("<h1>Hello, Servlet!</h1>");
		out.println("hello요청에 대한 doGet()메소드의 응답 화면입니다.");
	}


	// post방식 요청이 왔을 경우에 실행되는 메소드
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}


	/*
	 * 
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.service(req, resp);
	}
	*/

}
