package com.ssafy.edu;

import java.util.Scanner;

public class StudentMgTest {
	//*****************************************************************//
	//***********주의 : StudentMgTest.java에서 수정 될 내용은 없다.**********//
	//*****************************************************************//
	static StudentMgr studentMgr = StudentMgrImpl.getInstance();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println();
			System.out.println("====교육생 관리 시스템=========");
			System.out.println("1.교육생 등록");
			System.out.println("2.교육생 목록 보기");
			System.out.println("3.교육생 학번으로 검색(4자리 숫자)");
			System.out.println("4.교육생 이름으로 검색");
			System.out.println("5.교육생 수정");
			System.out.println("6.교육생 삭제");
			System.out.println("0.종료");
			System.out.println("===========================");
			System.out.println("원하는 번호를 선택하세요.");
			int num = sc.nextInt();
			sc.nextLine();
			switch (num) {
				case 0: {
					System.out.println("====교육생 관리 시스템 종료=========");
					return;
				}
				case 1: {
					System.out.println("등록할 교육생 정보를 입력하세요");
					System.out.println("교육생 학번 : ");
					int studentNum = sc.nextInt();
					sc.nextLine();
					System.out.println("교육생 이름 : " );
					String name = sc.nextLine();
					System.out.println("교육 참여 기수(ex>1기, 2기,..,12기) : ");
					String number = sc.nextLine();
					System.out.println("교육생 나이 : ");
					int age = Integer.parseInt(sc.nextLine());
					System.out.println("참여 과정 : ");
					String major = sc.nextLine();
					
					boolean result = studentMgr.addStudent(new Student(studentNum, name, number, age, major));
					if(result) {
						System.out.println("등록이 완료되었습니다.");
					}else {
						System.out.println("이미 등록 된 학번입니다.");
					}
					break;
				}
				case 2: {
					System.out.println("====전체 교육생 목록==========");
					Student[] students=studentMgr.search();
					
					for(int i = 0; i<students.length;i++) {
						System.out.println("교육생 "+(i+1)+" "+students[i]);
					}
					break;
				}
				case 3: {
					System.out.println("====학번으로 교육생 검색==========");
					System.out.println("학번(4자리) : ");
					int studentNum = sc.nextInt();
					sc.nextLine();
					try {
						System.out.println("검색 결과 : "+studentMgr.search(studentNum));
					} catch (NotFoundException e) {
						e.printStackTrace();
					}
					break;
				}
				case 4: {
					System.out.println("====이름으로 교육생 검색==========");
					System.out.println("교육생 이름 : ");
					String name = sc.nextLine();
					Student[] students= studentMgr.search(name);
					System.out.println("검색 결과 : " + students.length+"명");
					for(Student s: students) {
						System.out.println(s);
					}
					break;
				}
				case 5: {
					System.out.println("====교육생 참여 과정 수정==========");
					System.out.println("교육생 학번을 입력해주세요. : ");
					int studentNum = sc.nextInt();
					sc.nextLine();
					System.out.println("변경하려는 과정명을 입력해주세요. : ");
					String major =  sc.nextLine();
					studentMgr.update(studentNum, major);
					break;
				}
				case 6: {
					System.out.println("====교육생 삭제==========");
					System.out.println("교육생 학번을 입력해주세요. : ");
					int studentNum = sc.nextInt();
					sc.nextLine();
					System.out.println("정말 삭제하시겠습니까? 1.예 2.아니오");
					int del = sc.nextInt();
					if(del == 1) {
						boolean res = studentMgr.delete(studentNum);
						if(res) {
							System.out.println("정상적으로 삭제되었습니다.");
						}else {
							System.out.println("삭제에 실패하였습니다.");
						}
					}else {
						System.out.println("삭제를 취소하였습니다.");
					}
					break;
				}
				default:{
					System.out.println("메뉴를 다시 선택해주세요.");
				}
				

			}
		}
	}

}
