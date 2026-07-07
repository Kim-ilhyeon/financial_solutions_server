package com.kh.jstl.model.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.jstl.util.DBUtil;

/*
 * [Model - DAO] member 테이블에 대한 실제 SQL실행을 담당하는 객체
 * 
 * Controller 에서는 해당 클래스의 메소드만 호출할 뿐 그 안에 어떤 SQL문이 실행되는지는 몰라도 된다. (관심사 분리)
 */
public class MemberDAO {

	/*
	 * 모든 회원을 조회
	 */
	public List<MemberDTO> findAll () {
		List<MemberDTO> list = new ArrayList<>();
		
		String sql = "SELECT ID, NAME, EMAIL, AGE FROM MEMBER ORDER BY ID DESC";
		
		try(Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt =  conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();) {
			
			while(rs.next()) {
				MemberDTO dto = new MemberDTO(
									rs.getInt("id"), 
									rs.getString("name"), 
									rs.getString("email"), 
									rs.getInt("age")
								);
				list.add(dto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	/*
	 * 회원 추가 함수
	 */
	public void insert(MemberDTO dto) {
		String sql = "INSERT INTO MEMBER(NAME, EMAIL, AGE) VALUES (?, ?, ?)";
		
		try(Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt =  conn.prepareStatement(sql);) {
			
			pstmt.setString(1,  dto.getName());
			pstmt.setString(2,  dto.getEmail());
			pstmt.setInt(3,  dto.getAge());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 해당 회원 삭제 함수
	 */
	public void delete(int id) {
		String sql = "DELETE FROM MEMBER WHERE ID = ?";
		
		try(Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt =  conn.prepareStatement(sql);) {
			
			pstmt.setInt(1,  id);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
