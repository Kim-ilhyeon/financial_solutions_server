package com.jdbc.practice.crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.jdbc.practice.DBInfo;

public class MemberDeleteTest {

	public static void main(String[] args) {
		// member의 id을 입력받아 입력받은 id의 데이터를 member테이블에서 삭제해라.
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("삭제할 id(숫자) : ");
		int id = sc.nextInt();
		
		String sql = "DELETE FROM MEMBER WHERE ID = ?";
		
		try (Connection conn = DriverManager.getConnection(DBInfo.URL, DBInfo.USER, DBInfo.PASSWORD)) {
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			int result = pstmt.executeUpdate();	// 기본적으로 자동 커밋됨
			
			System.out.println("결과 : " + result + "행이 제거됨");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

}
