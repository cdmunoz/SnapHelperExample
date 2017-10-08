package co.cdmunoz.snaphelperexample;

import android.os.Handler;
import co.cdmunoz.snaphelperexample.data.model.Candy;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import javax.inject.Inject;

public class CandyPresenter {

  private final CandyView view;
  private final CandyInteractor candyInteractor;
  private CompositeDisposable compositeDisposable = new CompositeDisposable();

  @Inject public CandyPresenter(CandyView view) {
    this.view = view;
    this.candyInteractor = new CandyInteractor();
  }

  public void getCandies() {
    if (null != view) {
      view.showLoading();
      new Handler().postDelayed(new Runnable() {
        @Override public void run() {

          compositeDisposable.add(candyInteractor.getCandies()
              .subscribeOn(Schedulers.newThread())
              .observeOn(AndroidSchedulers.mainThread())
              .subscribe(new Consumer<List<Candy>>() {
                @Override public void accept(List<Candy> candies) throws Exception {
                  view.hideLoading();
                  view.showCandies(candies);
                }
              }));

          /*candyInteractor.getCandies()
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
              });*/
        }
      }, 2000);
    }
  }

  public void destroy() {
    if (null != compositeDisposable && !compositeDisposable.isDisposed()) {
      compositeDisposable.dispose();
    }
  }
}
