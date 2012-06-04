package com.alex.wang.addressbook.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserInfoTest {

    private UserInfo user;

    @Before
    public void setUp() throws Exception {
        user = new UserInfo("wang zhen", "pudong road", "021-22364342");
    }

    @After
    public void tearDown() throws Exception {
        user = null;
        File logFile = new File("address.log");
        if (logFile.exists()) {
            logFile.delete();
        }
    }

    @Test
    public void testHashcodeWhenAttNull() {
        UserInfo oneUser = new UserInfo();
        assertEquals("hashcode result not right when attrs value are null", 31 * 31 * 31, oneUser.hashCode());
    }

    @Test
    public void testHashcodeWhenAttNotNull() {
        int prime = 31;
        int result = 1;
        result = prime * result + user.getAddress().hashCode();
        result = prime * result + user.getName().hashCode();
        result = prime * result + user.getPhoneNum().hashCode();
        assertEquals("hashcode result not right when attrs value are not null", result, user.hashCode());
    }

    @Test
    public void testToString() {
        assertEquals("toString method not right", "[ Name: wang zhen ,Address: pudong road , PhoneNum: 021-22364342 ]", user.toString());
    }

    @Test
    public void testEqualNotNull() {
        UserInfo oneUser = new UserInfo();
        oneUser.setAddress("pudong road");
        oneUser.setName("wang zhen");
        oneUser.setPhoneNum("021-22364342");
        assertTrue("equals method not right when attr not null", oneUser.equals(user));

    }

    @Test
    public void testEqualWhenAddrNull() {
        UserInfo oneUser = new UserInfo();
        oneUser.setName("wang zhen");
        oneUser.setPhoneNum("021-22364342");
        assertFalse("equals method not right when addr is null", oneUser.equals(user));

    }

    @Test
    public void testEqualWhenNameNull() {
        UserInfo oneUser = new UserInfo();
        oneUser.setAddress("pudong road");
        oneUser.setPhoneNum("021-22364342");
        assertFalse("equals method not right when Name is null", oneUser.equals(user));

    }

    @Test
    public void testEqualWhenNumberNull() {
        UserInfo oneUser = new UserInfo();
        oneUser.setAddress("pudong road");
        oneUser.setName("wang zhen");
        assertFalse("equals method not right when number is null", oneUser.equals(user));

    }

    @Test
    public void testEqualWhenObjectNull() {
        assertFalse("equals method not right when compare object is null", user.equals(null));

    }

}
