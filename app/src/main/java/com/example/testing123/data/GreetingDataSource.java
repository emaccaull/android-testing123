package com.example.testing123.data;

import io.reactivex.Observable;

public interface GreetingDataSource {

  Observable<String> getUserGreetings();
}
