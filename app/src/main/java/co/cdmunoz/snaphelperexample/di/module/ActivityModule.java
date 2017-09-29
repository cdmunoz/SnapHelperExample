package co.cdmunoz.snaphelperexample.di.module;

import co.cdmunoz.snaphelperexample.CandyView;
import dagger.Module;
import dagger.Provides;

@Module public class ActivityModule {

  private final CandyView candyView;

  public ActivityModule(CandyView candyView) {
    this.candyView = candyView;
  }

  @Provides CandyView provideCandyView() {
    return candyView;
  }
}
