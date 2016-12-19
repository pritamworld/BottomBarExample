package com.moxdroid.bottombarexample.observerpattern;


import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Created by pritesh.patel on 2016-12-19, 9:38 AM.
 * ADESA, Canada
 */

public class MyObserver implements PropertyChangeListener
{
    public MyObserver(MyModel model) {
        model.addChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent)
    {
        System.out.println("Changed property: " + propertyChangeEvent.getPropertyName() + " [old -> "
                + propertyChangeEvent.getOldValue() + "] | [new -> " + propertyChangeEvent.getNewValue() +"]");
    }
}
