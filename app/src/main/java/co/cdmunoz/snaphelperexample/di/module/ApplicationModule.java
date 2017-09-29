package co.cdmunoz.snaphelperexample.di.module;

import android.app.Application;
import dagger.Module;
import dagger.Provides;

@Module public class ApplicationModule {

  private final Application application;

  public ApplicationModule(Application application) {
    this.application = application;
  }

  @Provides Application provideApplication() {
    return application;
  }
}
