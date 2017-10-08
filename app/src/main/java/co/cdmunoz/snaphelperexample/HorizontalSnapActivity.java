package co.cdmunoz.snaphelperexample;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import butterknife.BindView;
import butterknife.ButterKnife;
import co.cdmunoz.snaphelperexample.data.common.SnapToEndHelper;
import co.cdmunoz.snaphelperexample.data.common.SnapToStartHelper;
import co.cdmunoz.snaphelperexample.data.model.Candy;
import co.cdmunoz.snaphelperexample.di.component.ActivityComponent;
import co.cdmunoz.snaphelperexample.di.component.DaggerActivityComponent;
import co.cdmunoz.snaphelperexample.di.module.ActivityModule;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class HorizontalSnapActivity extends AppCompatActivity implements CandyView {

  @BindView(R.id.rootView) ScrollView rootView;
  @BindView(R.id.candiesContainerCenterSnap) RecyclerView recyclerViewCenterSnap;
  @BindView(R.id.candiesContainerStartSnap) RecyclerView recyclerViewStartSnap;
  @BindView(R.id.candiesContainerEndSnap) RecyclerView recyclerViewEndSnap;
  @BindView(R.id.startSnapProgress) ProgressBar startSnapProgress;
  @BindView(R.id.centerSnapProgress) ProgressBar centerSnapProgress;
  @BindView(R.id.endSnapProgress) ProgressBar endSnapProgress;

  CandiesAdapter adapterCenterSnap;
  CandiesAdapter adapterStartSnap;
  CandiesAdapter adapterEndSnap;

  List<Candy> candiesCenterSnap = new ArrayList<>();
  List<Candy> candiesStartSnap = new ArrayList<>();
  List<Candy> candiesEndSnap = new ArrayList<>();

  Snackbar snackbar;
  @Inject CandyPresenter candyPresenter;

  private ActivityComponent activityComponent;

  public ActivityComponent getActivityComponent() {
    if (null == activityComponent) {
      activityComponent =
          DaggerActivityComponent.builder().activityModule(new ActivityModule(this)).build();
    }
    return activityComponent;
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_horizontal_snap);
    ButterKnife.bind(this);

    if (null != getSupportActionBar()) getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    initializeRecyclerViews();
    getActivityComponent().inject(this);
    candyPresenter.getCandies();
  }

  private void initializeRecyclerViews() {
    LinearLayoutManager layoutManagerCenterSnap =
        new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
    recyclerViewCenterSnap.setLayoutManager(layoutManagerCenterSnap);
    adapterCenterSnap = new CandiesAdapter();
    recyclerViewCenterSnap.setAdapter(adapterCenterSnap);
    SnapHelper snapHelperCenter = new LinearSnapHelper();
    snapHelperCenter.attachToRecyclerView(recyclerViewCenterSnap);

    LinearLayoutManager layoutManagerStartSnap =
        new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
    recyclerViewStartSnap.setLayoutManager(layoutManagerStartSnap);
    adapterStartSnap = new CandiesAdapter();
    recyclerViewStartSnap.setAdapter(adapterStartSnap);
    SnapHelper snapHelperStart = new SnapToStartHelper();
    snapHelperStart.attachToRecyclerView(recyclerViewStartSnap);

    LinearLayoutManager layoutManagerEndSnap =
        new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
    recyclerViewEndSnap.setLayoutManager(layoutManagerEndSnap);
    adapterEndSnap = new CandiesAdapter();
    recyclerViewEndSnap.setAdapter(adapterEndSnap);
    SnapHelper snapHelperEnd = new SnapToEndHelper();
    snapHelperEnd.attachToRecyclerView(recyclerViewEndSnap);
  }

  @Override public void showLoading() {
    startSnapProgress.setVisibility(View.VISIBLE);
    centerSnapProgress.setVisibility(View.VISIBLE);
    endSnapProgress.setVisibility(View.VISIBLE);
  }

  @Override public void hideLoading() {
    startSnapProgress.setVisibility(View.GONE);
    centerSnapProgress.setVisibility(View.GONE);
    endSnapProgress.setVisibility(View.GONE);
  }

  @Override public void showCandies(List<Candy> candies) {
    candiesCenterSnap.addAll(candies);
    adapterCenterSnap.setCandies(candiesCenterSnap);
    candiesStartSnap.addAll(candies);
    adapterStartSnap.setCandies(candiesStartSnap);
    candiesEndSnap.addAll(candies);
    adapterEndSnap.setCandies(candiesEndSnap);
  }

  @Override public void showError() {
    snackbar = Snackbar.make(rootView, R.string.error_message, Snackbar.LENGTH_SHORT);
    snackbar.show();
  }

  @Override protected void onDestroy() {
    if (null != candyPresenter) candyPresenter.destroy();
    super.onDestroy();
  }
}
