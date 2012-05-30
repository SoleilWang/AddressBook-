package com.alex.wang.addressbook.ui;

import com.alex.wang.addressbook.entity.AddressBook;

public interface UIInterface {
    void registerBook(AddressBook book);
    AddressBook getBook();
    void start();
}
