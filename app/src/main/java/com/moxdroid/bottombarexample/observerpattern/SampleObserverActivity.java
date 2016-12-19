package com.moxdroid.bottombarexample.observerpattern;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.moxdroid.bottombarexample.R;

public class SampleObserverActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_observer);

        MyModel model = new MyModel();
        MyObserver observer = new MyObserver(model);
        // we change the last name of the person, observer will get notified
        for (MyModel.Person person : model.getPersons()) {
            person.setLastName(person.getLastName() + "1");
        }
        // we change the name of the person, observer will get notified
        for (MyModel.Person person : model.getPersons()) {
            person.setFirstName(person.getFirstName() + "1");
        }
    }
}
