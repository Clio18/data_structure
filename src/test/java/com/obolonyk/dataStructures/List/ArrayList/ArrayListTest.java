package com.obolonyk.dataStructures.List.ArrayList;

import com.obolonyk.dataStructures.List.AbstractListTest;
import com.obolonyk.dataStructures.List.List;

class ArrayListTest extends AbstractListTest {

    @Override
    protected List<String> getList() {
        return new ArrayList<>();
    }
}