package com.jubobs.corksoftwarecrafstmanship.propertybasedtesting.workshop;

import java.util.List;

public final class ListBasedRecentlyUsedList<T> implements RecentlyUsedList<T> {

    private final int capacity;

    private ListBasedRecentlyUsedList(int capacity) {
        if (capacity < 1) {
            String msg = String.format("nonpositive capacity: %d", capacity);
            throw new IllegalArgumentException(msg);
        }

        this.capacity = capacity;
    }

    public static <T> ListBasedRecentlyUsedList<T> newInstance(int capacity) {
        return new ListBasedRecentlyUsedList<>(capacity);
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public int capacity() {
        return capacity;
    }

    @Override
    public void clear() {

    }

    @Override
    public void push(T newHead) {

    }

    @Override
    public T elementAt(int index) {
        return null;
    }

    @Override
    public List<T> toList() {
        return null;
    }

}

