package com.example.rao.cachingsample.Dagger;

import com.example.rao.cachingsample.Caching.View.FCacheSave;
import com.example.rao.cachingsample.Caching.View.FCacheShow;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Rao on 30/11/2017.
 */
    @Singleton
    @Component(modules = {AppModule.class, PresenterModule.class})
    public interface AppComponent {
        void inject(FCacheSave target);
        void inject(FCacheShow target);
    }
