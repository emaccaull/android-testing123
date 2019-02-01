package com.example.testing123.ui;

import android.content.Context;
import com.example.testing123.R;
import com.example.testing123.data.GreetingDataSource;
import com.example.testing123.data.GreetingRepository;

public class Injection {

  public static GreetingDataSource provideGreetingDataSource(Context context) {
    return new GreetingRepository(context.getResources().getStringArray(R.array.greetings));
  }
}
