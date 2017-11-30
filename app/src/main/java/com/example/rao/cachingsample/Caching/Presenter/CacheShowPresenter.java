package com.example.rao.cachingsample.Caching.Presenter;

import android.content.Context;

import com.example.rao.cachingsample.Caching.View.CacheShowView;

import java.io.File;

/**
 * Created by Rao on 30/11/2017.
 */

public interface CacheShowPresenter {
    void setView(CacheShowView cacheShowView, Context context);
    void loadInternalCache();
    void loadExternalCache();
    void loadPrivateExternalFile();
    void loadPublicExternalFile();
    String readData(File myFile);
}
