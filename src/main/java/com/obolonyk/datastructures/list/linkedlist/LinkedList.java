package com.obolonyk.datastructures.list.linkedlist;

import com.obolonyk.datastructures.list.List;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.StringJoiner;

public class LinkedList<T> implements List<T> {
    private int size;
    private Node<T> head;
    private Node<T> tail;

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        validateIndexForAdd(index);
        Node<T> newNode = new Node<>(value);
        if (size == 0) {
            tail = head = newNode;
        } else if (index == size) {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        } else if (index == 0) {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        } else {
            Node<T> currentNode = getNode(index);
            Node<T> prevNode = currentNode.prev;
            prevNode.next = newNode;
            newNode.prev = prevNode;
            newNode.next = currentNode;
            currentNode.prev = newNode;
        }
        size++;
    }

    @Override
    public T remove(int index) {
        validateIndex(index);
        Node<T> currentNode = getNode(index);
        removeNode(currentNode);
        return currentNode.value;
    }

    @Override
    public T get(int index) {
        return getNode(index).value;
    }

    @Override
    public T set(T value, int index) {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("Set element in empty list");
        }
        validateIndex(index);
        Node<T> node = getNode(index);
        T oldValue = node.value;
        node.value = value;
        return oldValue;
    }

    @Override
    public void clear() {
        head = tail = null;
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
        Node<T> currentNode = head;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(currentNode.value, value)) {
                return i;
            }
            currentNode = currentNode.next;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(T value) {
        Node<T> currentNode = tail;
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(currentNode.value, value)) {
                return i;
            }
            currentNode = currentNode.prev;
        }
        return -1;
    }

    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(", ", "[", "]");
        for (T value : this) {
            stringJoiner.add(String.valueOf(value));
        }
        return stringJoiner.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<T> {
        private Node<T> nextNode = head;
        private Node<T> currentNode;

        @Override
        public boolean hasNext() {
            return nextNode != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("This element does not exist");
            }
            T value = nextNode.value;
            currentNode = nextNode;
            nextNode = nextNode.next;
            return value;
        }

        @Override
        public void remove() {
            if (currentNode == null) {
                throw new IllegalStateException("Next method has not yet been called, or the remove method has already been called after the last call to the next method");
            } else {
               removeNode(currentNode);
            }
        }
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

    private Node<T> getNode(int index) {
        Node<T> current;
        if (index < (size / 2)) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
        }
        return current;
    }

    private void removeNode(Node<T> currentNode) {
        if (currentNode==head&&currentNode==tail){
            currentNode.value = null;
        } else if (currentNode == head) {
            Node<T> newHead = currentNode.next;
            newHead.prev = null;
            head = newHead;
        } else if (currentNode == tail) {
            Node<T> newTail = currentNode.prev;
            newTail.next = null;
            tail = newTail;
        } else {
            Node<T> prev = currentNode.prev;
            Node<T> next = currentNode.next;
            prev.next = next;
            next.prev = prev;
        }
        size--;
    }

    private static class Node<T> {
        private T value;
        private Node<T> prev;
        private Node<T> next;

        private Node(T value) {
            this.value = value;
        }

    }
}
