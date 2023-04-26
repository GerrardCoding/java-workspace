package com.goodee.controller;

import java.util.ArrayList;

import com.goodee.model.dao.MemberDao;
import com.goodee.model.vo.Member;
import com.goodeel.view.MainMenu;

public class MemberController {
	
	//bookName, bookId, publisher, price
	
	public void insertMember(String bookName, int bookId, String publisher, int price) {
		
		Member m = new Member(bookName,bookId,publisher,price);
		
		int result = new MemberDao().insertMember(m);
		
		if(result > 0) { //성공
			new MainMenu().displaySuccess("성공적으로 도서 추가되었습니다.");
		} else {
			new MainMenu().displayFail("회원 추가에 실패했습니다.");
		}
	}
	/*
	 * 회원전체를 조회요청을 처리하는 메서드
	 */
	public void selectList() {
		ArrayList<Member> list = new MemberDao().selectList();
		
		if(list.isEmpty()) { //리스트가 비어있을 경우 => 조회된 결과 없음.
			new MainMenu().displayNoData("조회 결과 데이터가 없습니다.");
		} else {
			new MainMenu().displayMemberList(list);
		}
	}
	
	public void deleteMember(String bookId) {
		int result = new MemberDao().deleteMember(bookId);
		
		if(result > 0) { //성공
			new MainMenu().displaySuccess("회원정보를 성공적으로 삭제했습니다.");
		}else {
			new MainMenu().displayFail("회원정보를 삭제하는데 실패했습니다.");
		}
	}
}
