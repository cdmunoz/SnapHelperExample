package co.cdmunoz.snaphelperexample.di.component;

import android.app.Application;
import co.cdmunoz.snaphelperexample.CandyApplication;
import co.cdmunoz.snaphelperexample.di.module.ApplicationModule;
import dagger.Component;
import javax.inject.Singleton;

@Singleton @Component(modules = ApplicationModule.class) public interface ApplicationComponent {

  void inject(CandyApplication candyApplication);

  Application getApplication();
}
