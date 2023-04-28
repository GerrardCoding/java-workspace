package com.dk.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.dk.common.JDBCTemplate;
import com.dk.dao.MemberDao;
import com.dk.model.vo.Member;

public class MemberService {

	public int insertMember(Member m) {
		// 1. JDBC 드라이버 등록
		// 2. Connection 객체 생성
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new MemberDao().insertMember(conn,m);
		
		// 트랜잭션 처리
		if(result > 0) { //성공
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	public ArrayList<Member> selectList(){
		Connection conn = JDBCTemplate.getConnection();
		
		//MemberDao클래스의 selectList메서드 호출. 호출시 conn 객체 전달.
		//selectList의 조회결과가 ArrayList로 반환됨.
		//반환된 list를 MemberController의 selectList 메서드로 반환
		ArrayList<Member> list = new MemberDao().selectList(conn);

		JDBCTemplate.close(conn);
		return list;
	}
	
	// 회원 아이디 검색 서비스 메서드
	public Member  selectByUserId(String productId) {
		Connection conn = JDBCTemplate.getConnection();
		
		Member m = new MemberDao().selectByUserId(conn,productId);
		JDBCTemplate.close(conn);
		return m;
	}
	
	// 회원정보 업데이트 서비스 메서드
	public int updateMember(Member m) {
		Connection conn = JDBCTemplate.getConnection();

		int result = new MemberDao().updateMember(conn,m);
		
		// 트랜잭션 처리
		if(result > 0) { //성공
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	// 회원 탈퇴 서비스 메서드
	public int deleteMember(String productId) {
		Connection conn = JDBCTemplate.getConnection();

		int result = new MemberDao().deleteMember(conn,productId);
		
		// 트랜잭션 처리
		if(result > 0) { //성공
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;		
	}
}
