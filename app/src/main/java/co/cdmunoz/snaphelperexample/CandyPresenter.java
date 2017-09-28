package co.cdmunoz.snaphelperexample;

import android.os.Handler;
import co.cdmunoz.snaphelperexample.data.model.Candy;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.util.List;

public class CandyPresenter {

  private final CandyView view;
  private final CandyInteractor candyInteractor;

  public CandyPresenter(CandyView view) {
    this.view = view;
    this.candyInteractor = new CandyInteractor();
  }

  public void getCandies() {
    if (null != view) {
      view.showLoading();
      new Handler().postDelayed(new Runnable() {
        @Override public void run() {
          candyInteractor.getCandies()
              .subscribeOn(Schedulers.newThread())
              .observeOn(AndroidSchedulers.mainThread())
              .subscribe(new Observer<List<Candy>>() {
                @Override public void onSubscribe(Disposable d) {

                }

                @Override public void onNext(List<Candy> candies) {
                  view.hideLoading();
                  view.showCandies(candies);
                }

                @Override public void onError(Throwable e) {
                  view.showError();
                }

                @Override public void onComplete() {
                  view.hideLoading();
                }
              });
        }
      }, 2000);
    }
  }
}
