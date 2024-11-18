package com.tolik.list;

import java.util.*;

public interface CustomList<E> {

    boolean add(E e);

    void add(int index, E e);

    boolean addAll(Collection<? extends E> c);

    boolean addAll(int index, Collection<? extends E> c);

    void clear();

    boolean contains(Object o);

    boolean containsAll(Collection<?> c);

    boolean equals(Object o);

    E get(int index);

    int hashCode();

    int indexOf(Object o);

    boolean isEmpty();

    Iterator<E> iterator();

    int lastIndexOf(Object o);

    ListIterator<E> listIterator();

    ListIterator<E> listIterator(int index);

    E remove(int index);

    boolean remove(Object o);

    boolean removeAll(Collection<?> c);

//    default void replaceAll(UnaryOperator<E> operator) {}

    boolean retainAll(Collection<?> c);

    E set(int index, E e);

    int size();

//    default void sort(Comparator<? super E> c) {}

//    default Spliterator<E> spliterator() {}

    List<E> subList(int fromIndex, int toIndex);

    Object[] toArray();

    <T> T[] toArray(T[] a);

}
