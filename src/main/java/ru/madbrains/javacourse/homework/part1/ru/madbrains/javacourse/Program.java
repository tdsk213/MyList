package ru.madbrains.javacourse.homework.part1.ru.madbrains.javacourse;

import ru.madbrains.javacourse.homework.part1.MyList;

import java.util.ArrayList;
import java.util.List;

public class Program {

    public static void main(String[] args) {
        MyList<Integer> testList = new MyList<>();
        List<Integer> javalist = new ArrayList<>();

        testList.add(1);
        testList.add(2);
        testList.get(0);
//        for (int i = 0; i < testList.size(); i++) {
//            System.out.println(testList.get(i));
//        }
    }
}
