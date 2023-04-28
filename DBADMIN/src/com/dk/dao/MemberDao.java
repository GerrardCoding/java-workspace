package com.dk.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.dk.common.JDBCTemplate;
import com.dk.model.vo.Member;

public class MemberDao {
private Properties prop = new Properties();
	
	//사용자가 어떤 서비스를 요청할 때마다 매번 new MemberDao().xxx();
	//즉, 서비스를 요청할 때마다 매번 다음의 기본 생성자가 실행됨.
	
	public MemberDao() {
		
		try {
			prop.loadFromXML(new FileInputStream("resources/sql.xml"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public int insertMember(Connection conn,Member m) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertMember");		
		
		try {
			pstmt = conn.prepareStatement(sql);

			//pstmt.setString(물음표순서,대체할값)  => '대체할값' (양옆에 홑따옴표로 감싸준 데이터가 들어감)
			//pstmt.setInt(물음표순서,대체할값)     => 홑따옴표없이 데이터가 들어감
			pstmt.setString(1, m.getProductId());
			pstmt.setString(2, m.getpName());
			pstmt.setInt(3, m.getPrice());
			pstmt.setString(4, m.getDescription());
			pstmt.setInt(5, m.getStock());
			
			result = pstmt.executeUpdate();			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
				JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	// 전체 회원을 조회하는 메서드
	public ArrayList<Member> selectList(Connection conn) {
		ArrayList<Member> list = new ArrayList<>();
		
		PreparedStatement  pstmt = null;
		ResultSet  rset = null;
		
		String sql = prop.getProperty("selectList");
	
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Member m = new Member();
				
				// rset에서 user_no컬럼을 읽어서 m객체의 setter(setUserNo)를 이용하여 입력
				m.setProductId(rset.getString("product_id"));
				m.setpName(rset.getString("p_name"));
				m.setPrice(rset.getInt("price"));
				m.setDescription(rset.getString("description"));
				m.setStock(rset.getInt("stock"));
				
				list.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	// 회원 아이디로 검색 요청시 조회하는 메서드
	public Member selectByUserId(Connection conn,String productId) {
		Member m = null;
		
		PreparedStatement  pstmt = null;
		ResultSet  rset = null;
		
		String sql = "SELECT * FROM PRODUCT WHERE PRODUCT_ID = ?";
	
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, productId);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				m = new Member();
				
				// rset에서 user_no컬럼을 읽어서 m객체의 setter(setUserNo)를 이용하여 입력
				m.setProductId(rset.getString("product_id"));
				m.setpName(rset.getString("p_name"));
				m.setPrice(rset.getInt("price"));
				m.setDescription(rset.getString("description"));
				m.setStock(rset.getInt("stock"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return m;
	}	
	
	// Service에서 요청하는 회원정보 변경을 수행하는 메서드
	public int updateMember(Connection conn,Member m) {
		int result = 0;

		PreparedStatement  pstmt = null;
		
		String sql = "SELECT * FROM PRODUCT WHERE PRODUCT_ID = ?,"
				+       "    P_NAME = ?,"
				+       "    PRICE = ?,"
				+       "    DESCRIPTION = ?"
				+		"    STOCK = ?"
				+     "WHERE PRODUCT_ID = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getProductId());
			pstmt.setString(2, m.getpName());
			pstmt.setInt(3, m.getPrice());
			pstmt.setString(4, m.getDescription());
			pstmt.setInt(5, m.getStock());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	
	// MemberService에서 요청한 회원정보 삭제 작업을 수행할 메서드
	public int deleteMember(Connection conn,String productId)  {
		int result = 0;
		
		PreparedStatement  pstmt = null;
		
		String sql = "DELETE FROM PRODUCT WHERE USER_ID = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, productId);
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);;
		}
		
		return result;
	}
	
}