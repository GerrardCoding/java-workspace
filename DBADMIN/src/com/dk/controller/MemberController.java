package com.dk.controller;

import java.util.ArrayList;

import com.dk.model.service.MemberService;
import com.dk.model.vo.Member;
import com.dk.view.MainMenu;

public class MemberController {
	/*
	 * 1. 회원 추가 요청을 처리하는 메서드
	 */
	public void insertMember(String productId,String pName,int price,
			                 String description,int stock) {
		
		Member m = new Member(productId, pName, price, description, stock);
		
		int result = new MemberService().insertMember(m);
		
		if(result > 0) { //성공
			new MainMenu().displaySuccess("성공적으로 회원 추가되었습니다.");
		}else {          //실패
			new MainMenu().displayFail("회원 추가에 실패했습니다.");
		}
	}
	/*
	 * 2. 회원전체를 조회요청을 처리하는 메서드
	 */
	public void selectList() {
		ArrayList<Member> list = new MemberService().selectList();
		
		if(list.isEmpty()) { // 리스트가 비어있을 경우 => 조회된 결과 없음.
			new MainMenu().displayNoData("조회 결과 데이터가 없습니다.");
		}else {
			new MainMenu().displayMemberList(list);
		}
		
	}
	
	// 3. 회원 아이디로 검색 요청을 처리하는 메서드
	// @param : userId : 사용자가 입력해서 검색하고자 하는 회원 아이디값
	public void selectById(String productId) {
		Member m = new MemberService().selectByUserId(productId);
		
		if(m == null) {
			new MainMenu().displayNoData(productId + "에 대한 조회결과가 없습니다.");
		}else {
			new MainMenu().displayMember(m);
		}
	}
	
	
	// 회원 정보 변경 요청을 처리할 메서드 
	// @param : userId, userPwd, email, phone, address
	public void updateMember(String productId,String pName,int price,String description,int stock) {
		Member m = new Member();
		m.setProductId(productId);
		m.setpName(pName);
		m.setPrice(price);
		m.setDescription(description);
		m.setStock(stock);
		
		int result = new MemberService().updateMember(m);
		
		if(result > 0 ) { //성공
			new MainMenu().displaySuccess("성공적으로 변경되었습니다.");
		}else { //실패
			new MainMenu().displayFail("회원정보 변경에 실패했습니다.");
		}
	}
	
	//회원 탈퇴 요청을 처리하는 메서드
	public void deleteMember(String productId) {
		int result = new MemberService().deleteMember(productId);
		
		if(result > 0) { //성공
			new MainMenu().displaySuccess("회원정보를 성공적으로 삭제했습니다.");
		}else {
			new MainMenu().displayFail("회원정보를 삭제하는데 실패했습니다.");
		}
	}
	
}