package co.cdmunoz.snaphelperexample.data;

import co.cdmunoz.snaphelperexample.data.local.DummyDataHelper;
import co.cdmunoz.snaphelperexample.data.model.Candy;
import java.util.List;

public class DataManager {

  private final DummyDataHelper dummyDataHelper;

  public DataManager(DummyDataHelper dummyDataHelper) {
    this.dummyDataHelper = dummyDataHelper;
  }

  public List<Candy> getCandiesList(){
    return dummyDataHelper.getCandiesList();
  }
}
