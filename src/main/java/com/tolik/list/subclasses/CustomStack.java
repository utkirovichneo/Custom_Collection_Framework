package com.tolik.list.subclasses;

import java.util.EmptyStackException;

public class CustomStack<E> extends CustomVector<E>{

    public CustomStack() {
    }

    public E push(E e) {
        add(e);
        return e;
    }

    public synchronized E pop(){
        E obj;
        int length = size();
            obj = peek();
                removeLast();
        return obj;
    }

    public synchronized E peek() {
        int length = size();

        if (length == 0)
            throw new EmptyStackException();
        return elementData(length - 1);
    }

    public boolean empty() {
        return size() == 0;
    }

    public synchronized int search(Object o) {
        int i = lastIndexOf(o);

        if (i >= 0) {
            return size() - i;
        }
        return -1;
    }
}
