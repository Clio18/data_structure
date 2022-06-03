package com.obolonyk.datastructures.list.arraylist;

import com.obolonyk.datastructures.list.AbstractListTest;
import com.obolonyk.datastructures.list.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ArrayListTest extends AbstractListTest {

    @Override
    protected List<String> getList() {
        return new ArrayList<>();
    }

    @Test
    @DisplayName("test EnsureCapacity For Capacity One")
    void testEnsureCapacityForCapacityOne() {
        List<String> list = new ArrayList<>(1);
        list.add("A");
        list.add("A");

        assertEquals(2, list.size());

    }
}