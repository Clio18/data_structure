package com.obolonyk.datastructures.list.arraylist;

import com.obolonyk.datastructures.list.AbstractListTest;
import com.obolonyk.datastructures.list.List;

class ArrayListTest extends AbstractListTest {

    @Override
    protected List<String> getList() {
        return new ArrayList<>();
    }
}