package com.jdbc.practice.crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.jdbc.practice.DBInfo;

public class MemberInsertTest {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("이름 : ");
		String name = sc.next();
		
		System.out.print("이메일 : ");
		String email = sc.next();
		
		System.out.print("나이 : ");
		int age = sc.nextInt();
		
		String sql = "INSERT INTO MEMBER(NAME, EMAIL, AGE) VALUES (?, ?, ?)";
		
		try (Connection conn = DriverManager.getConnection(DBInfo.URL, DBInfo.USER, DBInfo.PASSWORD)) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
		
			
			// 1번째 ?에 name값 넣기
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			pstmt.setInt(3, age);
			
			int result = pstmt.executeUpdate();	// 기본적으로 auto commit처리됨
			
			System.out.println("결과 : " + result + "행이 추가됨");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
