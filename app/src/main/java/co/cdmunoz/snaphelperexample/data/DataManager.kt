package co.cdmunoz.snaphelperexample.data

import co.cdmunoz.snaphelperexample.data.local.DummyDataHelper
import co.cdmunoz.snaphelperexample.data.model.Candy

class DataManager(private val dummyDataHelper: DummyDataHelper) {

  val candiesList: List<Candy>
    get() = dummyDataHelper.candiesList
}
