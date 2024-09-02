package com.ssafy.edu;

public class NotFoundException extends Exception{
	public NotFoundException(String msg) {
		super("학번 : "+msg+"을 찾을 수 없습니다.");
	}
	public NotFoundException() {
		this("NotFound예외");
	}
}
