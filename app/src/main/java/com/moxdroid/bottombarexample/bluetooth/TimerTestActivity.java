package com.moxdroid.bottombarexample.bluetooth;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.moxdroid.bottombarexample.R;


public class TimerTestActivity extends AppCompatActivity
{
    private static String TAG = TimerTestActivity.class.getSimpleName();
    TextView txtTimer;
    Button btnTimer;

    Handler bluetoothHandler;
    static int i;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_test);

        txtTimer = (TextView)findViewById(R.id.txtTimer);
        btnTimer = (Button)findViewById(R.id.btnTimer);

        bluetoothHandler = new Handler();

        ///Set time interval here
        bluetoothHandler.postDelayed(runnable, 1000);

    }

    //Runnable class
    Runnable runnable = new Runnable() {

        @Override
        public void run() {
            try{
                //do your code here
                setData();
            }
            catch (Exception e) {
                // TODO: handle exception
                Log.d(TAG, "run: ");
            }
            finally{
                //also call the same runnable to call it at regular interval
                bluetoothHandler.postDelayed(this, 1000);
            }
        }
    };

    //Write Data Updation code here
    void setData(){
        i++;
        txtTimer.setText("From Timer : " + i);
    }
}
