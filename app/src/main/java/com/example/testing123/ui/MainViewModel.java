package com.example.testing123.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import com.example.testing123.data.GreetingDataSource;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class MainViewModel extends ViewModel {

  private final MutableLiveData<Boolean> loading = new MutableLiveData<>();
  private final MutableLiveData<String> greeting = new MutableLiveData<>();
  private final GreetingDataSource dataSource;

  CompositeDisposable disposables = new CompositeDisposable();

  public MainViewModel(GreetingDataSource dataSource) {
    this.dataSource = dataSource;
  }

  public void loadGreetings() {
    disposables.clear();

    loading.setValue(true);

    Disposable d = dataSource.getUserGreetings()
        .doOnNext(__ -> {
          if (loading.getValue()) {
            loading.postValue(false);
          }
        })
        .subscribe(value -> greeting.postValue(value));

    disposables.add(d);
  }

  @Override
  protected void onCleared() {
    disposables.clear();
  }

  public LiveData<Boolean> getLoading() {
    return loading;
  }

  public LiveData<String> getGreeting() {
    return greeting;
  }
}
