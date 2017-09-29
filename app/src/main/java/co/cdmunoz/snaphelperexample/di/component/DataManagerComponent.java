package co.cdmunoz.snaphelperexample.di.component;

import co.cdmunoz.snaphelperexample.CandyInteractor;
import co.cdmunoz.snaphelperexample.di.module.DataManagerModule;
import dagger.Component;
import javax.inject.Singleton;

@Singleton @Component(modules = DataManagerModule.class) public interface DataManagerComponent {

  void inject(CandyInteractor candyInteractor);
}
