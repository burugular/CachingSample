package com.example.rao.cachingsample.Caching.View;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rao.cachingsample.App.CachingSampleApplication;
import com.example.rao.cachingsample.Caching.Presenter.CacheSavePresenter;
import com.example.rao.cachingsample.Caching.Presenter.CacheSavePresenterImpl;
import com.example.rao.cachingsample.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Rao on 29/11/2017.
 */

public class FCacheSave extends Fragment implements CacheSaveView{

    @Inject
    CacheSavePresenter presenter;
    OnShowCacheResultsListener mListener;

    @BindView(R.id.et_user_name)
    EditText userName;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cache_save_layout, container, false);
        ((CachingSampleApplication)getActivity().getApplication()).getAppComponent().inject(this);
        ButterKnife.bind(this, view);
        presenter.setView(this, getContext());
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (OnShowCacheResultsListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString() + " must implement OnArticleSelectedListener");
        }
    }

    @OnClick(R.id.btn_internal_cache)
    void saveInternalCache(){
        presenter.saveInternalCache(userName.getText().toString());
    }

    @OnClick(R.id.btn_external_cache)
    void saveExternalCache(){
        //Cache dir from the external storage
        presenter.saveExternalCache(userName.getText().toString());
    }

    @OnClick(R.id.btn_external_private)
    void saveExternalPrivate(){
        //storing private files
        presenter.saveExternalPrivate(userName.getText().toString());
    }

    @OnClick(R.id.btn_external_public)
    void saveExternalPublic(){
        presenter.saveExternalPublic(userName.getText().toString());
    }

    @OnClick(R.id.show_cache_results)
    void showCacheResultsOnClick(){
        mListener.onShowCacheResults();
    }

    @Override
    public void showMessage(String message){
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    public interface OnShowCacheResultsListener{
        void onShowCacheResults();
    }
}
