package com.alex.wang.addressbook;

import java.io.IOException;

public class MainFrame {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
        AddressBook book = new AddressBook();
        UserInterface gui = new UserInterface(book);
        gui.start();
	}

}
