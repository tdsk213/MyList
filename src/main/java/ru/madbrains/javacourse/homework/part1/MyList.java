package ru.madbrains.javacourse.homework.part1;


import java.util.*;

public class MyList<T> implements AdvancedList<T>, AuthorHolder {

    private int maxCapacity = 10;
    private int size = 0;
    

    private Object[] elements;

    public MyList() {
        elements = new Object[maxCapacity];
    }

    public Object[] getElements() {
        return elements;
    }

    @Override
    public AdvancedList<T> shuffle() {
        return null;
    }

    @Override
    public AdvancedList<T> sort(Comparator<T> comparator) {
        return null;
    }

    @Override
    public String author() {
        return "Dmitry Toporkov";
    }

    @Override
    public void add(T item) {
        if (elements == null) {
            elements = new Object[maxCapacity];
        } else {
            if (maxCapacity > this.size && this.size >= 0) {
                elements[++size] = item;
            } else if (elements.length == this.maxCapacity) {
                elements = extendList();
                this.maxCapacity = elements.length;
                elements[++size] = item;
            }
        }
    }

    @Override
    public void insert(int index, T item) throws Exception {

    }

    @Override
    public void remove(int index) throws Exception {

    }

    @Override
    public Optional<T> get(int index) {
        return Optional.of((T) elements[index]);
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void addAll(SimpleList<T> list) {

    }

    @Override
    public int first(T item) {
        return 0;
    }

    @Override
    public int last(T item) {
        return 0;
    }

    @Override
    public boolean contains(T item) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    private Object[] extendList() {
        int newSize = this.maxCapacity + (this.maxCapacity >> 1);
        Object [] newList = new Object[newSize];
        for (int i = 0; i < this.maxCapacity; i++) {
            newList[i] = this.elements[i];
        }
        return newList;
    }

}
