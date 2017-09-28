package co.cdmunoz.snaphelperexample

import co.cdmunoz.snaphelperexample.data.DataManager
import co.cdmunoz.snaphelperexample.data.local.DummyDataHelper
import co.cdmunoz.snaphelperexample.data.model.Candy
import io.reactivex.Observable

class CandyInteractor {

  val candies: Observable<List<Candy>>
    get() = Observable.just(DataManager(DummyDataHelper()).candiesList)
}
