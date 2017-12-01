package com.example.rao.cachingsample.Caching.Presenter;

import android.content.Context;
import android.os.Environment;

import com.example.rao.cachingsample.Caching.View.CacheShowView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Rao on 30/11/2017.
 */

public class CacheShowPresenterImpl implements CacheShowPresenter {

    CacheShowView cacheShowView;
    Context context;

    @Override
    public void setView(CacheShowView cacheShowView, Context context) {
        this.cacheShowView = cacheShowView;
        this.context = context;
    }

    @Override
    public void loadInternalCache() {
        File folder = context.getCacheDir();
        File myFile = new File(folder, "myData1.txt");
        String data = readData(myFile);
        cacheShowView.showMessage(data);
    }

    @Override
    public void loadExternalCache() {
        File folder = context.getExternalCacheDir();
        File myFile = new File(folder, "myData2.txt");
        String data = readData(myFile);
        cacheShowView.showMessage(data);
    }

    @Override
    public void loadPrivateExternalFile() {
        File folder = context.getExternalFilesDir("CachingSample");
        File myFile = new File(folder, "myData3.txt");
        String data = readData(myFile);
        cacheShowView.showMessage(data);
    }

    @Override
    public void loadPublicExternalFile() {
        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File myFile = new File(folder, "myData4.txt");
        String data = readData(myFile);
        cacheShowView.showMessage(data);
    }
    @Override
    public String readData(File myFile) {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(myFile);
            int read = -1;
            StringBuffer stringBuffer = new StringBuffer();
            while((read = fileInputStream.read()) != -1){
                stringBuffer.append((char)read);
            }
            return stringBuffer.toString();
        } catch (FileNotFoundException e) {
                e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if(fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
