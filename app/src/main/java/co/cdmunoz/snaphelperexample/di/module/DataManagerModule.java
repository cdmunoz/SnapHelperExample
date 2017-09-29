package co.cdmunoz.snaphelperexample.di.module;

import co.cdmunoz.snaphelperexample.data.DataManager;
import co.cdmunoz.snaphelperexample.data.local.DummyDataHelper;
import dagger.Module;
import dagger.Provides;

@Module public class DataManagerModule {

  public DataManagerModule() {
  }

  @Provides DataManager provideDataManager() {
    return new DataManager(new DummyDataHelper());
  }
}
