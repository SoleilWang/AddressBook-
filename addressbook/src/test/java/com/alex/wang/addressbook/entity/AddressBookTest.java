package com.alex.wang.addressbook.entity;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AddressBookTest {

    private AddressBook book;

    @Before
    public void setUp() throws Exception {
        book = new AddressBook();
        List<UserInfo> userList = new ArrayList<UserInfo>();
        userList.add(new UserInfo("wang zhen", "tianshan road", "021-98764342"));
        userList.add(new UserInfo("wang zhen", "pudong road", "021-22364342"));
        userList.add(new UserInfo("li yang", "tianshan road", "021-98764342"));
        book.setUserList(userList);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testAddRepeatUser() {
        assertTrue(!book.add(new UserInfo("wang zhen", "tianshan road", "021-98764342")));
    }

    @Test
    public void testAddNotRepeatUser() {
        assertTrue(book.add(new UserInfo("li zhen", "tianshan road", "021-98764342")));
    }

    @Test
    public void testDeleteNotExistUser() {
        assertTrue(!book.delete("li qiang"));
    }

    @Test
    public void testDeleteMultiUser() {
        assertTrue(book.delete("wang zhen"));
    }

    @Test
    public void testDeleteOneUser() {
        assertTrue(book.delete("li yang"));
    }

    @Test
    public void testSearchByFullNo() {
        List<UserInfo> users = new ArrayList<UserInfo>();
        users.add(new UserInfo("wang zhen", "pudong road", "021-22364342"));
        assertEquals(users, book.search("021-22364342"));
    }

    @Test
    public void testSearchByPartNo() {
        List<UserInfo> users = new ArrayList<UserInfo>();
        users.add(new UserInfo("wang zhen", "tianshan road", "021-98764342"));
        users.add(new UserInfo("li yang", "tianshan road", "021-98764342"));
        assertEquals(users, book.search("021-98764342"));
    }

}
