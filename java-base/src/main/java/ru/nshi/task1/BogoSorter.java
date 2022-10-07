package ru.nshi.task1;

public class BogoSorter implements Sorter {
    @Override
    public int[] sort(int[] numbers) {
        if (numbers == null) {
            throw new NullPointerException("Array is null");
        }
        int[] copy = copyArray(numbers);
        while (!isSorted(copy)) {
            shake(copy);
        }
        return copy;
    }

    int[] copyArray(int[] array) {
        int n = array.length;
        int[] copy = new int[n];
        for (int i = 0; i < copy.length; i++) {
            copy[i] = array[i];
        }
        return copy;
    }

    void shake(int[] array) {
        int n = array.length;
        for (int i = 0; i < n; i++) {
            int j = (int) (Math.random() * n);
            int buf = array[i];
            array[i] = array[j];
            array[j] = buf;
        }
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
