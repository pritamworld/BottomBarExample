package com.moxdroid.bottombarexample.base;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ToggleButton;

import com.moxdroid.bottombarexample.R;

public class MyMainActivity extends BaseActivity
{
    private static String TAG = MyMainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_main);
        Log.d(TAG, "onCreate: B");
    }

    public void onClick(View view){

        switch (view.getId())
        {
            case R.id.btnShowAlert:
                showAlert("Welcome");
                break;

            case R.id.btnProgressBar:
                if(((ToggleButton)findViewById(R.id.btnProgressBar)).isChecked())
                    showProgress("Loading...");
                else dismissProgress();
                break;

            case R.id.btnToast:
                showToast("Hello Toast");
                break;

        }
    }
}
