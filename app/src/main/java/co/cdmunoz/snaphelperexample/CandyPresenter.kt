package co.cdmunoz.snaphelperexample

import android.os.Handler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CandyPresenter constructor(private val view: CandyView?) {

  private val candyInteractor: CandyInteractor = CandyInteractor()
  private val compositeDisposable = CompositeDisposable()

  fun getCandies() {
    if (null != view) {
      view.showLoading()
      Handler().postDelayed({
        compositeDisposable.add(candyInteractor.candies
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { candies ->
              view.hideLoading()
              view.showCandies(candies)
            })
      }, 2000)
    }
  }

  fun destroy() {
    if (!compositeDisposable.isDisposed) {
      compositeDisposable.dispose()
    }
  }
}
