package com.example.testing123.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.testing123.R;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

  private TextView textView;
  private ProgressBar progressBar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Timber.d("We're up and running");

    // For displaying the greeting.
    textView = findViewById(R.id.textView);

    // Provide feedback that the message is loading.
    progressBar = findViewById(R.id.progressBar);
  }
}
