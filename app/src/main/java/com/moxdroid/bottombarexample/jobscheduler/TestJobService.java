package com.moxdroid.bottombarexample.jobscheduler;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;

/**
 * Created by pritesh.patel on 2016-12-15, 11:31 AM.
 * ADESA, Canada
 */

public class TestJobService extends JobService
{
    private static final String TAG = TestJobService.class.getCanonicalName();
    @Override
    public boolean onStartJob(JobParameters jobParameters)
    {
        Log.i(TAG, "on start job: " + jobParameters.getJobId());
        String url = jobParameters.getExtras().getString(RssDownloadService.EXTRA_PARAM_URL);
        RssDownloadService.startActionDownload(TestJobService.this,url);
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters)
    {
        return true;
    }
}
