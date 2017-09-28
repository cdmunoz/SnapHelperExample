package co.cdmunoz.snaphelperexample

import co.cdmunoz.snaphelperexample.data.model.Candy

interface CandyView {

  fun showLoading()
  fun hideLoading()
  fun showCandies(candies: List<Candy>)
  fun showError()

}
