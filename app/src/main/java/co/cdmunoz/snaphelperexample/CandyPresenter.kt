package co.cdmunoz.snaphelperexample

import android.os.Handler
import co.cdmunoz.snaphelperexample.data.model.Candy
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class CandyPresenter(private val view: CandyView?) {
  private val candyInteractor: CandyInteractor

  init {
    this.candyInteractor = CandyInteractor()
  }

  fun getCandies() {
    if (null != view) {
      view.showLoading()
      Handler().postDelayed({
        candyInteractor.candies
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<List<Candy>> {
              override fun onSubscribe(d: Disposable) {

              }

              override fun onNext(candies: List<Candy>) {
                view.hideLoading()
                view.showCandies(candies)
              }

              override fun onError(e: Throwable) {
                view.showError()
              }

              override fun onComplete() {
                view.hideLoading()
              }
            })
      }, 2000)
    }
  }
}
