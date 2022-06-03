package com.obolonyk.datastructures.list;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractListTest {

    List<String> listWithElements = getList();
    List<String> listWithNull = getList();
    List<String> emptyList = getList();

    protected abstract List<String> getList();

    @BeforeEach
    void before() {
        listWithElements.add("A");
        listWithElements.add("B");
        listWithElements.add("C");

        listWithNull.add(null);
        listWithNull.add(null);
    }

    @Test
    @DisplayName("Test Add And Check Size")
    void testAddAndCheckSize() {
        assertEquals(3, listWithElements.size());
    }

    @Test
    @DisplayName("Test Add Null And Check Size")
    void testAddNullAndCheckSize() {
        assertEquals(2, listWithNull.size());
    }

    @Test
    @DisplayName("Test Add And Get And Check Size")
    void testAddAndGetAndCheckOrderByGetMethod() {
        String firstValue = listWithElements.get(0);
        String secondValue = listWithElements.get(1);
        assertEquals("A", firstValue);
        assertEquals("B", secondValue);
    }

    @Test
    @DisplayName("Test Add And Get And By Zero Index And Check Size And Order")
    void testAddAndGetAndByZeroIndexAndCheckSizeAndOrder() {
        listWithElements.add("C", 0);
        assertEquals(4, listWithElements.size());
        String firstValue = listWithElements.get(0);
        assertEquals("C", firstValue);
    }

    @Test
    @DisplayName("Test Add By Last Index And Get And Check Size And Order")
    void testAddByLastIndexAndGetAndCheckSizeAndOrder() {
        int size = listWithElements.size();
        listWithElements.add("X", size);
        assertEquals(4, listWithElements.size());
        String addedValue = listWithElements.get(3);

        assertEquals("X", addedValue);
    }

    @Test
    @DisplayName("Test Add By Less Than Zero Index And Check Exception")
    void testAddByLessThanZeroIndexAndCheckException() {
        assertThrows(IndexOutOfBoundsException.class,
                () -> listWithElements.add("C", -1));
    }

    @Test
    @DisplayName("Test Add By Less Than Zero Index And Check Exception Message")
    void testAddByLessThanZeroIndexAndCheckExceptionMessage() {
        try {
            listWithElements.add("C", -1);
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Element on the index " + -1 + " , is out of bounds: 0 - " + listWithElements.size(),
                    e.getMessage());
        }
    }

    @Test
    @DisplayName("Test Add By Greater Than Zero Index And Check Exception")
    void testAddByGreaterThanZeroIndexAndCheckException() {
        assertThrows(IndexOutOfBoundsException.class,
                () -> listWithElements.add("C", 4));
    }

    @Test
    @DisplayName("Test Add By Greater Than Zero Index And Check Exception Message")
    void testAddByGreaterThanZeroIndexAndCheckExceptionMessage() {
        try {
            listWithElements.add("C", 4);
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Element on the index " + 4 + " , is out of bounds: 0 - " + listWithElements.size(),
                    e.getMessage());
        }
    }

    @Test
    @DisplayName("Test Remove And Check Return Value")
    void testRemoveAndCheckReturnValue() {
        String removedValue = listWithElements.remove(0);
        assertEquals(2, listWithElements.size());
        assertEquals("A", removedValue);
    }

    @Test
    @DisplayName("Test Remove By Zero Index And Check Order")
    void testRemoveByZeroIndexAndCheckOrder() {
        listWithElements.remove(0);
        assertEquals(2, listWithElements.size());
        String firstValue = listWithElements.get(0);
        String secondValue = listWithElements.get(1);
        assertEquals("B", firstValue);
        assertEquals("C", secondValue);
        assertEquals("[B, C]", listWithElements.toString());

    }

    @Test
    @DisplayName("Test Remove By Last Index And Check Order")
    void testRemoveByLastIndexAndCheckOrder() {
        int lastIndex = listWithElements.size() - 1;
        listWithElements.remove(lastIndex);
        assertEquals(2, listWithElements.size());
        String firstValue = listWithElements.get(0);
        String secondValue = listWithElements.get(1);
        assertEquals("A", firstValue);
        assertEquals("B", secondValue);
        assertEquals("[A, B]", listWithElements.toString());
    }

    @Test
    @DisplayName("Test Remove By Greater Than Last Index And Check Exception")
    void testRemoveByGreaterThanLastIndexAndCheckException() {
        assertThrows(IndexOutOfBoundsException.class,
                () -> listWithElements.remove(3));
    }

    @Test
    @DisplayName("Test Remove By Greater Than Last Index And Check Exception Message")
    void testRemoveByGreaterThanLastIndexAndCheckExceptionMessage() {
        try {
            listWithElements.remove(3);
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Element on the index " + 3 + " , is out of bounds: 0 - " + (listWithElements.size() - 1),
                    e.getMessage());
        }
    }

    @Test
    @DisplayName("Test Remove On Empty List And Check Exception")
    void testRemoveOnEmptyListAndCheckException() {
        assertThrows(IndexOutOfBoundsException.class,
                () -> emptyList.remove(0));
    }

    @Test
    @DisplayName("Test Remove On Empty List And Check Exception Message")
    void testRemoveOnEmptyListAndCheckExceptionMessage() {
        try {
            emptyList.remove(0);
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Element on the index " + 0 + " , is out of bounds: 0 - " + (emptyList.size() - 1),
                    e.getMessage());
        }
    }

    @Test
    @DisplayName("Test Remove On List After Clear And Check Exception")
    void testRemoveOnListAfterClearAndCheckException() {
        listWithElements.clear();
        assertThrows(IndexOutOfBoundsException.class,
                () -> listWithElements.remove(0));
    }

    @Test
    @DisplayName("Test Remove On List After Clear And Check Exception Message")
    void testRemoveOnListAfterClearAndCheckExceptionMessage() {
        listWithElements.clear();
        try {
            listWithElements.remove(0);
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Element on the index " + 0 + " , is out of bounds: 0 - " + (listWithElements.size() - 1),
                    e.getMessage());
        }
    }

    @Test
    @DisplayName("Test Get Null And Check Result")
    void testGetNullAndCheckResult() {
        emptyList.add("A");
        emptyList.add(null);
        assertNull(emptyList.get(1));
    }

    @Test
    @DisplayName("Test Get When Index Less Than Zero And Check Exception")
    void testGetWhenIndexLessThanZeroAndCheckException() {
        assertThrows(IndexOutOfBoundsException.class,
                () -> listWithElements.remove(-1));
    }

    @Test
    @DisplayName("Test Get When Index Less Than Zero And Check Exception Message")
    void testGetWhenIndexLessThanZeroAndCheckExceptionMessage() {
        try {
            listWithElements.remove(-1);
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Element on the index " + -1 + " , is out of bounds: 0 - " + (listWithElements.size() - 1),
                    e.getMessage());
        }
    }

    @Test
    @DisplayName("Test Get When Index Greater Than Last Index And Check Exception")
    void testGetWhenIndexLessThanLastIndexAndCheckException() {
        assertThrows(IndexOutOfBoundsException.class,
                () -> listWithElements.remove(3));
    }

    @Test
    @DisplayName("Test Get When Index Greater Than Last Index And Check Exception Message")
    void testGetWhenIndexLessThanLastIndexAndCheckExceptionMessage() {
        try {
            listWithElements.remove(3);
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Element on the index " + 3 + " , is out of bounds: 0 - " + (listWithElements.size() - 1),
                    e.getMessage());
        }
    }

    @Test
    @DisplayName("Test Set And Check Returned Value")
    void testSetAndCheckReturnedValue() {
        String returnedValue = listWithElements.set("X", 0);
        assertEquals("A", returnedValue);
    }

    @Test
    @DisplayName("Test Set And Contains And Check Size And Contains Set Value")
    void testSetAndContainsAndCheckSizeAndContainsSetValue() {
        listWithElements.set("X", 0);
        assertEquals(3, listWithElements.size());
        assertTrue(listWithElements.contains("X"));
        assertFalse(listWithElements.contains("A"));
        String value = listWithElements.get(0);
        assertEquals("X", value);
    }

    @Test
    @DisplayName("Test Set To Index Less Than Zero And Check Exception")
    void testSetToIndexLessThanZeroAndCheckException() {
        assertThrows(IndexOutOfBoundsException.class,
                () -> listWithElements.set("X", -1));
    }

    @Test
    @DisplayName("Test Set To Index Less Than Zero And Check Exception Message")
    void testSetToIndexLessThanZeroAndCheckExceptionMessage() {
        try {
            listWithElements.set("X", -1);
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Element on the index " + -1 + " , is out of bounds: 0 - " + (listWithElements.size() - 1),
                    e.getMessage());
        }
    }

    @Test
    @DisplayName("Test Set To Index Greater Than Last Index And Check Exception")
    void testSetToIndexGreaterThanLastIndexAndCheckException() {
        assertThrows(IndexOutOfBoundsException.class,
                () -> listWithElements.set("X", 3));
    }

    @Test
    @DisplayName("Test Set To Index Greater Than Last Index And Check Exception Message")
    void testSetToIndexGreaterThanLastIndexAndCheckExceptionMessage() {
        try {
            listWithElements.set("X", 3);
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Element on the index " + 3 + " , is out of bounds: 0 - " + (listWithElements.size() - 1),
                    e.getMessage());
        }
    }

    @Test
    @DisplayName("Test Set To Empty List And Check Exception")
    void testSetToEmptyListAndCheckException() {
        assertThrows(IndexOutOfBoundsException.class,
                () -> emptyList.set("X", 0));
    }

    @Test
    @DisplayName("Test Set To Empty List And Check Exception Message")
    void testSetToEmptyListAndCheckExceptionMessage() {
        try {
            emptyList.set("X", 0);
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Set element in empty list",
                    e.getMessage());
        }
    }

    @Test
    @DisplayName("Test Clear And Check Size And IsEmpty")
    void testClearAndCheckSizeAndIsEmpty() {
        listWithElements.clear();
        assertEquals(0, listWithElements.size());
        assertTrue(listWithElements.isEmpty());
    }

    @Test
    @DisplayName("Test Size On Empty And Full List")
    void testSizeOnEmptyAndFullList() {
        assertEquals(0, emptyList.size());
        emptyList.add("A");
        emptyList.add("B");
        emptyList.add("C");
        assertEquals(3, emptyList.size());
    }

    @Test
    @DisplayName("Test IsEmpty On New List")
    void testIsEmptyOnNewList() {
        assertTrue(emptyList.isEmpty());
        emptyList.add("A");
        assertFalse(emptyList.isEmpty());
    }

    @Test
    @DisplayName("Test Contains And Return True")
    void testContainsAndReturnTrue() {
        emptyList.add("A");
        assertTrue(emptyList.contains("A"));
    }

    @Test
    @DisplayName("Test Contains And Return False")
    void testContainsAndReturnFalse() {
        assertFalse(emptyList.contains("A"));
        emptyList.add("A");
        assertFalse(emptyList.contains("X"));
    }

    @Test
    @DisplayName("Test IndexOf Exist Element And Return Index")
    void testIndexOfExistElementAndReturnIndex() {
        assertEquals(0, listWithElements.indexOf("A"));
        assertEquals(2, listWithElements.indexOf("C"));
    }

    @Test
    @DisplayName("Test IndexOf Not Exist Element And Return Negative Index")
    void testIndexOfNotExistElementAndReturnNegative() {
        assertEquals(-1, listWithElements.indexOf("X"));
    }

    @Test
    @DisplayName("Test Last IndexOf")
    void testLastIndexOf() {
        listWithElements.add("A");
        assertEquals(3, listWithElements.lastIndexOf("A"));
    }

    @Test
    @DisplayName("Test IndexOf Null")
    void testIndexOfNull() {
        assertEquals(0, listWithNull.indexOf(null));
    }

    @Test
    @DisplayName("Test Ensure Capacity")
    void testEnsureCapacity() {
        for (int i = 0; i < 20; i++) {
            emptyList.add("Aa");
        }
        assertEquals(20, emptyList.size());
    }

    @Test
    @DisplayName("Test Remove Null")
    void testRemoveNull() {
        assertEquals(2, listWithNull.size());
        listWithNull.remove(0);
        assertEquals(1, listWithNull.size());
        listWithNull.remove(0);
        assertTrue(listWithNull.isEmpty());
    }

    @Test
    @DisplayName("Test To String")
    void testToString() {
        assertEquals("[A, B, C]", listWithElements.toString());
    }

    @Test
    @DisplayName("Test Iterator And Check Correctness String Result")
    void testIteratorAndCheckCorrectnessStringResult() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String element : listWithElements) {
            stringBuilder.append(element);
        }
        assertEquals("ABC", stringBuilder.toString());
    }

    @Test
    @DisplayName("Test Iterator Next And Check Returned Value")
    void testIteratorNextAndCheckReturnedValue() {
        Iterator<String> iterator = listWithElements.iterator();
        assertEquals("A", iterator.next());
        assertEquals("B", iterator.next());
        assertEquals("C", iterator.next());
    }

    @Test
    @DisplayName("Test Iterator Has Next On One Element And Check  Tue")
    void testIteratorHasNextOnOneElementAndCheckTue() {
        emptyList.add("A");
        Iterator<String> iterator = emptyList.iterator();
        assertTrue(iterator.hasNext());
    }

    @Test
    @DisplayName("test When Size Is One After Remove Then Is Empty")
    void testWhenSizeIsOneAfterRemoveThenIsEmpty() {
        emptyList.add("A");
        emptyList.remove(0);
        assertTrue(emptyList.isEmpty());
        assertEquals(0, emptyList.size());
    }

    @Test
    @DisplayName("test When Size Is One After Remove Then Size Is Zero")
    void testWhenSizeIsOneAfterRemoveThenSizeIsZero() {
        emptyList.add("A");
        emptyList.remove(0);
        assertEquals(0, emptyList.size());
    }

    @Test
    @DisplayName("test When Add Elements And Then Check Iterator Next")
    void testWhenElementsAndThenCheckIteratorNext() {
        emptyList.add("A");
        emptyList.add("B");
        emptyList.add("C");
        Iterator<String> iterator = emptyList.iterator();
        assertEquals("A", iterator.next());
        assertEquals("B", iterator.next());
        assertEquals("C", iterator.next());
    }

    @Test
    @DisplayName("test When Add Elements Iterator Next And Then Remove Check To String")
    void testWhenAddElementsIteratorNextAndThenRemoveCheckToString() {
        emptyList.add("A");
        emptyList.add("B");
        emptyList.add("C");
        Iterator<String> iterator = emptyList.iterator();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.remove();
        assertEquals("A", emptyList.get(0));
        assertEquals("B", emptyList.get(1));
    }

    @Test
    @DisplayName("test When Add Elements Iterator Next And Then Remove Check To String And Size")
    void testWhenAddElementsIteratorNextAndThenRemoveCheckToStringAndSize() {
        emptyList.add("A");
        emptyList.add("B");
        emptyList.add("C");
        Iterator<String> iterator = emptyList.iterator();
        iterator.next();
        iterator.remove();
        assertEquals("B", emptyList.get(0));
        assertEquals("C", emptyList.get(1));
        assertEquals(2, emptyList.size());
    }

    @Test
    @DisplayName("test Add Elements Iterator Remove And Check Exception")
    void testWhenAddElementsIteratorRemoveAndCheckException() {
        emptyList.add("A");
        emptyList.add("B");
        emptyList.add("C");
        Iterator<String> iterator = emptyList.iterator();
        assertThrows(IllegalStateException.class, iterator::remove);
    }

    @Test
    @DisplayName("test Add Elements Iterator Remove And Check Exception Message")
    void testWhenAddElementsIteratorRemoveAndCheckExceptionMessage() {
        emptyList.add("A");
        emptyList.add("B");
        emptyList.add("C");
        Iterator<String> iterator = emptyList.iterator();
        IllegalStateException illegalStateException = assertThrows(IllegalStateException.class, iterator::remove);
        assertEquals("Next method has not yet been called, or the remove method has already been called after the last call to the next method",
                illegalStateException.getMessage());
    }

    @Test
    @DisplayName("test LastIndexOf With The Same Elements")
    void testLastIndexOfWithTheSameElements() {
        emptyList.add("A");
        emptyList.add("B");
        emptyList.add("C");
        emptyList.add("D");
        emptyList.add("A");
        emptyList.add("E");
        int index = emptyList.lastIndexOf("A");
        assertEquals(4, index);
    }

    @Test
    @DisplayName("test LastIndexOf With The Same Elements Where The Element In The Beginning")
    void testLastIndexOfWithTheSameElementsWhereTheElementInTheBeginning() {
        emptyList.add("A");
        emptyList.add("B");
        emptyList.add("C");
        emptyList.add("D");
        emptyList.add("N");
        emptyList.add("E");
        int index = emptyList.lastIndexOf("A");
        assertEquals(0, index);
    }

    @Test
    @DisplayName("test LastIndexOf With The Same Elements Where The Element In The End")
    void testLastIndexOfWithTheSameElementsWhereTheElementInTheEnd() {
        emptyList.add("E");
        emptyList.add("B");
        emptyList.add("C");
        emptyList.add("D");
        emptyList.add("N");
        emptyList.add("A");
        int index = emptyList.lastIndexOf("A");
        assertEquals(5, index);
    }

    @Test
    @DisplayName("test LastIndexOf With The Same Elements Where The Element In The Middle")
    void testLastIndexOfWithTheSameElementsWhereTheElementInTheMiddle() {
        emptyList.add("E");
        emptyList.add("B");
        emptyList.add("C");
        emptyList.add("A");
        emptyList.add("N");
        emptyList.add("C");
        emptyList.add("F");
        int index = emptyList.lastIndexOf("A");
        assertEquals(3, index);
    }

    @Test
    @DisplayName("Test Remove By Removing By Zero Index")
    void testRemoveByRemovingByZeroIndex() {
        for (int i = 0; i < 5; i++) {
            emptyList.add("A");
        }
        for (int i = 0; i < 5; i++) {
            emptyList.remove(0);
        }
        assertEquals(0, emptyList.size());
        assertTrue(emptyList.isEmpty());
    }

}
