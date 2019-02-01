package com.example.testing123.data;

import io.reactivex.observers.TestObserver;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.schedulers.TestScheduler;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.concurrent.TimeUnit;

@RunWith(MockitoJUnitRunner.class)
public class GreetingRepositoryTest {

  private TestScheduler testScheduler = new TestScheduler();

  @Before
  public void setUp() {
    RxJavaPlugins.setComputationSchedulerHandler(__ -> testScheduler);
  }

  @After
  public void tearDown() {
    RxJavaPlugins.reset();
  }

  @Test
  public void getUserGreetings() {
    String[] greetings = { "hello", "ciao", "hola" };
    GreetingRepository repository = new GreetingRepository(greetings);

    TestObserver<String> observer = repository.getUserGreetings().test();
    testScheduler.advanceTimeBy(5, TimeUnit.SECONDS);

    observer.assertValue("hello");
    observer.assertValueCount(1);

    testScheduler.advanceTimeBy(2, TimeUnit.SECONDS);

    observer.assertValueCount(1);

    testScheduler.advanceTimeBy(3, TimeUnit.SECONDS);

    observer.assertValueCount(2);
  }
}
