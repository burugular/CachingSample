package com.example.rao.cachingsample;

import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.rao.cachingsample.App.Constants;
import com.example.rao.cachingsample.Caching.View.FCacheSave;
import com.example.rao.cachingsample.Caching.View.FCacheShow;

public class MainActivity extends AppCompatActivity implements FCacheSave.OnShowCacheResultsListener, FCacheShow.OnSaveCacheListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(null != findViewById(R.id.fragment_container)){
            if(null != savedInstanceState){
                return;
            }

            //create a new fragment
            FCacheSave fCacheSave = new FCacheSave();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.fragment_container, fCacheSave).commit();
        }
    }

    @Override
    public void onShowCacheResults() {
        FCacheShow fCacheShow = new FCacheShow();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fCacheShow).commit();
    }

    @Override
    public void onSaveCache() {
        FCacheSave fCacheSave = new FCacheSave();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fCacheSave).commit();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
