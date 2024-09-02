package com.ssafy.edu;

public interface StudentMgr {
	//*****************************************************************//
	//***********주의 : StudentMgr.java에서 수정 될 내용은 없다.*************//
	//*****************************************************************//
	boolean addStudent(Student s);
	Student[] search();
	Student search(int num) throws NotFoundException;
	Student[] search(String name);
	void update(int studentNum, String major);
	boolean delete(int studentNum);
	
}
