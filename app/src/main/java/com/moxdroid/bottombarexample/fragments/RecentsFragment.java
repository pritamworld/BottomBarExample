package com.moxdroid.bottombarexample.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.moxdroid.bottombarexample.R;

import cz.msebera.android.httpclient.Header;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 */
public class RecentsFragment extends Fragment
{

    WebView wv;
    ProgressDialog pd;
    private final String TAG = RecentsFragment.class.getSimpleName();
int a;
    public RecentsFragment()
    {
        // Required empty public constructor
        a=10;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_recents, container, false);
        wv = (WebView)view.findViewById(R.id.myWebView);
        pd = new ProgressDialog(getActivity());
        pd.setTitle("Loading...");
        pd.setCancelable(false);
        pd.setIndeterminate(true);
        Log.d(TAG, "onCreateView: " + a);

        AsyncHttpClient client = new AsyncHttpClient();

        client.get("https://www.google.com", new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
                // called before request is started
                Log.d(TAG, "onStart: ");
                pd.show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                // called when response HTTP status is "200 OK"
                Log.d(TAG, "onSuccess: " + new String(response));
                wv.loadData(new String(response),"text/html","UTF8");
                try
                {
                    Thread.sleep(5000);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                pd.hide();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                Log.d(TAG, "onFailure: ");
                pd.hide();
            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
                Log.d(TAG, "onRetry: ");
            }
            
        });
        return view;
    }



    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);

    }

    @Override
    public void onDetach()
    {
        super.onDetach();
    }


}
