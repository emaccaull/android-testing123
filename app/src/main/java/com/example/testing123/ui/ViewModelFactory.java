package com.example.testing123.ui;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import android.support.annotation.NonNull;

public class ViewModelFactory implements ViewModelProvider.Factory {

  private Context context;

  public ViewModelFactory(Context context) {
    this.context = context;
  }

  @NonNull
  @Override
  public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
    //noinspection unchecked
    return (T) new MainViewModel(Injection.provideGreetingDataSource(context));
  }
}
