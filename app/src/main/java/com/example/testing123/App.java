package com.example.testing123;

import android.app.Application;
import timber.log.Timber;

public class App extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    Timber.plant(new Timber.DebugTree());
  }
}
