package com.obolonyk.datastructures.map.hashmap;

import com.obolonyk.datastructures.map.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class HashMapTest {

    HashMap<String, Integer> map = new HashMap<>();
    HashMap<Integer, Integer> mapEachBucketHasElements = new HashMap<>();
    HashMap<Integer, Integer> mapFirstBucketEmpty = new HashMap<>();
    HashMap<Integer, Integer> mapOnlyFirstBucketHasElements = new HashMap<>();
    HashMap<Integer, Integer> mapOnlyLastBucketHasElements = new HashMap<>();
    HashMap<Integer, Integer> mapMiddleBucketHasElements = new HashMap<>();
    HashMap<Integer, Integer> emptyMap = new HashMap<>();

    @BeforeEach
    void before() {
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);
        map.put("AA", 4);

        mapEachBucketHasElements.put(5, 0);
        mapEachBucketHasElements.put(10, 0); //
        mapEachBucketHasElements.put(15, 0); //

        mapEachBucketHasElements.put(6, 1); //
        mapEachBucketHasElements.put(11, 1); //
        mapEachBucketHasElements.put(16, 1);

        mapEachBucketHasElements.put(7, 2);
        mapEachBucketHasElements.put(12, 2);
        mapEachBucketHasElements.put(17, 2);

        mapEachBucketHasElements.put(8, 3);
        mapEachBucketHasElements.put(13, 3);
        mapEachBucketHasElements.put(18, 3);

        mapEachBucketHasElements.put(9, 4);
        mapEachBucketHasElements.put(14, 4);
        mapEachBucketHasElements.put(19, 4);


        mapFirstBucketEmpty.put(6, 1);
        mapFirstBucketEmpty.put(11, 1);
        mapFirstBucketEmpty.put(16, 1);

        mapFirstBucketEmpty.put(7, 2);
        mapFirstBucketEmpty.put(12, 2);
        mapFirstBucketEmpty.put(17, 2);

        mapFirstBucketEmpty.put(8, 3);
        mapFirstBucketEmpty.put(13, 3);
        mapFirstBucketEmpty.put(18, 3);

        mapFirstBucketEmpty.put(9, 4);
        mapFirstBucketEmpty.put(14, 4);
        mapFirstBucketEmpty.put(19, 4);


        mapOnlyFirstBucketHasElements.put(5, 0);
        mapOnlyFirstBucketHasElements.put(10, 0);
        mapOnlyFirstBucketHasElements.put(15, 0);


        mapOnlyLastBucketHasElements.put(9, 4);
        mapOnlyLastBucketHasElements.put(14, 4);
        mapOnlyLastBucketHasElements.put(19, 4);


        mapMiddleBucketHasElements.put(6, 1);
        mapMiddleBucketHasElements.put(11, 1);
        mapMiddleBucketHasElements.put(16, 1);

        mapMiddleBucketHasElements.put(7, 2);
        mapMiddleBucketHasElements.put(12, 2);
        mapMiddleBucketHasElements.put(17, 2);

        mapMiddleBucketHasElements.put(8, 3);
        mapMiddleBucketHasElements.put(13, 3);
        mapMiddleBucketHasElements.put(18, 3);
    }

    @Test
    @DisplayName("Test Put And Check Size")
    void testPutAndCheckSize() {
        map.put("D", 4);
        assertEquals(5, map.size());
    }

    @Test
    @DisplayName("Test Put In The Same Bucket And Check Size")
    void testPutInTheSameBucketAndCheckSize() {
        map.put("AAA", 4);
        assertEquals(5, map.size());
    }

    @Test
    @DisplayName("Test Put Duplicate Key And Check Size")
    void testPutDuplicateKeyAndCheckSize() {
        map.put("D", 4);
        assertEquals(5, map.size());
        map.put("D", 8);
        assertEquals(5, map.size());
    }

    @Test
    @DisplayName("Test Put And Check Old Value")
    void testPutAndCheckOldValue() {
        Integer oldValue = map.put("A", 8);
        assertEquals(1, oldValue);
    }

    @Test
    @DisplayName("Test Put New And Check Null")
    void testPutNewAndCheckNull() {
        Object oldValue = map.put("N", 8);
        assertNull(oldValue);
    }

    @Test
    @DisplayName("Test Get And Check Result")
    void testGetAndCheckResult() {
        Integer resultValue = map.get("A");
        assertEquals(1, resultValue);
    }

    @Test
    @DisplayName("Test Get Not Existed Value And Check Null")
    void testGetNotExistedValueAndCheckNull() {
        Object resultValue = map.get("D");
        assertNull(resultValue);
    }

    @Test
    @DisplayName("Test Contains And Return True")
    void testContainsAndReturnTrue() {
        assertTrue(map.containsValue(1));
    }

    @Test
    @DisplayName("Test Contains Not Existed Value And Return False")
    void testContainsNotExistedValueAndReturnFalse() {
        assertFalse(map.containsValue(100));
    }


    @Test
    @DisplayName("Test Remove And Check Size")
    void testRemoveAndCheckSize() {
        map.remove("A");
        assertEquals(3, map.size());
    }

    @Test
    @DisplayName("Test Remove And Check Removed Value")
    void testRemoveAndCheckRemovedValue() {
        Integer removedValue = map.remove("A");
        assertEquals(1, removedValue);
    }

    @Test
    @DisplayName("Test Size On Empty Map")
    void testSizeOnEmptyMap() {
        assertEquals(0, emptyMap.size());
    }

    @Test
    @DisplayName("Test Iterator And Check Size")
    void testIteratorAndCheckSize() {
        Iterator<Map.Entry<String, Integer>> iterator = map.iterator();
        int counter = 0;
        while (iterator.hasNext()) {
            counter++;
            iterator.next();
        }
        assertEquals(map.size(), counter);
    }

    @Test
    @DisplayName("Test Iterator On Map Each Bucket Has Elements And Check Size")
    void testIteratorOnMapEachBucketHasElementsAndCheckSize() {
        Iterator<Map.Entry<Integer, Integer>> iterator = mapEachBucketHasElements.iterator();
        int counter = 0;
        while (iterator.hasNext()) {
            counter++;
            iterator.next();
        }
        assertEquals(mapEachBucketHasElements.size(), counter);
    }

    @Test
    @DisplayName("Test Iterator On Map First Bucket Empty And Check Size")
    void testIteratorOnMapFirstBucketEmptyAndCheckSize() {
        Iterator<Map.Entry<Integer, Integer>> iterator = mapFirstBucketEmpty.iterator();
        int counter = 0;
        while (iterator.hasNext()) {
            counter++;
            iterator.next();
        }
        assertEquals(mapFirstBucketEmpty.size(), counter);
    }

    @Test
    @DisplayName("Test Iterator On Map Only First Bucket Has Elements And Check Size")
    void testIteratorOnMapOnlyFirstBucketHasElementsAndCheckSize() {
        Iterator<Map.Entry<Integer, Integer>> iterator = mapOnlyFirstBucketHasElements.iterator();
        int counter = 0;
        while (iterator.hasNext()) {
            counter++;
            iterator.next();
        }

        assertEquals(mapOnlyFirstBucketHasElements.size(), counter);
    }

    @Test
    @DisplayName("Test Iterator On Map Only Last Bucket Has Elements And Check Size")
    void testIteratorOnMapOnlyLastBucketHasElementsAndCheckSize() {
        Iterator<Map.Entry<Integer, Integer>> iterator = mapOnlyLastBucketHasElements.iterator();
        int counter = 0;
        while (iterator.hasNext()) {
            counter++;
            iterator.next();
        }
        assertEquals(mapOnlyLastBucketHasElements.size(), counter);
    }

    @Test
    @DisplayName("Test Iterator On Map Middle Bucket Has Elements And Check Size")
    void testIteratorOnMapMiddleBucketHasElementsAndCheckSize() {
        Iterator<Map.Entry<Integer, Integer>> iterator = mapMiddleBucketHasElements.iterator();
        int counter = 0;
        while (iterator.hasNext()) {
            counter++;
            iterator.next();
        }
        assertEquals(mapMiddleBucketHasElements.size(), counter);
    }

    @Test
    @DisplayName("Test Iterator Remove And Check IsEmpty")
    void testIteratorRemoveAndCheckIsEmpty() {
        Iterator<Map.Entry<String, Integer>> iterator = map.iterator();
        assertThrows(IllegalStateException.class, () -> {
            while (iterator.hasNext()) {
                iterator.remove();
            }
        });
    }


    @Test
    @DisplayName("Test To String For Map Only First Bucket Has Elements")
    void testToStringForMapOnlyFirstBucketHasElements() {
        assertEquals("[{key=5, value=0}, {key=10, value=0}, {key=15, value=0}]", mapOnlyFirstBucketHasElements.toString());
    }

    @Test
    @DisplayName("Test To String For Full Map")
    void testToStringForFullMap() {
        assertEquals("[{key=A, value=1}, {key=AA, value=4}, {key=B, value=2}, {key=C, value=3}]", map.toString());
    }

    @Test
    @DisplayName("Test To String For Empty Map")
    void testToStringForEmptyMap() {
        assertEquals("[]", emptyMap.toString());
    }

    @Test
    @DisplayName("Test To String For Map With Null")
    void testToStringForMapWithNull() {
        emptyMap.put(null, 1);
        assertEquals("[{key=null, value=1}]", emptyMap.toString());
    }


    @Test
    @DisplayName("Test To String For Map With Null When Rewrite Value")
    void testToStringForMapWithNullWhenRewriteValue() {
        emptyMap.put(null, 1);
        emptyMap.put(null, 2);
        assertEquals("[{key=null, value=2}]", emptyMap.toString());
    }

    @Test
    @DisplayName("Test Put And Check Restructure")
    void testPutAndCheckRestructure() {
        int bucketLength = map.getBucketLength();
        assertEquals(5, bucketLength);
        map.put("D", 5);
        map.put("DD", 6);
        map.put("DDD", 7);
        assertEquals(7, map.size());
        int newBucketLength = map.getBucketLength();
        assertEquals(10, newBucketLength);
    }


    @Test
    @DisplayName("Given Empty Map When Get By Null Key Then Null Should Be Returned")
    void givenEmptyMapWhenGetByNullKeyThenNullShouldBeReturned() {
        Map<String, String> map = new HashMap<>();
        assertNull(map.get(null));
    }

    @Test
    @DisplayName("Given Empty Map When Get By Not Null Key Then Null Should Be Returned")
    void givenEmptyMapWhenGetByNotNullKeyThenNullShouldBeReturned() {
        Map<String, String> map = new HashMap<>();
        assertNull(map.get("key"));
    }

    @Test
    @DisplayName("Given Null Key When Put Once Then Size Should Be Equal To One And Value Should Be Equal To Inserted")
    void givenNullKeyWhenPutOnceThenSizeShouldBeEqualToOneAndValueShouldBeEqualToInserted() {
        Map<String, String> map = new HashMap<>();

        String key = null;
        String value = "test";

        map.put(key, value);

        assertEquals(1, map.size());
        assertEquals(value, map.get(key));
    }

    @Test
    @DisplayName("Given Null Key When Put Multiple Times Then Size Should Be Equal To One And Value Should Be Overwritten With Last")
    void givenNullKeyWhenPutMultipleTimesThenSizeShouldBeEqualToOneAndValueShouldBeOverwrittenWithLast() {
        Map<String, String> map = new HashMap<>();

        String key = null;
        String firstValue = "test1";
        String secondValue = "test2";
        String thirdValue = "test3";

        map.put(key, firstValue);
        map.put(key, secondValue);
        map.put(key, thirdValue);

        assertEquals(1, map.size());
        assertEquals(thirdValue, map.get(key));
    }

    @Test
    @DisplayName("Given Not Null Key When Put Then Size Should Be Equal To One And Value Should Be Equal To Inserted")
    void givenNotNullKeyWhenPutThenSizeShouldBeEqualToOneAndValueShouldBeEqualToInserted() {
        Map<String, String> map = new HashMap<>();

        String key = "key";
        String value = "value";

        map.put(key, value);

        assertEquals(1, map.size());
        assertEquals(value, map.get(key));
    }

    @Test
    @DisplayName("Given Multiple Not Null Keys When Put Then Size Should Be Equal To Size Of Keys And Get By Key Returns Corresponding Value\n")
    void givenMultipleNotNullKeysWhenPutThenSizeShouldBeEqualToSizeOfKeysAndGetByKeyReturnsCorrespondingValue() {
        Map<String, String> map = new HashMap<>();

        String firstKey = "key1";
        String secondKey = "key2";
        String firstValue = "value1";
        String secondValue = "value2";

        map.put(firstKey, firstValue);
        map.put(secondKey, secondValue);

        assertEquals(2, map.size());
        assertEquals(firstValue, map.get(firstKey));
        assertEquals(secondValue, map.get(secondKey));
    }

    @Test
    @DisplayName("Given Multiple Nodes In Same Bucket When Get By Existing Key Then Get By Key Returns Corresponding Value")
    void givenMultipleNodesInSameBucketWhenGetByExistingKeyThenGetByKeyReturnsCorrespondingValue() {
        Map<String, String> map = new HashMap<>();

        String firstKey = "key2"; //same bucket
        String secondKey = "key-1"; //same bucket
        String thirdKey = "key-10"; //same bucket

        String firstValue = "value2";
        String secondValue = "value3";
        String thirdValue = "value4";

        map.put(firstKey, firstValue);
        map.put(secondKey, secondValue);
        map.put(thirdKey, thirdValue);

        assertEquals(3, map.size());

        assertEquals(firstValue, map.get(firstKey));
        assertEquals(secondValue, map.get(secondKey));
        assertEquals(thirdValue, map.get(thirdKey));
    }

    @Test
    @DisplayName("Given Not Existing Key When Get By Key Then Null Should Be Returned")
    void givenNotExistingKeyWhenGetByKeyThenNullShouldBeReturned() {
        Map<String, String> map = new HashMap<>();
        map.put("existing key", "value");
        assertNull(map.get("not existing key"));
    }

    @Test
    @DisplayName("Given Not Null Key When Put Multiple Times With Same Key Then Size Should Be Equal To One And Value Should Be Overwritten With Last")
    void givenNotNullKeyWhenPutMultipleTimesWithSameKeyThenSizeShouldBeEqualToOneAndValueShouldBeOverwrittenWithLast() {
        Map<String, String> map = new HashMap<>();

        String key = "key";
        String firstValue = "test1";
        String secondValue = "test2";
        String thirdValue = "test3";

        map.put(key, firstValue);
        map.put(key, secondValue);
        map.put(key, thirdValue);

        assertEquals(1, map.size());
        assertEquals(thirdValue, map.get(key));
    }

    @Test
    @DisplayName("Given Empty Map When Remove By Null Key Then Size Should Be Equal To Zero")
    void givenEmptyMapWhenRemoveByNullKeyThenSizeShouldBeEqualToZero() {
        Map<String, String> map = new HashMap<>();
        map.remove(null);
        assertEquals(0, map.size());
    }

    @Test
    @DisplayName("Given Empty Map When Remove By Not Null Key Then Size Should Be Equal To Zero")
    void givenEmptyMapWhenRemoveByNotNullKeyThenSizeShouldBeEqualToZero() {
        Map<String, String> map = new HashMap<>();
        map.remove("key");
        assertEquals(0, map.size());
    }

    @Test
    @DisplayName("Given Not Empty Map When Remove By Null Key Then Size Should Be Equal To Zero")
    void givenNotEmptyMapWhenRemoveByNullKeyThenSizeShouldBeEqualToZero() {
        Map<String, String> map = new HashMap<>();
        map.put(null, "value");
        map.remove(null);
        assertEquals(0, map.size());
    }

    @Test
    @DisplayName("Given Not Empty Map With Not Null Key When Put With Null Key And Remove By Null Key Then Size Should Decrease By One")
    void givenNotEmptyMapWithNotNullKeyWhenPutWithNullKeyAndRemoveByNullKeyThenSizeShouldDecreaseByOne() {
        Map<String, String> map = new HashMap<>();
        map.put(null, "value");
        map.put("not null key", "value");

        assertEquals(2, map.size());

        map.remove(null);
        assertEquals(1, map.size());
    }

    @Test
    @DisplayName("Given Empty Map When Remove Then Size Should Be Equal To Zero")
    void givenEmptyMapWhenRemoveThenSizeShouldBeEqualToZero() {
        Map<String, String> map = new HashMap<>();
        map.remove("key");
        assertEquals(0, map.size());
    }

    @Test
    @DisplayName("Given Not Empty Map When Remove Then Size Should Be Equal To Zero")
    void givenNotEmptyMapWhenRemoveThenSizeShouldBeEqualToZero() {
        Map<String, String> map = new HashMap<>();

        map.put("key", "value");
        assertEquals(1, map.size());

        map.remove("key");
        assertEquals(0, map.size());
    }

    @Test
    @DisplayName("Given Not Empty Map When Remove One By One Then Size Should Decrease After Each Removal By One\n")
    void givenNotEmptyMapWhenRemoveOneByOneThenSizeShouldDecreaseAfterEachRemovalByOne() {
        Map<String, String> map = new HashMap<>();

        String firstKey = "key1";
        String secondKey = "key2"; //same bucket
        String thirdKey = "key-1"; //same bucket

        String firstValue = "value1";
        String secondValue = "value2";
        String thirdValue = "value3";

        map.put(firstKey, firstValue);
        map.put(secondKey, secondValue); //case: remove first node
        map.put(thirdKey, thirdValue); //case: remove last node

        assertEquals(3, map.size());

        map.remove(firstKey);
        assertEquals(2, map.size());

        map.remove(secondKey);
        assertEquals(1, map.size());

        map.remove(thirdKey);
        assertEquals(0, map.size());
    }

    @Test
    @DisplayName("Given Not Empty Map And Objects In Same Bucket When Remove First Node Then Size Should Decrease By One")
    void givenNotEmptyMapAndObjectsInSameBucketWhenRemoveFirstNodeThenSizeShouldDecreaseByOne() {
        Map<String, String> map = new HashMap<>();

        String firstKey = "key1";
        String secondKey = "key2"; //same bucket
        String thirdKey = "key-1"; //same bucket
        String fourthKey = "key-10"; //same bucket

        String firstValue = "value1";
        String secondValue = "value2";
        String thirdValue = "value3";
        String fourthValue = "value4";

        map.put(firstKey, firstValue);
        map.put(secondKey, secondValue);
        map.put(thirdKey, thirdValue);
        map.put(fourthKey, fourthValue);

        assertEquals(4, map.size());

        map.remove(secondKey); //case: remove first node
        assertEquals(3, map.size());
    }

    @Test
    @DisplayName("Given Not Empty Map And Objects In Same Bucket When Remove Last Node Then Size Should Decrease By One")
    void givenNotEmptyMapAndObjectsInSameBucketWhenRemoveLastNodeThenSizeShouldDecreaseByOne() {
        Map<String, String> map = new HashMap<>();

        String firstKey = "key1";
        String secondKey = "key2"; //same bucket
        String thirdKey = "key-1"; //same bucket
        String fourthKey = "key-10"; //same bucket

        String firstValue = "value1";
        String secondValue = "value2";
        String thirdValue = "value3";
        String fourthValue = "value4";

        map.put(firstKey, firstValue);
        map.put(secondKey, secondValue);
        map.put(thirdKey, thirdValue);
        map.put(fourthKey, fourthValue);

        assertEquals(4, map.size());

        map.remove(fourthKey); //case: remove first node
        assertEquals(3, map.size());
    }

    @Test
    @DisplayName("Given Not Empty Map And Objects In Same Bucket When Remove Node In The Middle Then Size Should Decrease By One")
    void givenNotEmptyMapAndObjectsInSameBucketWhenRemoveNodeInTheMiddleThenSizeShouldDecreaseByOne() {
        Map<String, String> map = new HashMap<>();

        String firstKey = "key1";
        String secondKey = "key2"; //same bucket
        String thirdKey = "key-1"; //same bucket
        String fourthKey = "key-10"; //same bucket

        String firstValue = "value1";
        String secondValue = "value2";
        String thirdValue = "value3";
        String fourthValue = "value4";

        map.put(firstKey, firstValue);
        map.put(secondKey, secondValue);
        map.put(thirdKey, thirdValue);
        map.put(fourthKey, fourthValue);

        assertEquals(4, map.size());

        map.remove(thirdKey); //case: remove node in the middle
        assertEquals(3, map.size());
    }

    @Test
    @DisplayName("Given Empty Map Not And Not Existing Key When Remove Then Size Should Not Change")
    void givenEmptyMapNotAndNotExistingKeyWhenRemoveThenSizeShouldNotChange() {
        Map<String, String> map = new HashMap<>();
        map.remove("not existing key");
        assertEquals(0, map.size());
    }

    @Test
    @DisplayName("Given Not Empty Map Not And Not Existing Key When Remove Then Size Should Not Change")
    void givenNotEmptyMapNotAndNotExistingKeyWhenRemoveThenSizeShouldNotChange() {
        Map<String, String> map = new HashMap<>();
        map.put("key", "value");
        assertEquals(1, map.size());
        map.remove("not existing key");
        assertEquals(1, map.size());
    }

    @Test
    @DisplayName("Given Empty Map When Iterator Next Then No Such Element Exception Should Be Raised")
    void givenEmptyMapWhenIteratorNextThenNoSuchElementExceptionShouldBeRaised() {
        assertThrows(NoSuchElementException.class, () -> new HashMap<>().iterator().next());

    }

    @Test
    @DisplayName("Given Iterator When Next After Last Element Then No Such Element Exception Should Be Raised")
    void givenIteratorWhenNextAfterLastElementThenNoSuchElementExceptionShouldBeRaised() {
        HashMap<String, String> map = new HashMap<>();

        String key = "key";
        String value = "value";
        map.put(key, value);

        Iterator<Map.Entry<String, String>> iterator = map.iterator();
        Map.Entry<String, String> entry = iterator.next();
        assertEquals(key, entry.getKey());
        assertEquals(value, entry.getValue());

        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    @DisplayName("Given Empty Map When Iterator Has Next Then Should Return False")
    void givenEmptyMapWhenIteratorHasNextThenShouldReturnFalse() {
        HashMap<String, String> map = new HashMap<>();
        assertFalse(map.iterator().hasNext());
    }

    @Test
    @DisplayName("Given Not Empty Map When Iterator Has Next Then Should Return True")
    void givenNotEmptyMapWhenIteratorHasNextThenShouldReturnTrue() {
        HashMap<String, String> map = new HashMap<>();
        map.put("key", "value");
        Iterator<Map.Entry<String, String>> iterator = map.iterator();
        assertTrue(iterator.hasNext());
        assertTrue(iterator.hasNext());
        map.remove("key");
        assertFalse(iterator.hasNext());
        map.put("key", "value");
        assertTrue(iterator.hasNext());
    }

    @Test
    @DisplayName("Given Map With Two Elements When Iterator Next Then Iterator Has Next Should Return False")
    void givenMapWithTwoElementsWhenIteratorNextThenIteratorHasNextShouldReturnFalse() {
        HashMap<String, String> map = new HashMap<>();
        map.put("key", "value");
        map.put("key2", "value");
        Iterator<Map.Entry<String, String>> iterator = map.iterator();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertTrue(iterator.hasNext());
    }

    @Test
    @DisplayName("Given Map With One Element When Iterator Next Then Iterator Has Next Should Return False")
    void givenMapWithOneElementWhenIteratorNextThenIteratorHasNextShouldReturnFalse() {
        HashMap<String, String> map = new HashMap<>();
        map.put("key", "value");
        Iterator<Map.Entry<String, String>> iterator = map.iterator();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertFalse(iterator.hasNext());
    }

    @Test
    @DisplayName("Given Empty Map When Iterator Remove Then No Such Element Exception Should Be Raised")
    void givenEmptyMapWhenIteratorRemoveThenNoSuchElementExceptionShouldBeRaised() {
        Iterator<Map.Entry<Object, Object>> iterator = new HashMap<>().iterator();
        assertThrows(IllegalStateException.class, iterator::remove);
    }

    @Test
    @DisplayName("Given Iterator When Remove Called Without Next Then Illegal State Exception Should Be Raised")
    void givenIteratorWhenRemoveCalledWithoutNextThenIllegalStateExceptionShouldBeRaised() {
        HashMap<String, String> map = new HashMap<>();
        map.put("key", "value");
        assertEquals(1, map.size());
        Iterator<Map.Entry<String, String>> iterator = map.iterator();
        assertThrows(IllegalStateException.class, iterator::remove);
    }

    @Test
    @DisplayName("Given Iterator When Remove Called After Next Then Size Should Be Decreased By One And Map Should Not Contain Key")
    void givenIteratorWhenRemoveCalledAfterNextThenSizeShouldBeDecreasedByOneAndMapShouldNotContainKey() {
        HashMap<String, String> map = new HashMap<>();
        String key = "key";
        map.put(key, "value");
        assertEquals(1, map.size());

        Iterator<Map.Entry<String, String>> iterator = map.iterator();
        iterator.next();
        iterator.remove();

        assertEquals(0, map.size());
    }

    @Test
    @DisplayName("Given Empty Map When Contains Null Key Then False Should Be Returned")
    void givenEmptyMapWhenContainsNullKeyThenFalseShouldBeReturned() {
        Map<String, String> map = new HashMap<>();
        assertFalse(map.containsKey(null));
    }

    @Test
    @DisplayName("Given Empty Map When Contains Not Null Key Then False Should Be Returned")
    void givenEmptyMapWhenContainsNotNullKeyThenFalseShouldBeReturned() {
        Map<String, String> map = new HashMap<>();
        assertFalse(map.containsKey("key"));
    }

    @Test
    @DisplayName("Given Map With Existing Null Key When Contains Null Key Then True Should Be Returned")
    void givenMapWithExistingNullKeyWhenContainsNullKeyThenTrueShouldBeReturned() {
        Map<String, String> map = new HashMap<>();
        map.put(null, "value");

        assertTrue(map.containsKey(null));
    }

    @Test
    @DisplayName("Given Map With Not Existing Null Key When Contains Null Key Then False Should Be Returned")
    void givenMapWithNotExistingNullKeyWhenContainsNullKeyThenFalseShouldBeReturned() {
        Map<String, String> map = new HashMap<>();
        map.put("key", "value");

        assertFalse(map.containsKey(null));
    }

    @Test
    @DisplayName("Given Not Existing Key When Contains Key Then False Should Be Returned")
    void givenNotExistingKeyWhenContainsKeyThenFalseShouldBeReturned() {
        Map<String, String> map = new HashMap<>();
        map.put("key", "value");

        assertTrue(map.containsKey("key"));
    }

    @Test
    @DisplayName("Given Existing Key When Contains Key Then True Should Be Returned")
    void givenExistingKeyWhenContainsKeyThenTrueShouldBeReturned() {
        Map<String, String> map = new HashMap<>();
        map.put("key", "value");

        assertFalse(map.containsKey("not existing key"));
    }

    @Test
    @DisplayName("Given Multiple Nodes In Same Bucket And Existing Key When Contains By Key Then True Should Be Returned")
    void givenMultipleNodesInSameBucketAndExistingKeyWhenContainsByKeyThenTrueShouldBeReturned() {
        Map<String, String> map = new HashMap<>();

        String firstKey = "key1";
        String secondKey = "key2"; //same bucket
        String thirdKey = "key-1"; //same bucket
        String fourthKey = "key-10"; //same bucket

        String firstValue = "value1";
        String secondValue = "value2";
        String thirdValue = "value3";
        String fourthValue = "value4";

        map.put(firstKey, firstValue);
        map.put(secondKey, secondValue);
        map.put(thirdKey, thirdValue);
        map.put(fourthKey, fourthValue);

        assertTrue(map.containsKey(firstKey));
        assertTrue(map.containsKey(secondKey));
        assertTrue(map.containsKey(thirdKey));
        assertTrue(map.containsKey(fourthKey));
    }

    @Test
    @DisplayName("Given Multiple Nodes In Same Bucket And Not Existing Key With Hash Leading To Same Bucket When Contains By Key Then False Should Be Returned")
    void givenMultipleNodesInSameBucketAndNotExistingKeyWithHashLeadingToSameBucketWhenContainsByKeyThenFalseShouldBeReturned() {
        Map<String, String> map = new HashMap<>();

        String firstKey = "key1";
        String secondKey = "key2"; //same bucket
        String thirdKey = "key-1"; //same bucket
        String fourthKey = "key-10"; //same bucket
        String notExistingKeyWithHashLeadingToSameBucket = "key+12";

        String firstValue = "value1";
        String secondValue = "value2";
        String thirdValue = "value3";
        String fourthValue = "value4";

        map.put(firstKey, firstValue);
        map.put(secondKey, secondValue);
        map.put(thirdKey, thirdValue);
        map.put(fourthKey, fourthValue);

        assertTrue(map.containsKey(firstKey));
        assertTrue(map.containsKey(secondKey));
        assertTrue(map.containsKey(thirdKey));
        assertTrue(map.containsKey(fourthKey));

        assertFalse(map.containsKey(notExistingKeyWithHashLeadingToSameBucket));
    }

    @Test
    @DisplayName("Given Iterator When Remove Called After Next Then Size Should Be Decreased By One And Map Should Not Contain This Key")
    void givenIteratorWhenRemoveCalledAfterNextThenSizeShouldBeDecreasedByOneAndMapShouldNotContainThisKey() {
        HashMap<String, String> map = new HashMap<>();
        String key = "key";
        map.put(key, "value");
        assertEquals(1, map.size());

        Iterator<Map.Entry<String, String>> iterator = map.iterator();
        iterator.next();
        iterator.remove();

        assertEquals(0, map.size());
        assertFalse(map.containsKey(key));
    }

}