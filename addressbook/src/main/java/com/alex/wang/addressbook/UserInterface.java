package com.alex.wang.addressbook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import java.util.regex.Pattern;

import java.util.regex.Matcher;



public class UserInterface {
	AddressBook book;
    private static Scanner in = new Scanner(System.in);
	public UserInterface(AddressBook book) {
		this.book = book;
	}

	public void start() throws IOException {
		System.out.println("************************************************************************");
		System.out.println("                         AddressBook                                    ");
		System.out.println("                  *A:add new User Address   ");
		System.out.println("                  *D:delete User            ");
		System.out.println("                  *S:search User by phoneNum");
		System.out.println("                  *L:list all User          ");
		System.out.println("                  *Q:Quit the program       ");
		System.out.println("************************************************************************" );
		while (true) {
			System.out.print("please input the option(A/D/S/L/Q):");
			
			String readLine = in.nextLine();
			if (readLine.length() != 1) {
				System.out.print("input error,please input again");
				continue;
			}
			char c = readLine.toUpperCase().charAt(0);
			switch (c) {
			case 'A':add();break;
			case 'D':delete();break;
			case 'S':search();break;
			case 'L':list(book.list());break;
			case 'Q':quit();break;
			}
			
		}

	}

	private void add() {

		System.out.print("UserName:");
		String name = in.nextLine();
		System.out.print("Address:");
		String address = in.nextLine();
		String phoneNum ;
		do{
		   System.out.print("PhoneNumber:");
		   phoneNum = in.nextLine();
		}while(!checkNumber(phoneNum));

		UserInfo info = new UserInfo(name,address,phoneNum);
		
		boolean result = book.add(info);
		if(!result){
			System.out.println("user "+name + " repeat");
		}else{
			System.out.println("add user "+ name +" successful");
		}
		
	}

	private void delete() {
		System.out.print("please input the username:");
		String name = in.nextLine();
		boolean result = book.delete(name);
		if(!result){
			System.out.println("not exist this user " + name);
		}else{
			System.out.println("delete user " + name + " successful");
		}
	}

	private void search() {
		System.out.print("please input the phoneNum:");
		String phoneNum = in.nextLine();
		List<UserInfo>  result = book.search(phoneNum);
        if(result.size() == 0){
        	System.out.println("not exist this phoneNum " + phoneNum);
        }else{
        	list(result);
        }
	}

	private void list(List<UserInfo> list) {
		System.out.println("************************************************************************" );
		System.out.printf("%-15S %-20S %-30S","UserName" , "|Address ","|phoneNumber" );
		System.out.println();
		System.out.println("************************************************************************" );
	
		   Iterator<UserInfo> it = list.iterator();
		   while(it.hasNext()){
			   UserInfo cur = (UserInfo)(it.next());
			   System.out.printf("%-15S %-20S %-30S",cur.getName()    ,"|"+cur.getAddress()   ,"|"+cur.getPhoneNum() );
			   System.out.println();
		   }
	}
	private void quit() {
		System.out.print("confirm exist(Y/N):");
		String readLine = in.nextLine().toUpperCase();
		if(readLine.equals("Y")){
			System.out.println(" BYEBYE");
		   System.exit(0);
		}else{
			System.out.println(" we will continue");
		}
	}
	
	public boolean checkNumber(String s){
		String phoneNumberPattern = "^[0-9]{3}[-][0-9]{7,8}$";
		Pattern ps = Pattern.compile(phoneNumberPattern);
		Matcher mt = ps.matcher(s.trim());
		if(mt.find()){
			return true;
		}else{
			System.out.println("number pattern error,should be [3位数字]-[7位或者8位数字]的格式,如021-34322123");
			return false;
		}
		
	}
	
	
}
