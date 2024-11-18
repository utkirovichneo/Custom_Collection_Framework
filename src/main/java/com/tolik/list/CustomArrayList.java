package com.tolik.list;

public class CustomArrayList<E>{

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



}