package co.cdmunoz.snaphelperexample.di.component;

import co.cdmunoz.snaphelperexample.HorizontalSnapActivity;
import co.cdmunoz.snaphelperexample.VerticalSnapActivity;
import co.cdmunoz.snaphelperexample.di.module.ActivityModule;
import dagger.Component;
import javax.inject.Singleton;

@Singleton @Component(modules = ActivityModule.class) public interface ActivityComponent {

  void inject(HorizontalSnapActivity horizontalSnapActivity);
  void inject(VerticalSnapActivity verticalSnapActivity);
}
