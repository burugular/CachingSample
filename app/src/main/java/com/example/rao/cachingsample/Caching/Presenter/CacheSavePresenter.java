package com.example.rao.cachingsample.Caching.Presenter;

import android.app.Activity;
import android.content.Context;

import com.example.rao.cachingsample.Caching.View.CacheSaveView;

import java.io.File;

/**
 * Created by Rao on 30/11/2017.
 */

public interface CacheSavePresenter {
    void setView(CacheSaveView cacheSaveView, Context context, Activity activity);
    void saveInternalCache(String data);
    void saveExternalCache(String data);
    void saveExternalPrivate(String data);
    void saveExternalPublic(String data);
    void writeData(File myFile, String data);
}
