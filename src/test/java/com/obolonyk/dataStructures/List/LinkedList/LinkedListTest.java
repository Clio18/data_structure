package com.obolonyk.dataStructures.List.LinkedList;

import com.obolonyk.dataStructures.List.AbstractListTest;
import com.obolonyk.dataStructures.List.List;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTest extends AbstractListTest {

    @Override
    protected List<String> getList() {
        return new LinkedList<>();
    }
}