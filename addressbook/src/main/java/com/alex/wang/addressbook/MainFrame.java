package com.alex.wang.addressbook;

import java.io.IOException;

import com.alex.wang.addressbook.entity.AddressBook;
import com.alex.wang.addressbook.exception.AddressBookException;
import com.alex.wang.addressbook.persistence.PersistenceInterface;
import com.alex.wang.addressbook.persistence.XmlPersistence;
import com.alex.wang.addressbook.ui.ConsoleUI;
import com.alex.wang.addressbook.ui.UIInterface;

public class MainFrame {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args){
		// TODO Auto-generated method stub
	    PersistenceInterface  perInstance = new XmlPersistence();
	    UIInterface gui = new ConsoleUI();
        AddressBook book;
        try {
            System.out.println("read addressBook....................");
            book = perInstance.read();
            gui.registerBook(book);
            gui.start();
            System.out.println("write addressBook...................");
            perInstance.write(gui.getBook());
            System.out.println("ByeBye");
        } catch (AddressBookException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
	}

}
