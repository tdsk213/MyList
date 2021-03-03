package ru.madbrains.javacourse.homework.part1.ru.madbrains.javacourse;

import ru.madbrains.javacourse.homework.part1.AdvancedList;
import ru.madbrains.javacourse.homework.part1.MyList;
import ru.madbrains.javacourse.homework.part1.SimpleList;

import java.util.ArrayList;
import java.util.List;

public class Program {

    public static void main(String[] args) throws Exception {
        AdvancedList<Integer> testList = new MyList<>();
        SimpleList<Integer> secondList = new MyList<>();

        testList.add(1);
        testList.add(2);
        testList.add(3);
        testList.add(4);
        testList.add(5);
        testList.add(6);
        testList.add(7);
        testList.add(8);
        testList.add(9);
        testList.add(10);

//        for (int i = 1; i <= 200; i++)
//             secondList.add(i);

        if (testList.isEmpty()) System.out.println("true");


//        for (int i = 0; i < testList.size(); i++) {
//            System.out.println("element: " + testList.get(i).get());
//        }

//        System.out.println("size: " + testList.size());
//        testList.remove(1);

//        testList.insert(2, 32);

//        for (int i = 0; i < testList.size(); i++) {
//            System.out.println("element: " + testList.get(i).get());
//        }

//        System.out.println("size: " + testList.size());
//        System.out.println("first: " + testList.first(1));
//        System.out.println("last: " + testList.last(1));

        testList.remove(3);
        System.out.println("size: " + testList.size());
//        for (int i = 0; i < testList.size(); i++) {
//            System.out.println("element: " + testList.get(i).get());
//        }


        testList.addAll(testList);

        System.out.println("size: " + testList.size());


        for (int i = 0; i < testList.size(); i++) {
            System.out.println("element[" + i + "]: " + testList.get(i).get());
        }



        System.out.println("size: " + testList.size());

        testList.shuffle();

        for (int i = 0; i < testList.size(); i++) {
            System.out.println("element[" + i + "]: " + testList.get(i).get());
        }

        System.out.println("size: " + testList.size());

        testList = testList.shuffle();

        for (int i = 0; i < testList.size(); i++) {
            System.out.println("element[" + i + "]: " + testList.get(i).get());
        }

        System.out.println("size: " + testList.size());
    }
}
