package com.obolonyk.dataStructures.List.ArrayList;

import com.obolonyk.dataStructures.List.List;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.StringJoiner;

public class ArrayList<T> implements List<T> {
    private static final double LOAD_FACTOR = 1.5;
    private static final int DEFAULT_CAPACITY = 10;

    private int size;
    private T[] array;

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayList(int length) {
        this.array = (T[]) new Object[length];
    }

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        checkInBounds(index, size);
        if (size == array.length - 1) {
            ensureCapacity();
        }
        System.arraycopy(array, index, array, index + 1, size - index + 1);
        array[index] = value;
        size++;
    }

    @Override
    public T remove(int index) {
        checkInBounds(index, size - 1);
        Object removedValue = array[index];
        System.arraycopy(array, index + 1, array, index, array.length - index - 1);
        array[size - 1] = null;
        size--;
        return (T) removedValue;
    }

    @Override
    public T get(int index) {
        checkInBounds(index, size);
        return array[index];
    }

    @Override
    public T set(T value, int index) {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("Set element in empty list");
        }
        checkInBounds(index, size - 1);
        Object oldValue = array[index];
        array[index] = value;
        return (T) oldValue;
    }

    @Override
    public void clear() {
        array = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(T value) {
        return indexOf(value) != -1;
    }

    @Override
    public int indexOf(T value) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(array[i], value)) {
                return i;
            }
        }
        return index;
    }

    @Override
    public int lastIndexOf(T value) {
        int index = -1;
        for (int i = size - 1; i > 0; i--) {
            if (Objects.equals(array[i], value)) {
                index = i;
            }
        }
        return index;
    }

    private void checkInBounds(int index, int endOfBound) {
        if (index < 0 || index > endOfBound) {
            throw new IndexOutOfBoundsException("Element on the index " + index + " , is out of bounds: 0 - " + endOfBound);
        }
    }

    private void ensureCapacity() {
        int newSize = (int) (size * LOAD_FACTOR);
        T[] newArray = (T[]) new Object[newSize];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }

    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(", ", "[", "]");
        Iterator<T> iterator = iterator();
        while (iterator.hasNext()) {
            stringJoiner.add(iterator.next().toString());
        }
        return stringJoiner.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<T> {
        private int nextPosition = 0;
        private int currentPosition = -1;

        @Override
        public boolean hasNext() {
            return nextPosition < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("This element does not exist");
            }
            T result = array[nextPosition];
            currentPosition++;
            nextPosition++;
            return result;
        }

        @Override
        public void remove() {
            if (currentPosition != -1) {
                ArrayList.this.remove(currentPosition);
                currentPosition = -1;
            } else {
                throw new IllegalStateException("The removing element is not identified");
            }
        }
    }

}
