package ru.madbrains.javacourse.homework.part1.ru.madbrains.javacourse;

import java.util.Comparator;
import java.util.Map;

public class TimSort {

    private static final int MIN_MERGE = 32;

    private static boolean compare(Comparable s1, Comparable s2) {

        if (new Comparator<Comparable>() {
            @Override
            public int compare(Comparable o1, Comparable o2) {
                return o1.compareTo(o2);
            }
        }.compare(s1, s2) >= 0) {
            return true;
        } else
            return false;
    }

    public static int getMinrun(int n)
    {
        assert n >= 0;

        int r = 0;
        while (n >= MIN_MERGE)
        {
            r |= (n & 1);
            n >>= 1;
        }
        return n + r;
    }

    public static void insertionSort(String[] arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            String temp = arr[i];
            int j = i - 1;
            while (j >= left && compare(arr[j], temp)) //arr[j] > temp
            {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = temp;
        }
    }

    // Merge function merges the sorted runs
    public static void merge(String[] arr, int l, int m, int r) {

        int len1 = m - l + 1, len2 = r - m;
        String[] left = new String[len1];
        String[] right = new String[len2];

        for (int x = 0; x < len1; x++) {
            left[x] = arr[l + x];
        }

        for (int x = 0; x < len2; x++) {
            right[x] = arr[m + 1 + x];
        }

        int i = 0;
        int j = 0;
        int k = l;

        while (i < len1 && j < len2) {
            if (compare(left[i], right[j])) { //left[i] <= right[j]
                arr[k] = left[i];
                i++;
            }
            else {
                arr[k] = right[j];
                j++;
            }
            k++;
        }

        while (i < len1) {
            arr[k] = left[i];
            k++;
            i++;
        }

        while (j < len2) {
            arr[k] = right[j];
            k++;
            j++;
        }
    }

    public static void timSort(String[] arr) {
        int n = arr.length;
        int minRun = getMinrun(MIN_MERGE);

        for (int i = 0; i < n; i += minRun) {
            insertionSort(arr, i, Math.min((i + 31), (n - 1)));
        }

        for (int size = minRun; size < n; size = 2 * size) {

            for (int left = 0; left < n; left += 2 * size) {

                int mid = left + size - 1;
                int right = Math.min((left + 2 * size - 1),
                        (n - 1));

                merge(arr, left, mid, right);
            }
        }
    }

    public static void printArray(String[] arr, int n) {
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.print("\n");
    }


    public static void main(String[] args)
    {
//        String[] arr = { -2, 7,  15,  -14, 0, 15,  0, 7, -7, -4, -13, 5, 8, -14, 12 };
        String[] arr = {"Dima", "Anna", "Sergei"};
        int n = arr.length;
        System.out.println("Given Array is");
        printArray(arr, n);

        timSort(arr);

        System.out.println("After Sorting Array is");
        printArray(arr, n);
    }
}
