package com.moxdroid.bottombarexample.jobscheduler;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class RssDownloadService extends IntentService
{
    private static final String TAG = RssDownloadService.class.getCanonicalName();
    private static final String ACTION_RSS_DOWNLOAD = "com.moxdroid.bottombarexample.jobscheduler.action.START_DOWNLOAD";
    public static final String EXTRA_PARAM_URL = "com.moxdroid.bottombarexample.jobscheduler.extra.URL";
    public static final String URL ="http://www.google.com/";

    public RssDownloadService()
    {
        super("RssDownloadService");
    }

    public static void startActionDownload(Context context, String param1)
    {
        Intent intent = new Intent(context, RssDownloadService.class);
        intent.setAction(ACTION_RSS_DOWNLOAD);
        intent.putExtra(EXTRA_PARAM_URL, param1);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent)
    {
        if (intent != null)
        {
            final String action = intent.getAction();
            if (ACTION_RSS_DOWNLOAD.equals(action))
            {
                final String param1 = intent.getStringExtra(EXTRA_PARAM_URL);
                handleActionDownload(param1);
            }
        }
    }

    private void handleActionDownload(String param1)
    {
        Log.d(TAG, "handleActionDownload: Download Started");
        Log.d(TAG, "handleActionDownload: " + param1);
    }
}
