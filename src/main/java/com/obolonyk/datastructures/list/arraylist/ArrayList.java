package com.obolonyk.datastructures.list.arraylist;

import com.obolonyk.datastructures.list.List;

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

    @SuppressWarnings("unchecked")
    public ArrayList(int capacity) {
        this.array = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        validateIndexForAdd(index);
        if (size == array.length) {
            ensureCapacity();
        }

        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = value;
        size++;
    }

    @Override
    public T remove(int index) {
        validateIndex(index);
        T removedValue = array[index];
        System.arraycopy(array, index + 1, array, index, array.length - index - 1);
        array[size - 1] = null;
        size--;
        return removedValue;
    }

    @Override
    public T get(int index) {
        validateIndex(index);
        return array[index];
    }

    @Override
    public T set(T value, int index) {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("Set element in empty list");
        }
        validateIndex(index);
        T oldValue = array[index];
        array[index] = value;
        return oldValue;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
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
        for (int i = 0; i < size; i++) {
            if (Objects.equals(array[i], value)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(T value) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(array[i], value)) {
                return i;
            }
        }
        return -1;
    }

    private void checkInBounds(int index, int endOfBound) {
        if (index < 0 || index > endOfBound) {
            throw new IndexOutOfBoundsException("Element on the index " + index +
                    " , is out of bounds: 0 - " + endOfBound);
        }
    }

    private void validateIndexForAdd(int index) {
        checkInBounds(index, size);
    }

    private void validateIndex(int index) {
        checkInBounds(index, size-1);
    }

    @SuppressWarnings("unchecked")
    private void ensureCapacity() {
        int newSize = 2;
        if (size!=1){
            newSize = (int) (size * LOAD_FACTOR);
        }
        T[] newArray = (T[]) new Object[newSize];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }

    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(", ", "[", "]");
        for (T t : this) {
            stringJoiner.add(t.toString());
        }
        return stringJoiner.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<T> {
        private int currentPosition = -1;

        @Override
        public boolean hasNext() {
            return currentPosition + 1  < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("This element does not exist");
            }
            T result = array[currentPosition + 1];
            currentPosition++;
            return result;
        }

        @Override
        public void remove() {
            if (currentPosition == -1) {
                throw new IllegalStateException("Next method has not yet been called, " +
                        "or the remove method has already been called after the last call to the next method");
            }
            ArrayList.this.remove(currentPosition);
            currentPosition = -1;
        }
    }

}
