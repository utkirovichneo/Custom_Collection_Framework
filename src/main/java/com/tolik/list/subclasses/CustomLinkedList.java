package com.tolik.list.subclasses;

import com.tolik.list.CustomList;

import java.util.*;

public class CustomLinkedList<E> implements CustomList<E> {

    private Node<E> head;
    private Node<E> tail;
    private int size;

    @Override
    public boolean add(E e) {
        var last = tail;
        var newNode = new Node<>(e);
        tail = newNode;
        if(last == null) {
            head = newNode;
        }
        else{
            last.next = tail;
            newNode.prev = last;
        }
        size++;
        return true;
    }

    @Override
    public void add(int index, E e) {
        Objects.checkIndex(index, size);
        var newNode = new Node<>(e);

        if(index == 0) {
            if(head != null) {
                newNode.next = head;
                head.prev = newNode;
            }
            head = newNode;
            if(tail == null) {
                tail = newNode;
            }
        }

        else if(index < size / 2){
            var current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            newNode.prev = current;
            newNode.next = current.next;
            if(current.prev != null) {
                current.prev.next = newNode;
            }
            else {
                head = newNode;
            }
            current.prev = newNode;
        }
        else{
            var current = tail;
            for (int i = size - 1; i >= index; i--) {
                current = current.prev;
            }
            newNode.prev = current;
            newNode.next = current.next;
            if(current.next != null) {
                current.next.prev = newNode;
            }
            else {
                head = newNode;
            }
            current.next = newNode;
        }
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
        c.forEach(this::add);
        return true;
    }

    @Override
    public void clear() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public boolean contains(Object o) {
        var current = head;
        for (int i = 0; i < size; i++) {
            if(Objects.equals(current.element, o))
                return true;
            current = current.next;
        }
        return false;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
            if(index == 0)
                return head.element;
            else if(index == size - 1)
                return tail.element;
            else if(index < size / 2){
                var current = head;
                for (int i = 0; i < index; i++) {
                    current = current.next;
                }
                return current.element;
            }
            else{
                var current = tail;
                for (int i = size - 1; i > index; i--) {
                    current = current.prev;
                }
                return current.element;
            }
    }

    @Override
    public E getFirst() {
        return get(0);
    }

    @Override
    public E getLast() {
        return get(size - 1);
    }

    @Override
    public int indexOf(Object o) {
        var current = head;
            if(head!= null)
                for (int i = 0; i < size; i++) {
                    if(Objects.equals(current.element, o)){
                        return i;
                    }
                    current = current.next;
                }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        var current = tail;
        if(tail != null){
            for (int i = size - 1; i >= 0; i--) {
                if (Objects.equals(current.element, o)) {
                    return i;
                }
                current = current.prev;
            }
            }
        return -1;
    }

    @Override
    public E remove(int index) {
        Objects.checkIndex(index, size);
            E deletingCurrent = null;
            if(index == 0) {
                deletingCurrent = head.element;
                head = head.next;
                size--;
                return deletingCurrent;
            }
            else if(index <= size/2){
                var current = head;
                for (int i = 0; i < index; i++) {
                    current = current.next;
                }
                deletingCurrent = current.element;
                current.prev.next = current.next;
                size--;
                return deletingCurrent;
            }
            else if(index>size/2 && index != size - 1){
                var current = tail;
                for (int i = size - 1; i > index; i--) {
                    current = current.prev;
                }
                deletingCurrent = current.element;
                current.prev.next = current.next;
                size--;
                return deletingCurrent;
            }
            else{
                deletingCurrent = tail.element;
                tail = tail.prev;
                size--;
                return deletingCurrent;
            }
    }

    @Override
    public boolean remove(Object o) {
        if(head != null){
                var current = head;
            for (int i = 0; i < size; i++) {
                    if(Objects.equals(current.element, o)){
                        if(i == 0)
                            head = current.next;
                        else if(i == size - 1)
                            tail = current.prev;
                        else
                            current.prev.next = current.next;
                        size--;
                        return true;
                    }
                    current = current.next;
            }
        }
        return false;
    }

    @Override
    public E removeFirst() {
        return remove(0);
    }

    @Override
    public E removeLast() {
        return remove(size - 1);
    }

    @Override
    public E set(int index, E e) {
        Objects.checkIndex(index, size);
        E oldValue;
        Node<E> current;
            if(index == 0){
                oldValue = head.element;
                head.element = e;
            }
            else if(index <= size / 2){
                current = head.next;
                for (int i = 1; i < index; i++) {
                    current = current.next;
                }
                oldValue = current.element;
                current.element = e;
            }
            else if(index > size / 2 && index != size - 1){
                System.out.println("Nimadir");
                current = tail;
                for (int i = size - 1; i > index; i--) {
                    current = current.prev;
                }
                oldValue = current.element;
                current.element = e;
            }
            else{
                oldValue = tail.element;
                tail.element = e;
            }
        return oldValue;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<E> subList(int fromIndex, int toIndex) {
        Object[] array = new Object[toIndex - fromIndex];
        for (int i = fromIndex; i < toIndex; i++) {
            array[i - fromIndex] = get(i);
        }
        return (List<E>) List.of(array);
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> current = head;
            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                E element = current.element;
                current = current.next;
                return element;
            }
        };
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(", ", "[", "]");
        var current = this.head;
            for (int i = 0; i < size; i++) {
                joiner.add(current.toString());
                current = current.next;
            }
        return joiner.toString();
    }

    private static class Node<E>
    {
        E element;
        Node<E> prev;
        Node<E> next;

        public Node(E element) {
            this.element = element;
        }

        public Node(E element, Node<E> prev, Node<E> next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }

        @Override
        public String toString() {
            return element.toString();
        }
    }
}