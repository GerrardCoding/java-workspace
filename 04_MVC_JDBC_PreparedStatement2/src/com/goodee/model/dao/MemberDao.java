package com.goodee.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.goodee.model.vo.Member;

public class MemberDao {
	
	//bookName, bookId, publisher, price
	
	public int insertMember(Member m) {
		
		int result = 0;
		
		Connection			conn = null;
		PreparedStatement	pstmt = null;
		
		String sql = "INSERT INTO BOOK VALUES(?,?,?,?)";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","JDBC","JDBC");
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getBookName()); //도서이름
			pstmt.setInt(2, m.getBookId());	 //도서번호
			pstmt.setString(3, m.getPublisher());//출판사
			pstmt.setInt(4, m.getPrice());		 //가격
			
			result = pstmt.executeUpdate();
			
			if(result > 0) {  // 성공
				conn.commit();
			}else {           // 실패
				conn.rollback();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	// 전체 회원을 조회하는 메서드
		public ArrayList<Member> selectList() {
			ArrayList<Member> list = new ArrayList<>();
			
			Connection conn = null;
			PreparedStatement  pstmt = null;
			ResultSet  rset = null;
			
			String sql = "SELECT * FROM BOOK";
		
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","JDBC","JDBC");
				pstmt = conn.prepareStatement(sql);
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					Member m = new Member();
					
					// rset에서 user_no컬럼을 읽어서 m객체의 setter(setUserNo)를 이용하여 입력
					m.setBookName(rset.getString("book_name"));  
					m.setBookId(rset.getInt("book_id"));
					m.setPublisher(rset.getString("user_pwd"));
					m.setPrice(rset.getInt("price"));
					
					list.add(m);
				}
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					rset.close();
					pstmt.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			
			return list;
		}
		
	// Controller에서 요청한 회원정보 삭제 작업을 수행할 메서드
		public int deleteMember(String bookName)  {
			int result = 0;
			
			Connection conn = null;
			PreparedStatement  pstmt = null;
			
			String sql = "DELETE FROM BOOK WHERE BOOKNAME = ?";
			
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","JDBC","JDBC");
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, bookName);
				result = pstmt.executeUpdate();
				
				if(result > 0) { //성공
					conn.commit();
				}else {
					conn.rollback();
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
					try {
						pstmt.close();
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
			}
			
			return result;
		}
		
}
