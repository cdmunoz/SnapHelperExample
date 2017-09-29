package co.cdmunoz.snaphelperexample;

import co.cdmunoz.snaphelperexample.data.DataManager;
import co.cdmunoz.snaphelperexample.data.model.Candy;
import co.cdmunoz.snaphelperexample.di.component.DaggerDataManagerComponent;
import co.cdmunoz.snaphelperexample.di.module.DataManagerModule;
import io.reactivex.Observable;
import java.util.List;
import javax.inject.Inject;

public class CandyInteractor {

  @Inject DataManager dataManager;

  @Inject public CandyInteractor() {
    DaggerDataManagerComponent.builder()
        .dataManagerModule(new DataManagerModule())
        .build()
        .inject(this);
  }

  public Observable<List<Candy>> getCandies() {
    return Observable.just(dataManager.getCandiesList());
  }
}
