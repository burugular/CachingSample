package com.example.rao.cachingsample.App;

import android.app.Application;

import com.example.rao.cachingsample.Dagger.AppComponent;
import com.example.rao.cachingsample.Dagger.AppModule;
import com.example.rao.cachingsample.Dagger.DaggerAppComponent;

/**
 * Created by Rao on 30/11/2017.
 */

public class CachingSampleApplication extends Application {
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = initDagger(this);
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    protected AppComponent initDagger(CachingSampleApplication application){
        return DaggerAppComponent.builder()
                .appModule(new AppModule(application))
                .build();
    }
}
