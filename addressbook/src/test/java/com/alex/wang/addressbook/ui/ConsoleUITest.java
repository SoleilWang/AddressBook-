package com.alex.wang.addressbook.ui;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import java.io.File;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.alex.wang.addressbook.entity.AddressBook;
import com.alex.wang.addressbook.entity.UserInfo;

public class ConsoleUITest {

    private AddressBook book;
    private ConsoleUI uiSpy;

    @Before
    public void setUp() throws Exception {
        book = new AddressBook();
        ConsoleUI ui = new ConsoleUI();
        ui.registerBook(book);
        uiSpy = spy(ui);

    }

    @After
    public void tearDown() throws Exception {

        book = null;
        uiSpy = null;
        File logFile = new File("address.log");
        if (logFile.exists()) {
            logFile.delete();
        }
    }

    @Test
    public void testAdd() {
        doReturn("A").doReturn("wang").doReturn("tianshan").doReturn("090-343234").doReturn("090-34323234").doReturn("Q").doReturn("Y").when(uiSpy)
                .getCommand();
        uiSpy.start();
        verify(uiSpy).add();
        UserInfo user = new UserInfo("wang", "tianshan", "090-34323234");
        assertEquals("add operation error", user, uiSpy.getBook().getUserList().get(0));

    }

    @Test
    public void testAddRepeatUser() {
        UserInfo user = new UserInfo("wang", "tianshan", "090-34323234");
        book.add(user);
        doReturn("A").doReturn("wang").doReturn("tianshan").doReturn("090-343234").doReturn("090-34323234").doReturn("Q").doReturn("Y").when(uiSpy)
                .getCommand();
        uiSpy.start();
        verify(uiSpy).add();
        assertEquals("add repeat user operation error", 1, uiSpy.getBook().getUserList().size());

    }

    @Test
    public void testDelete() {
        UserInfo user = new UserInfo("wang", "tianshan", "090-34323234");
        book.add(user);
        doReturn("D").doReturn("wang").doReturn("Q").doReturn("Y").when(uiSpy).getCommand();
        uiSpy.start();
        verify(uiSpy).delete();
        assertEquals("delete operation error", 0, uiSpy.getBook().getUserList().size());
    }
    @Test
    public void testdeleteNotExistUser() {
        UserInfo user = new UserInfo("wang", "tianshan", "090-34323234");
        book.add(user);
        doReturn("D").doReturn("wangLI").doReturn("Q").doReturn("Y").when(uiSpy)
                .getCommand();
        uiSpy.start();
        verify(uiSpy).delete();
        assertEquals("add repeat user operation error", 1, uiSpy.getBook().getUserList().size());

    }


    @Test
    public void testSearchByFullNo() {
        UserInfo user1 = new UserInfo("wang", "tianshan", "090-34323234");
        UserInfo user2 = new UserInfo("li", "tianshan", "099-34323234");
        book.add(user1);
        book.add(user2);
        doReturn("S").doReturn("090-34323234").doReturn("Q").doReturn("Y").when(uiSpy).getCommand();
        uiSpy.start();
        verify(uiSpy).search();
        assertEquals("search operation error", user1, uiSpy.getBook().getUserList().get(0));

    }

    @Test
    public void testSearchByPartNo() {
        UserInfo user1 = new UserInfo("wang", "tianshan", "090-34323234");
        UserInfo user2 = new UserInfo("li", "tianshan", "089-34323234");
        book.add(user1);
        book.add(user2);
        doReturn("089").doReturn("Q").doReturn("Y").when(uiSpy).getCommand();
        List<UserInfo> result = uiSpy.search();
        assertEquals("search operation error", user2, result.get(0));

    }

    @Test
    public void testSearchByNotExistNo() {
        UserInfo user1 = new UserInfo("wang", "tianshan", "090-34323234");
        UserInfo user2 = new UserInfo("li", "tianshan", "099-34323234");
        book.add(user1);
        book.add(user2);
        doReturn("S").doReturn("0992").doReturn("Q").doReturn("Y").when(uiSpy).getCommand();
        List<UserInfo> result = uiSpy.search();
        assertEquals("search operation error", 0, result.size());

    }

    @Test
    public void testQuit() {

        doReturn("Q").doReturn("Y").when(uiSpy).getCommand();
        uiSpy.start();
        verify(uiSpy).quit();

    }

    @Test
    public void testList() {

        doReturn("L").doReturn("Q").doReturn("Y").when(uiSpy).getCommand();
        uiSpy.start();
        verify(uiSpy).list(book.list());

    }
}
