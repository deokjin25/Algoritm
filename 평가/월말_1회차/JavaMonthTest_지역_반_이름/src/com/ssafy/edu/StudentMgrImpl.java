package com.ssafy.edu;

import java.util.Arrays;

public class StudentMgrImpl implements StudentMgr {
	private StudentMgrImpl() {
		this.addStudent(new Student(1111, "김싸피", "12기", 27, "자바전공"));
		this.addStudent(new Student(1112, "박싸피", "12기", 26, "비전공 파이썬"));
		this.addStudent(new Student(1113, "홍싸피", "12기", 24, "데이터"));
		this.addStudent(new Student(1114, "김삼성", "12기", 29, "자바전공"));
	}
	
	private static StudentMgrImpl instance = new StudentMgrImpl();
	
	public static StudentMgrImpl getInstance() {
		return instance;
	};
	
	private static final int MAX_SIZE = 100;
	private Student[] students = new Student[MAX_SIZE];
	private int size;

	@Override
	public boolean addStudent(Student s) {
		for(int i=0; i<size+1; i++) {
			if(students[i] !=null && students[i].getStudentNo() == s.getStudentNo()) {
				return false;
			}
		}
		students[size++] = s;
		return true;
	}

	@Override
	public Student[] search() {
		return Arrays.copyOf(students, size);
	}

	@Override
	public Student search(int num) throws NotFoundException {
		// TODO Auto-generated method stub
		for(int i=0; i<size; i++) {
			if(students[i].getStudentNo() == num) {
				return students[i];
			}
		}
		throw new NotFoundException();
	}

	@Override
	public Student[] search(String name) {
		// TODO Auto-generated method stub
		int cnt = 0; //몇 명인지
		for(int i=0; i<size; i++) {
			if(students[i].getName().equals(name)) {
				cnt++;
			}
		}
		
		Student[] tmp_stu = new Student[cnt];
		int tmp = 0;
		for(int i=0; i<size; i++) {
			if(students[i].getName().equals(name)) {
				tmp_stu[tmp] = students[i];
			}
		}
		return tmp_stu;
	}

	@Override
	public void update(int studentNum, String major) {
		// TODO Auto-generated method stub
		for(int i=0; i<size; i++) {
			if(students[i].getStudentNo() == studentNum) {
				students[i].setMajor(major);
			}
		}
	}

	@Override
	public boolean delete(int studentNum) {
		// TODO Auto-generated method stub
		for(int i=0; i<size; i++) {
			if(students[i].getStudentNo() == studentNum) {
				students[i] = null;
				size--;
			}
			
			if(students[i] == null) {
				if(students[i+2] == null && students[i+1] == null) {
					return true;
				}
				students[i] = students[i+1];
			}
		}
		return false;
	}

}
