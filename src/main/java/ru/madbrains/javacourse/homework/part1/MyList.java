package ru.madbrains.javacourse.homework.part1;

import java.util.*;

public class MyList<T> implements AdvancedList<T>, AuthorHolder {

    private int maxCapacity = 10;
    private int size = 0;
    private static final int MIN_MERGE = 64;


    private Object[] elements;

    public MyList() {
        this.elements = new Object[maxCapacity];
    }

    @Override
    public AdvancedList<T> shuffle() {
        AdvancedList<T> shuffledList = new MyList<>();
        Object[] shuffledArray = new Object[size];
        System.arraycopy(elements, 0, shuffledArray, 0, size);
        int index;
        Object temp;
        Random random = new Random();
        for (int i = size - 1; i > 0; i--)
        {
            index = random.nextInt(i + 1);
            temp = shuffledArray[index];
            shuffledArray[index] = shuffledArray[i];
            shuffledArray[i] = temp;
        }
        for (int i = 0; i < this.size; i++) {
            shuffledList.add((T)shuffledArray[i]);
        }
        return shuffledList;
    }

    @Override
    public AdvancedList<T> sort(Comparator<T> comparator) {

        AdvancedList<T> sortedList = new MyList<>();
        Object[] sortedArray = new Object[size];
        System.arraycopy(elements, 0, sortedArray, 0, size);
        timSort(sortedArray, comparator);

        for (int i = 0; i < this.size; i++) {
            sortedList.add((T)sortedArray[i]);
        }
        return sortedList;
    }

    private static int getMinrun(int n)
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

    private void insertionSort(Object[] arr, int left, int right, Comparator<T> comparator) {
        for (int i = left + 1; i <= right; i++) {
            Object temp = arr[i];
            int j = i - 1;
            while (j >= left && comparator.compare((T)arr[j], (T)temp) > 0) //arr[j] > temp
            {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = temp;
        }
    }

    private void merge(Object[] arr, int l, int m, int r, Comparator<T> comparator) {

        int len1 = m - l + 1,
                len2 = r - m;
        Object[] left = new Object[len1];
        Object[] right = new Object[len2];

        if (len1 >= 0) System.arraycopy(arr, l + 0, left, 0, len1);

        for (int x = 0; x < len2; x++) {
            right[x] = arr[m + 1 + x];
        }

        int i = 0;
        int j = 0;
        int k = l;

        while (i < len1 && j < len2) {
            if (comparator.compare((T)left[i], (T)right[j]) < 0) { //left[i] <= right[j]
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

    public void timSort(Object[] arr, Comparator<T> comparator) {
        int n = arr.length;
        int minRun = getMinrun(size);

        for (int i = 0; i < n; i += minRun) {
            insertionSort(arr, i, Math.min((i + 31), (n - 1)), comparator);
        }

        for (int size = minRun; size < n; size = 2 * size) {

            for (int left = 0; left < n; left += 2 * size) {

                int mid = left + size - 1;
                int right = Math.min((left + 2 * size - 1),
                        (n - 1));

                merge(arr, left, mid, right, comparator);
            }
        }
    }

    @Override
    public String author() {
        return "Dmitry Toporkov";
    }

    @Override
    public void add(T item) {
            extendIfFull();
            elements[size++] = item;
    }

    @Override
    public void insert(int index, T item) {
            extendIfFull();
            size++;

        if (this.size - 1 - index >= 0) System.arraycopy(elements, index, elements, index + 1, this.size - 1 - index);
        elements[index] = item;
    }

    @Override
    public void remove(int index) {
        if (this.size > 0) {
            this.size--;
            if (this.size - index >= 0) System.arraycopy(elements, index + 1, elements, index, this.size - index);
        }
    }

    @Override
    public Optional<T> get(int index) {
        return Optional.ofNullable((T)elements[index]);
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void addAll(SimpleList<T> list) {
        int newSize = list.size() + this.size;
        if (this.maxCapacity < newSize) {
                Object[] newElements = new Object[this.maxCapacity + list.size()];
                System.arraycopy(elements, 0, newElements, 0, this.size);
                int index = this.size;
                for (int i = 0; i < list.size(); i++) {
                    newElements[index] = list.get(i).get();
                    index++;
                }
                this.elements = newElements;
                this.maxCapacity = elements.length;
        } else {
            int index = this.size;
            for (int i = 0; i < list.size(); i++) {
                elements[index] = list.get(i).get();
                index++;
            }
        }
        this.size = newSize;
    }

    @Override
    public int first(T item) {
        for (int i = 0; i < this.size; i++) {
            if (item.equals(elements[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int last(T item) {
        for (int i = this.size - 1; i >= 0; i--) {
            if (item.equals(elements[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean contains(T item) {
        for (int i = 0; i < this.size; i++) {
            if (item.equals(elements[i])) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return this.size <= 0;
    }

    private void extendIfFull () {
        if (this.size() >= elements.length) {
            int newSize = this.maxCapacity + (this.maxCapacity >> 1);
            if (newSize > 0) {
                Object[] newElements = new Object[newSize];
                if (this.maxCapacity >= 0) System.arraycopy(this.elements, 0, newElements, 0, this.maxCapacity);
                this.elements = newElements;
                this.maxCapacity = elements.length;
            }
        }
    }


}
