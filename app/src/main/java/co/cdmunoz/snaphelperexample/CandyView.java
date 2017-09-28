package co.cdmunoz.snaphelperexample;

import co.cdmunoz.snaphelperexample.data.model.Candy;
import java.util.List;

public interface CandyView {

  void showLoading();
  void hideLoading();
  void showCandies(List<Candy> candies);
  void showError();

}
