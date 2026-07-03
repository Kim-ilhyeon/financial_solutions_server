package com.servlet.practice.responseType;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Forward방식 : 서버 내부에서 다른 자원으로 요청을 전달하는 방식
 * 
 * 서블릿에서 요청을 받아 로직을 수정한 뒤 화면에 응답은 forward를 통해서 jsp로 위임할 수 있음
 * 이 때, request setAttibute(key, value)로 값을 전달하면 jsp에서 그대로 사용이 가능하다.
 */
@WebServlet("/forwardTest")
public class ForwardTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// request객체에 데이터를 담을 수 있음. -> forward방식으로 다른 곳으로 이동 후에도 데이터가 유지됨.
		request.setAttribute("message", "forward로 전달된 데이터입니다.");
		
		// 서버 내부에서 result.jsp로 요청을 그대로 넘김 (URL 변화 없음)
		request.getRequestDispatcher("/result.jsp").forward(request, response);
	}


}
