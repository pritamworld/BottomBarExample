package com.moxdroid.bottombarexample.observerpattern;

/**
 * Created by pritesh.patel on 2016-12-19, 9:36 AM.
 * ADESA, Canada
 */

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class MyModel {
    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";

    private List<Person> persons = new ArrayList<Person>();
    private List<PropertyChangeListener> listener = new ArrayList<PropertyChangeListener>();

    public class Person {

        private String firstName;

        private String lastName;

        public Person(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public String getFirstName() {

            return firstName;
        }

        public void setFirstName(String firstName) {
            notifyListeners(
                    this,
                    FIRST_NAME,
                    this.firstName,
                    this.firstName = firstName);

        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            notifyListeners(
                    this,
                    LAST_NAME,
                    this.lastName,
                    this.lastName = lastName);
        }
    }

    public List<Person> getPersons() {
        return persons;
    }

    public MyModel() {
        // just for testing we hard-code the persons here:
        persons.add(new Person("Pritesh", "Patel"));
        persons.add(new Person("Jigisha", "Patel"));
    }

    private void notifyListeners(Object object, String property, String oldValue, String newValue) {
        for (PropertyChangeListener name : listener) {
            name.propertyChange(new PropertyChangeEvent(this, property, oldValue, newValue));
        }
    }

    public void addChangeListener(PropertyChangeListener newListener) {
        listener.add(newListener);
    }

}