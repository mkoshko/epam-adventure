package by.koshko.cyberwikia.main;

import by.koshko.cyberwikia.bean.Role;

import java.time.LocalDate;

public class Test {
    public static void main(final String[] args) {
        LocalDate date1 = LocalDate.parse("2009-05-20");
        System.out.println(date1);
        LocalDate date2 = LocalDate.parse("2005-04-15");
        System.out.println(date2);
        System.out.println(date1 + " is after " + date2 + " : " + date1.isAfter(date2));
        System.out.println(date1 + " is before " + date2 + " : " + date1.isBefore(date2));
        System.out.println(date2.compareTo(date1));
    }
}
