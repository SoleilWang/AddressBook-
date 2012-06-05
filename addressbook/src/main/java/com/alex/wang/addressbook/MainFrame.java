package com.alex.wang.addressbook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.alex.wang.addressbook.ui.ConsoleUI;
import com.alex.wang.addressbook.ui.UIInterface;
import com.alex.wang.addressbook.entity.AddressBook;
import com.alex.wang.addressbook.exception.AddressBookException;
import com.alex.wang.addressbook.persistence.PersistenceInterface;
import com.alex.wang.addressbook.persistence.XmlPersistence;

public class MainFrame {
    private static final Logger logger = LoggerFactory.getLogger(MainFrame.class);

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) {

        final PersistenceInterface perInstance = new XmlPersistence();
        final UIInterface gui = new ConsoleUI();

        ExecutorService exec = Executors.newSingleThreadExecutor();
        Runnable run = new Runnable() {
            public void run() {
                try {
                    logger.info("program start");
                    System.out.println("read addressBook....................");
                    AddressBook book = perInstance.read();
                    gui.registerBook(book);
                    gui.start();
                    System.out.println("write addressBook...................");
                    perInstance.write(gui.getBook());
                    System.out.println("ByeBye");
                    logger.info("program exit");
                } catch (AddressBookException e) {
                    logger.info("program error", e);
                    System.out.println(e.getMessage());
                }
            }
        };
        exec.execute(run);
        exec.shutdownNow();
    }

}
