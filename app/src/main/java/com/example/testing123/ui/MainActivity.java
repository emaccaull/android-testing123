package com.example.testing123.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.testing123.R;

/**
 * An activity that loads a greeting and displays it to the user.
 */
public class MainActivity extends AppCompatActivity {

  private TextView textView;
  private ProgressBar progressBar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // For displaying the greeting.
    textView = findViewById(R.id.textView);

    // Provide feedback that the message is loading.
    progressBar = findViewById(R.id.progressBar);

    ViewModelFactory factory = new ViewModelFactory(getApplicationContext());
    MainViewModel viewModel = ViewModelProviders.of(this, factory).get(MainViewModel.class);

    viewModel.getLoading().observe(this, loading -> {
      if (loading) {
        textView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
      } else {
        textView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
      }
    });

    viewModel.getGreeting()
        .observe(this, greeting -> textView.setText(greeting));

    viewModel.loadGreetings();
  }
}
