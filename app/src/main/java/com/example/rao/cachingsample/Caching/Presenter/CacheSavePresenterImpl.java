package com.example.rao.cachingsample.Caching.Presenter;

import android.content.Context;
import android.os.Environment;

import com.example.rao.cachingsample.Caching.View.CacheSaveView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Rao on 30/11/2017.
 */

public class CacheSavePresenterImpl implements CacheSavePresenter {

    CacheSaveView cacheSaveView;
    Context context;
    @Override
    public void setView(CacheSaveView cacheSaveView, Context context) {
        this.cacheSaveView = cacheSaveView;
        this.context = context;
    }

    @Override
    public void saveInternalCache(String data) {
        File folder = context.getCacheDir();
        File myFile = new File(folder, "myData1.txt");
        writeData(myFile, data);
    }

    @Override
    public void saveExternalCache(String data) {
        File folder = context.getExternalCacheDir();
        File myFile = new File(folder, "myData2.txt");
        writeData(myFile, data);
    }

    @Override
    public void saveExternalPrivate(String data) {
        File folder = context.getExternalFilesDir("CachingSample");//custom type
        File myFile = new File(folder, "myData3.txt");
        writeData(myFile, data);
    }

    @Override
    public void saveExternalPublic(String data) {
        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File myFile = new File(folder, "myData4.txt");
        folder.mkdirs();
        writeData(myFile, data);
    }

    @Override
    public void writeData(File myFile, String data) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(myFile);
            fileOutputStream.write(data.getBytes());
            cacheSaveView.showMessage(data + " was written successfully "+ myFile.getAbsolutePath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(null != fileOutputStream) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
