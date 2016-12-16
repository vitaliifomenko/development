package com.netsuite;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by VitaliiFomenko
 */
public class RemovingDuplicates {

    public static int[] removeDuplicates(int[] values){
        if(values == null || values.length < 2){
            return values;
        }
        int j = 0;
        int i = 1;
        while (i < values.length) {
            if (values[i] == values[j]) {
                i++;
            } else {
                j++;
                values[j] = values[i];
                i++;
            }
        }
        int [] result = new int[j + 1];
        System.arraycopy(values, 0, result, 0, j + 1);
        return result;
    }


    public static void main(String[] args){
        int[] values = new int[] {1, 2, 3, 3, 3, 4, 4, 10, 13, 15, 15, 17};
        System.out.println("Input array: ");
        for(int i : values){
            System.out.format("%d ", i);
        }
        System.out.println("\nRemoved duplicates array: ");
        for(int i : removeDuplicates(values)){
            System.out.format("%d ", i);
        }
        Integer[] immutableValues = new Integer[] {1, 2, 3, 3, 3, 4, 4, 10, 13, 15, 15, 17};
        ArrayIterator<Integer> arrayIterator = new ArrayIterator<>(immutableValues);
        System.out.println("\nRemoved duplicates array without modification source: ");
        while (arrayIterator.hasNext()){
            System.out.print(arrayIterator.next() + " ");
        }
    }

    public static class ArrayIterator<E> implements Iterator<E> {

        private E[] elementData;
        private int currentIndex = 0;
        private E currentElement;

        public ArrayIterator(E[] elements) {
            elementData = elements;
            System.arraycopy(elements, 0, elementData, 0, elements.length);
        }

        @Override
        public boolean hasNext(){
            return currentIndex < elementData.length && elementData[currentIndex] != null;
        }

        @Override
        public E next(){
            if (currentIndex >= elementData.length){
                throw new NoSuchElementException();
            }
            if(currentIndex == 0){
                currentElement = elementData[currentIndex++];
                return currentElement;
            }
            while(hasNext()){
                E element = elementData[currentIndex++];
                if(!element.equals(currentElement)){
                    currentElement = element;
                    return currentElement;
                }
            }
            return currentElement;
        }

    }

}
