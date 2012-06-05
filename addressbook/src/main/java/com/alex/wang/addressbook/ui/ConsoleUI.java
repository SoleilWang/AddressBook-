package com.alex.wang.addressbook.ui;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alex.wang.addressbook.entity.AddressBook;
import com.alex.wang.addressbook.entity.UserInfo;

/**
 * Console UI interface
 * 
 * @author ewyabdl
 * 
 */
public class ConsoleUI implements UIInterface {
    private static final Logger logger = LoggerFactory.getLogger(ConsoleUI.class);
    private AddressBook book;
    private static Scanner in = new Scanner(System.in);

    public void registerBook(AddressBook book) {
        if (book != null) {
            this.book = book;
        }
    }

    public AddressBook getBook() {
        return this.book;
    }

    public ConsoleUI() {
        super();
        this.book = new AddressBook();
    }

    public void start() {

        System.out.println("************************************************************************");
        System.out.println("                         AddressBook                                    ");
        System.out.println("                  *A:add new User Address   ");
        System.out.println("                  *D:delete User            ");
        System.out.println("                  *S:search User by phoneNum");
        System.out.println("                  *L:list all User          ");
        System.out.println("                  *Q:Quit the program       ");
        System.out.println("************************************************************************");
        while (true) {

            System.out.print("please input the option(A/D/S/L/Q):");

            String readLine = getCommand();
            if (readLine.length() != 1) {
                System.out.print("input error,please input again");
                continue;
            }
            char c = readLine.toUpperCase().charAt(0);
            logger.info("user input the Option:" + c);
            switch (c) {
            case 'A':
                add();
                break;
            case 'D':
                delete();
                break;
            case 'S':
                search();
                break;
            case 'L':
                list(book.list());
                logger.info("list all users succesfully");
                break;
            case 'Q':
                if (quit())
                    return;
                break;
            }
        }
    }

    void add() {

        System.out.print("UserName:");
        String name = getCommand();
        System.out.print("Address:");
        String address = getCommand();
        String phoneNum;
        do {
            System.out.print("PhoneNumber:");
            phoneNum = getCommand();
        } while (!checkNumber(phoneNum));

        UserInfo info = new UserInfo(name, address, phoneNum);
        logger.info("users want to add  user" + info.toString());
        boolean result = book.add(info);
        if (!result) {
            System.out.println("user " + name + " repeat");
            logger.info("the user repeat,so not add");
        } else {
            System.out.println("add user " + name + " successfully");
            logger.info("add user successfully");
        }

    }

    /**
     * Read a line of input from console
     * 
     * @return the input from user
     */
    public String getCommand() {
        return in.nextLine().trim();
    }

    void delete() {
        System.out.print("please input the username:");
        String name = getCommand();
        logger.info("users want to delete  user : " + name);
        boolean result = book.delete(name);
        if (!result) {

            System.out.println("not exist this user " + name);
            logger.info("not exist this user: " + name + " so don't delete any uses");
        } else {

            System.out.println("delete user " + name + " successfully");
            logger.info("delete user " + name + " successfully");
        }
    }

    List<UserInfo> search() {
        System.out.print("please input the phoneNum:");
        String phoneNum = getCommand();
        logger.info("users want to search by   phoneNum : " + phoneNum);
        List<UserInfo> result = book.search(phoneNum);
        if (result.size() == 0) {
            System.out.println("not exist this phoneNum " + phoneNum);
            logger.info("not exist this phoneNum " + phoneNum);
        } else {
            list(result);
            logger.info("search successfully ,exist " + result.size() + " users");
        }
        return result;
    }

    void list(List<UserInfo> list) {
        System.out.println("************************************************************************");
        System.out.printf("%-15S %-20S %-30S", "UserName", "|Address ", "|phoneNumber");
        System.out.println();
        System.out.println("************************************************************************");

        Iterator<UserInfo> it = list.iterator();
        while (it.hasNext()) {
            UserInfo cur = (UserInfo) (it.next());
            System.out.printf("%-15S %-20S %-30S", cur.getName(), "|" + cur.getAddress(), "|" + cur.getPhoneNum());
            System.out.println();
        }
    }

    boolean quit() {
        System.out.print("confirm exist(Y/N):");
        String readLine = getCommand().toUpperCase();
        if (readLine.equals("Y")) {
            logger.info("users  confirm to quit ");
            return true;
        } else {
            System.out.println("we will continue");
            logger.info("users not confirm to quit ,program continue");
            return false;

        }
    }

    private boolean checkNumber(String s) {
        String phoneNumberPattern = "^[0-9]{3}[-][0-9]{7,8}$";
        Pattern ps = Pattern.compile(phoneNumberPattern);
        Matcher mt = ps.matcher(s.trim());
        if (mt.find()) {
            return true;
        } else {
            System.out.println("number pattern error,should be ^[0-9]{3}[-][0-9]{7,8}$,like 021-34322123");
            return false;
        }

    }

}
