package com.alex.wang.addressbook.ui;

import com.alex.wang.addressbook.entity.AddressBook;

/**
 * UI interface
 * 
 * @author ewyabdl
 * 
 */
public interface UIInterface {
    /**
     * set the AddressBook object to store users's data
     * 
     * @param book
     *            Initialized AddressBook object
     */
    void registerBook(AddressBook book);

    /**
     * get AddressBook object which used to store users's data
     * 
     * @return copy of the AddressBook object
     */
    AddressBook getBook();

    /**
     * start the ui interface, get input from user and handle the request
     */
    void start();
}
