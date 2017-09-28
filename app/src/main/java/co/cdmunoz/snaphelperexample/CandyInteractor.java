package co.cdmunoz.snaphelperexample;

import co.cdmunoz.snaphelperexample.data.DataManager;
import co.cdmunoz.snaphelperexample.data.local.DummyDataHelper;
import co.cdmunoz.snaphelperexample.data.model.Candy;
import io.reactivex.Observable;
import java.util.List;

public class CandyInteractor {

  public Observable<List<Candy>> getCandies(){
    return Observable.just(new DataManager(new DummyDataHelper()).getCandiesList());
  }
}
