package com.jdbc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jdbc.practice.DBInfo;

/*
 * PreparedStatement
 * Statement와 동일한 역할을 하는 객체로, 사용방식을 조금 변경한 형태이다.
 */
public class PreparedStatementTest {

	public static void main(String[] args) {
		// searchName은 사용자로부터 입력받은 값이라고 가정
		String searchName = "최지원";
		// String searchName = "' or '1' = '1";	// 이렇게 하는 경우 true가 되어 모든 값을 다 가져오는 해킹의 위험이 있음
		// PreparedStatement의 동일하게 searchName(' or '1' = '1)을 넣어도 이름이 정확하게 문자열인 회원을 찾을 뿐 전체조회로 이어지지 않는다.
		
		// sql구성 시 변수로 값을 대체할 자리에 ?를 넣어줌
		String sql = "SELECT * FROM MEMBER WHERE NAME = ?";
		
		// 데이터 베이스 연결 객체 생성
		try (Connection conn = DriverManager.getConnection(DBInfo.URL, DBInfo.USER, DBInfo.PASSWORD)) {
			
			// 기본적으로 auto commit상태이므로 혹시 트랜잭션을 활용하고 싶다면
			// conn.setAutoCommit(false);	// auto commit을 사용하지 않겠다.
			// conn.commit();
			// conn.rollback();
			// 를 통해서 트랜잭션을 제어할 수 있다.
			
			// sql을 전달해서 값을 받아오기 위한 객체 -> 미완성 sql문 전달
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			// sql의 ?부분을 완성
			pstmt.setString(1, searchName);
			
			// Statement객체를 통해서 sql을 전달할 때 insert, update, delete를 하는 경우 -> .executeUpdate();
			// . . . select하는 경우 -> .executeQuery(); / 조회결과 (ResultSet)
			ResultSet rset = pstmt.executeQuery();

			// ResultSet에서 객체의 값을 가져오는 방식은 커서방식으로
			// next();가 실행될 때마다 커서를 한 칸 아래로 내리면서 가져올 데이터가 있는지를 boolean으로 반환해준다.
			int count = 0;
			while (rset.next()) {
				count++;
				System.out.println(rset.getInt("id") + ", " + rset.getString("name") + ", " + rset.getString("email"));
			}
			System.out.println("조회된 회원 수 : " + count);
			
			System.out.println(rset.next());
			System.out.println(rset.next());
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

	}

}
