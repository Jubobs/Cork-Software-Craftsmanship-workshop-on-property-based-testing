package com.jubobs.corksoftwarecrafstmanship.propertybasedtesting.workshop;

import java.util.List;

public interface RecentlyUsedList<T> {

    boolean isEmpty();

    int size();

    int capacity();

    void clear();

    void push(T newHead);

    T elementAt(int index);

    List<T> toList();

}
