package com.obolonyk.datastructures.list.linkedlist;

import com.obolonyk.datastructures.list.AbstractListTest;
import com.obolonyk.datastructures.list.List;

class LinkedListTest extends AbstractListTest {

    @Override
    protected List<String> getList() {
        return new LinkedList<>();
    }

}