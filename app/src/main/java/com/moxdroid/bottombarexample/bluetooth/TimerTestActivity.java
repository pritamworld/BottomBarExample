package com.moxdroid.bottombarexample.bluetooth;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.moxdroid.bottombarexample.MySampleSingleton;
import com.moxdroid.bottombarexample.R;


public class TimerTestActivity extends AppCompatActivity
{
    static int i;
    private static String TAG = TimerTestActivity.class.getSimpleName();
    TextView txtTimer;
    Button btnTimer;
    Handler bluetoothHandler;
    MySampleSingleton mySampleSingleton;
    //Runnable class
    Runnable runnable = new Runnable()
    {

        @Override
        public void run()
        {
            try
            {
                //do your code here
                setData();
            } catch (Exception e)
            {
                // TODO: handle exception
                Log.d(TAG, "run: ");
            } finally
            {
                //also call the same runnable to call it at regular interval
                bluetoothHandler.postDelayed(this, 1000);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_test);

        mySampleSingleton = MySampleSingleton.getInstance();
        mySampleSingleton.setName("Pritesh");
        mySampleSingleton.setName("Patel");
        mySampleSingleton.setName("Toronto");

        txtTimer = (TextView) findViewById(R.id.txtTimer);
        btnTimer = (Button) findViewById(R.id.btnTimer);

        bluetoothHandler = new Handler();

        ///Set time interval here
        bluetoothHandler.postDelayed(runnable, 1000);

        btnTimer.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Log.d(TAG, "onClick: " + mySampleSingleton.getNameByIndex(1));
            }
        });

    }

    //Write Data Updation code here
    void setData()
    {

        txtTimer.setText("From Timer : " + i);

        if (mySampleSingleton.getNameByIndex(i) != null)
            Log.d(TAG, "setData: " + mySampleSingleton.getNameByIndex(i));
        i++;

    }
}
