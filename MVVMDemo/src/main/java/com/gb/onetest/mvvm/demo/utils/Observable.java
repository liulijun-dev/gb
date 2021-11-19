package com.gb.onetest.mvvm.demo.utils;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Observable<T> implements PropertyChangeListener {
    private final T property;
    private PropertyChangeSupport listeners = new PropertyChangeSupport(this);

    public Observable(T property) {
        this.property = property;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Object oldValue = evt.getOldValue();
        Object newValue = evt.getNewValue();
        onValueChanged(oldValue==null ? null : (T)oldValue, newValue==null ? null : (T)newValue);
    }

    public void onValueChanged(T oldValue, T newValue) {}
}
