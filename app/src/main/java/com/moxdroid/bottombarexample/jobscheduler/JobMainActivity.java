package com.moxdroid.bottombarexample.jobscheduler;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.moxdroid.bottombarexample.R;

public class JobMainActivity extends AppCompatActivity
{
    private static int kJobId = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_main);
    }

    public void onClick(View v) {
        Toast.makeText(getApplicationContext(), "Start", Toast.LENGTH_SHORT).show();

        ComponentName mServiceComponent = new ComponentName(this, TestJobService.class);
        JobInfo.Builder builder = new JobInfo.Builder(kJobId++, mServiceComponent);
        //Pass Parameter to Job Scheduler
        PersistableBundle bundle = new PersistableBundle();
        bundle.putString(DataDownloadService.EXTRA_PARAM_URL, DataDownloadService.URL);
        builder.setExtras(bundle)
                .setPersisted(true);
        builder.setPeriodic(60000);//10 Seconds
        //builder.setMinimumLatency(5 * 1000); // wait at least
        //builder.setOverrideDeadline(50 * 1000); // maximum delay
        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED); // require unmetered network
        builder.setRequiresDeviceIdle(true); // device should be idle
        builder.setRequiresCharging(false); // we don't care if the device is charging or not
        JobScheduler jobScheduler = (JobScheduler)getApplication().getSystemService(Context.JOB_SCHEDULER_SERVICE);
        jobScheduler.schedule(builder.build());

    }

    public void cancelAllJobs(View v) {
        Toast.makeText(getApplicationContext(), "Cancel", Toast.LENGTH_SHORT).show();
        JobScheduler tm = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        tm.cancelAll();
    }
}
