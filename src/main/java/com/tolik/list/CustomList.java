package com.tolik.list;

import java.util.*;

public interface CustomList<E> {

    boolean add(E e);

    void add(int index, E e);

    void addFirst(E e);

    void addLast(E e);

    boolean addAll(Collection<? extends E> c);

    void clear();

    boolean contains(Object o);

    boolean equals(Object o);

    E get(int index);

    E getFirst();

    E getLast();

    int hashCode();

    int indexOf(Object o);

    boolean isEmpty();

    int lastIndexOf(Object o);

    E remove(int index);

    boolean remove(Object o);

    E set(int index, E e);

    int size();

    List<E> subList(int fromIndex, int toIndex);

    Object[] toArray();
}