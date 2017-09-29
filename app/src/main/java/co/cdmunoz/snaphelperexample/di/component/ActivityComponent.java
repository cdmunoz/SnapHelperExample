package co.cdmunoz.snaphelperexample.di.component;

import co.cdmunoz.snaphelperexample.MainActivity;
import co.cdmunoz.snaphelperexample.di.module.ActivityModule;
import dagger.Component;
import javax.inject.Singleton;

@Singleton @Component(modules = ActivityModule.class) public interface ActivityComponent {

  void inject(MainActivity mainActivity);
}
