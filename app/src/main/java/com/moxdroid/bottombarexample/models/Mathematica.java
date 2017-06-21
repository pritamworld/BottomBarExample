package com.moxdroid.bottombarexample.models;

/**
 * Created by pritesh.patel on 2017-01-16, 3:45 PM.
 * ADESA, Canada
 */

public interface Mathematica
{
    int add(int a, int b);

    default int multiply(int a, int b) {
        return a * b;
    }

    static int minus(int a, int b) {
        return a - b;
    }
}
