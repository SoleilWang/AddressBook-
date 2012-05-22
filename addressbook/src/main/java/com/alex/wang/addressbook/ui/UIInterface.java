package com.alex.wang.addressbook.ui;

import com.alex.wang.addressbook.entity.AddressBook;

public interface UIInterface {
    public void registerBook(AddressBook book);
    public AddressBook getBook();
    public void start();
}
