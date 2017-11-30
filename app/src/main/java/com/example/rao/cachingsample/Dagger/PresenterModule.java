package com.example.rao.cachingsample.Dagger;

import com.example.rao.cachingsample.Caching.Presenter.CacheSavePresenter;
import com.example.rao.cachingsample.Caching.Presenter.CacheSavePresenterImpl;
import com.example.rao.cachingsample.Caching.Presenter.CacheShowPresenter;
import com.example.rao.cachingsample.Caching.Presenter.CacheShowPresenterImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Rao on 30/11/2017.
 */

@Module
public class PresenterModule {
    @Provides
    @Singleton
    CacheSavePresenter providesCacheSavePresenter(){
        return new CacheSavePresenterImpl();
    }

    @Provides
    @Singleton
    CacheShowPresenter providesCacheShowPresenter(){
        return new CacheShowPresenterImpl();
    }
}
