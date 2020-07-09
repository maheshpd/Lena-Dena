package com.example.lenadena.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class LenaViewModel extends AndroidViewModel {
    public LenaViewModel(Application application) {
        super(application);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
