package com.ssafy.edu;

public class Student {
	private int studentNo;
	private String name;
	private String number;
	private int age;
	private String major;
	
	public Student() {
		// TODO Auto-generated constructor stub
	}



	public Student(int studentNo, String name, String number, int age, String major) {
		super();
		this.studentNo = studentNo;
		this.name = name;
		this.number = number;
		this.age = age;
		this.major = major;
	}



	public int getStudentNo() {
		return studentNo;
	}

	public void setStudentNo(int studentNo) {
		this.studentNo = studentNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	

	public String getMajor() {
		return major;
	}



	public void setMajor(String major) {
		this.major = major;
	}



	@Override
	public String toString() {
		return "studentNo=" + studentNo + ", name=" + name + ", number=" + number + ", age=" + age + ", major="
				+ major + " ";
	}



	
	

}
