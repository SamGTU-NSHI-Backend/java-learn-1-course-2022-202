package ru.nshi.task1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class SorterTest {
    public static Stream<Arguments> sorters() {
//      ToDo: Stream.of(Arguments.of(new SortImpl()),
//       Arguments.of(new SortOtherImpl()));
        return Stream.of(Arguments.of(new SorterImpl()));
    }

    @ParameterizedTest
    @MethodSource("sorters")
    void simpleTest(Sorter sorter) {
        int[] array = new int[]{5, 6, 2, 1};
        int[] actual = sorter.sort(array);
        Assertions.assertNotSame(array, actual);
        Assertions.assertTrue(isSorted(actual));
    }

    @ParameterizedTest
    @MethodSource("sorters")
    void emptyTest(Sorter sorter) {
        int[] array = new int[0];
        int[] actual = sorter.sort(array);
        Assertions.assertNotSame(array, actual);
    }

    @ParameterizedTest
    @MethodSource("sorters")
    void nullTest(Sorter sorter) {
        int[] array = null;
        Assertions.assertThrows(NullPointerException.class, () -> sorter.sort(array));
    }

    boolean isSorted(int[] array) {
        int n = array.length;
        for (int i = 1; i < n; i++) {
            if (array[i] < array[i - 1]) {
                return false;
            }
        }
        return true;
    }
}