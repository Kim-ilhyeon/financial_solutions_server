package com.jdbc.practice.crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.jdbc.practice.DBInfo;

public class MemberUpdateTest {

	public static void main(String[] args) {
		// 변경할 member의 id를 입력 받고, 변경할 email을 입력 받아서
		// 입력받은 id를 가진 member의 email을 입력받은 email로 변경
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("변경할 id : ");
		int id = sc.nextInt();
		System.out.print("새로운 이메일 : ");
		String newEmail = sc.next();
		
		String sql = "UPDATE MEMBER SET EMAIL = ? WHERE ID = ?";
		
		try (Connection conn = DriverManager.getConnection(DBInfo.URL, DBInfo.USER, DBInfo.PASSWORD)) {
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, newEmail);
			pstmt.setInt(2, id);
			
			int result = pstmt.executeUpdate();
			
			System.out.println("이메일이 " + newEmail + "로 변경됨");
			System.out.println("결과 : " + result + "행이 수정됨");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
