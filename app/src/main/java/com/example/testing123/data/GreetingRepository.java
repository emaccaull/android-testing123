package com.example.testing123.data;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class GreetingRepository implements GreetingDataSource {

  private final String[] greetings;

  public GreetingRepository(String[] greetings) {
    this.greetings = greetings;
  }

  @Override
  public Observable<String> getUserGreetings() {
    Observable<String> g = Observable.fromArray(greetings);
    Observable<Long> timer = Observable.interval(5, TimeUnit.SECONDS);

    return Observable.zip(g, timer, (n, i) -> n);
  }
}
