package com.example.rao.cachingsample.Caching.View;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rao.cachingsample.App.CachingSampleApplication;
import com.example.rao.cachingsample.App.Constants;
import com.example.rao.cachingsample.Caching.Presenter.CacheShowPresenter;
import com.example.rao.cachingsample.Caching.Presenter.CacheShowPresenterImpl;
import com.example.rao.cachingsample.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.support.v4.content.PermissionChecker.checkSelfPermission;

/**
 * Created by Rao on 29/11/2017.
 */

public class FCacheShow extends Fragment implements CacheShowView {


    @BindView(R.id.et_user_name)
    EditText userName;
    @Inject
    CacheShowPresenter presenter;
    OnSaveCacheListener mListener;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cache_show_layout, container, false);
        ButterKnife.bind(this, view);
        ((CachingSampleApplication)getActivity().getApplication()).getAppComponent().inject(this);
        presenter.setView(this,getContext());
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (OnSaveCacheListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString() + " must implement OnArticleSelectedListener");
        }
    }

    @OnClick(R.id.loadInternalCache)
    void loadInternalCache(){
        presenter.loadInternalCache();
    }

    @OnClick(R.id.loadExternalCache)
    void loadExternalCache(){
       presenter.loadExternalCache();
    }

    @OnClick(R.id.loadPrivateExternalFile)
    void loadPrivateExternalFile(){
       presenter.loadPrivateExternalFile();
    }

    @OnClick(R.id.loadPublicExternalFile)
    void loadPublicExternalFile(){
        if(checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, Constants.MY_PERSMISSION_REQUEST_EXTERNAL_STORAGE );
        }else{
            presenter.loadPublicExternalFile();
        }
    }

    @OnClick(R.id.goToCacheSave)
    void goToCacheSave(){
        mListener.onSaveCache();
    }

    @Override
    public void showMessage(String message) {
        if(null != message){
            userName.setText(message);
        }else{
            userName.setText("No data returned");
        }
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case Constants.MY_PERSMISSION_REQUEST_EXTERNAL_STORAGE:
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    presenter.loadPublicExternalFile();
                }else{
                    showMessage("Permission denied");
                }
                break;
        }
    }

    public interface OnSaveCacheListener{
        void onSaveCache();
    }


}
