package com.tolik.list;

import java.util.*;

public class CustomArrayList<E> implements CustomList<E> {

    private static final int DEFAULT_CAPACITY = 10;

    private static final Object[] EMPTY_ELEMENTDATA = {};

    transient Object[] elements;

    private int size;

    public CustomArrayList(){
        elements = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    public CustomArrayList(int capacity){
        if(capacity > 0){
            elements = new Object[capacity];
        }
        else if (capacity == 0){
            elements = EMPTY_ELEMENTDATA;
        }
        else{
            throw new IllegalArgumentException("Capacity must be greater than 0");
        }
    }

    private void grow(){
        int newCapacity = elements.length + elements.length/2 + 1;
        elements = Arrays.copyOf(elements, newCapacity);
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean add(E e) {
        if(size == elements.length)
            grow();
        elements[size++] = e;
        return true;
    }

    @Override
    public void add(int index, E e) {
        Objects.checkIndex(index, size());
            if(size() + 1 == elements.length)
                grow();
            System.arraycopy(elements, index, elements, index + 1, size() - index);
                elements[index] = e;
                    size++;
    }

    @Override
    public void addFirst(E e) {
        add(0, e);
    }

    @Override
    public void addLast(E e) {
        add(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        Object[] o = c.toArray();
            int newElementSize = o.length;
            if(newElementSize == 0)
                return false;
            while(newElementSize > elements.length - size)
                grow();
            System.arraycopy(o, 0, elements, size, newElementSize);
            size = size + newElementSize;
        return true;
    }

    @Override
    public void clear() {
        for (Object element : elements)
            element = null;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        return elementData(index);
    }

    @Override
    public E getFirst() {
        if(this.size == 0) throw new NoSuchElementException();
        else return elementData(0);
    }

    @Override
    public E getLast(){
        int last = this.size - 1;
        if(last < 0) throw new NoSuchElementException();
        else return elementData(last);
    }

    @SuppressWarnings("unchecked")
    E elementData(int index){
        return (E) elements[index];
    }

    @Override
    public int indexOf(Object o) {
        return indexOfRange(o, 0, size);
    }

    int indexOfRange(Object o, int start, int end) {
        Object[] elements = this.elements;
        if(o == null){
            for (int i = start; i < end; i++) {
                if(elements[i] == null){
                    return i;
                }
            }
        }
        else{
            for (int i = start; i < end; i++) {
                if(o.equals(elements[i])){
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        return lastIndexOfRange(o, 0, size);
    }

    int lastIndexOfRange(Object o, int start, int end) {
        Object[] elements = this.elements;
        if(o == null){
            for (int i = end - 1; i >= start; i--) {
                if(elements[i] == null){
                    return i;
                }
            }
        }
        else {
            for (int i = end - 1; i >= start; i--) {
                if(o.equals(elements[i])){
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public E remove(int index) {
        Objects.checkIndex(index, size);
        var oldValue = elementData(index);
        int newSize = this.size - 1;
        System.arraycopy(elements, index + 1, elements, index, newSize - index);
        elements[this.size - 1] = null;
        size = newSize;
        return (E) oldValue;
    }

    @Override
    public boolean remove(Object o) {
        int i = 0;
        loop: {
            if(o == null){
                for (; i <size; i++) {
                    if(elements[i] == null) break loop;
                }
            }
            else{
                for (; i <size; i++) {
                    if(Objects.equals(o, elements[i])) break loop;
                }
            }
            return false;
        }
            remove(i);
        return true;
    }

    @Override
    public E set(int index, E e) {
        Objects.checkIndex(index, this.size);
            E oldValue = elementData(index);
                this.elements[index] = e;
                    return oldValue;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<E> subList(int fromIndex, int toIndex) {
        var list = new ArrayList<E>();
            for (int i = fromIndex; i < toIndex; i++) {
                list.add((E) elements[i - fromIndex]);
            }
            return list;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(", ", "[", "]");
            for (int i = 0; i < size; i++) {
                joiner.add(elementData(i).toString());
            }
        return joiner.toString();
    }
}