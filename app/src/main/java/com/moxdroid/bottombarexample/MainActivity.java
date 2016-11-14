package com.moxdroid.bottombarexample;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.moxdroid.bottombarexample.fragments.ExploreFragment;
import com.moxdroid.bottombarexample.fragments.FavoritesFragment;
import com.moxdroid.bottombarexample.fragments.RecentsFragment;

public class MainActivity extends AppCompatActivity
{

    FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.container,new RecentsFragment())
                .commit();

        BottomNavigationView bottomNavigation =
                (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                handleBottomNavigationItemSelected(item);
                return true;
            }
        });
    }

    private void handleBottomNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_recents:
                switchFragment(new RecentsFragment());
                break;
            case R.id.action_favorites:
                switchFragment(new FavoritesFragment());
                break;
            case R.id.action_explore:
                switchFragment(new ExploreFragment());
                break;
        }
    }

    private void switchFragment(Fragment fragment){
        fragmentManager.beginTransaction()
                .replace(R.id.container,fragment)
                .commit();
    }
}
