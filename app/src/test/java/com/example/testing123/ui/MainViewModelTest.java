package com.example.testing123.ui;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.Observer;
import com.example.testing123.data.GreetingDataSource;
import io.reactivex.Observable;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MainViewModelTest {

  @Rule
  public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

  @Mock
  private GreetingDataSource dataSource;

  @Mock
  private Observer<Boolean> loading;

  @Mock
  private Observer<String> greeting;

  @Test
  public void loadGreeting() {
    when(dataSource.getUserGreetings()).thenReturn(Observable.just("hello"));

    MainViewModel viewModel = new MainViewModel(dataSource);

    viewModel.getLoading().observeForever(loading);
    viewModel.getGreeting().observeForever(greeting);

    viewModel.loadGreetings();

    InOrder inOrder = Mockito.inOrder(loading, greeting);
    inOrder.verify(loading).onChanged(true);
    inOrder.verify(loading).onChanged(false);
    inOrder.verify(greeting).onChanged("hello");
  }

  @Test
  public void dispose() {
    when(dataSource.getUserGreetings()).thenReturn(Observable.just("hello"));

    MainViewModel viewModel = new MainViewModel(dataSource);
    assertThat(viewModel.disposables.size(), is(0));

    viewModel.loadGreetings();
    viewModel.loadGreetings();

    assertThat(viewModel.disposables.size(), is(1));
  }

  @Test
  public void onCleared() {
    when(dataSource.getUserGreetings()).thenReturn(Observable.just("hello"));

    MainViewModel viewModel = new MainViewModel(dataSource);
    assertThat(viewModel.disposables.size(), is(0));

    viewModel.loadGreetings();

    viewModel.onCleared();

    assertThat(viewModel.disposables.size(), is(0));
  }
}
