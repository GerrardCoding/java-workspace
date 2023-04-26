package com.goodeel.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.goodee.controller.MemberController;
import com.goodee.model.vo.Member;

public class MainMenu {
	
	private Scanner sc = new Scanner(System.in);
	
	private MemberController mc = new MemberController();
	
	public void mainMenu() {
		
		while(true) {
			System.out.println("\n===도서 관리 프로그램===");
			System.out.println("1. 도서 추가");
			System.out.println("2. 전체 도서 조회");
			System.out.println("3. 도서 삭제(도서번호)");
			System.out.println("0. 프로그램 종료");
			
			System.out.println(">> 메뉴선택 : ");
			int menu = sc.nextInt();
			sc.nextLine();
			
			switch(menu) {
			case 1 : insertMember(); break; 				 //현재 클래스에서 처리
			case 2 : mc.selectList(); break;				 //Controll 패키지에 있는 클래스에 처리
			case 3 : mc.deleteMember(inputMemberId()); break; //Controll 패키지에 있는 클래스에 처리
			case 0 : System.out.println("이용해 주셔서 감사합니다."); return;
			default : System.out.println("메뉴를 잘못입력했습니다. 다시 입력해주세요.");
			}
		}
	}
	
	public void insertMember() {
		
		System.out.println("\n=== 도서 추가 ===");
		
		System.out.println("도서이름 : ");
		String bookName = sc.nextLine();
		
		System.out.println("도서번호 : ");
		int bookId = sc.nextInt();
		sc.nextLine();
		
		System.out.println("출판사 :");
		String publisher = sc.nextLine();
		
		System.out.println("도서가격");
		int price = sc.nextInt();
		
		mc.insertMember(bookName, bookId, publisher, price);
	}
	
	public String inputMemberId() {
		System.out.println("\n도서 번호 입력 : ");
		return sc.nextLine();
	}
	
	public String inputMemberName() {
		System.out.println("\n도서 이름 입력 : ");
		return sc.nextLine();
	}
	
	//---------------------응답화면-------------------------------
	// 서비스 요청 처리후 성공했을 때 사용자가 보게될 화면
	public void displaySuccess(String message) {
		System.out.println("\n서비스 요청 성공 : " + message);
	}
	
	// 서비스 요청 처리 실패했을때 사용자가 보게될 화면
	public void displayFail(String message) {
		System.out.println("\n서비스 요청 실패 : " + message);
	}
	
	// 조회 서비스 요청 처리 후 조회결과가 없을 경우 사용자가 보게 될 화면
	public void displayNoData(String message) {
		System.out.println("\n" + message);
	}
	
	// 조회 서비스 요청 처리 후 조회결과가 여러개일 경우 사용자가 보게될 화면
	public void displayMemberList(ArrayList<Member> list) {
		System.out.println("\n조회된 데이터는 다음과 같습니다.\n");
		
		for(Member m: list) {
			System.out.println(m);
		}
	}
	
	public void displayMember(Member m) {
		System.out.println("\n조회된 데이터는 다음과 같습니다.");
		System.out.println(m);
	}
}
