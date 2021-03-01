package ru.madbrains.javacourse.homework.part1;

import java.util.*;

public class MyList<T> implements AdvancedList<T>, AuthorHolder {

    private int maxCapacity = 10;
    private int size = 0;

    private Object[] elements;

    public MyList() {
        this.elements = new Object[maxCapacity];
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
