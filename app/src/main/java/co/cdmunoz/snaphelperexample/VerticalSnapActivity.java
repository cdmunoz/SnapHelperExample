package co.cdmunoz.snaphelperexample;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
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

public class VerticalSnapActivity extends AppCompatActivity implements CandyView {

  public static final String EXTRA_PARAM_SNAP_TYPE = "EXTRA_PARAM_SNAP_TYPE";

  @BindView(R.id.rootView) RelativeLayout rootView;
  @BindView(R.id.snapTitle) TextView snapTitle;
  @BindView(R.id.candiesContainerSnap) RecyclerView recyclerView;
  @BindView(R.id.snapProgress) ProgressBar snapProgress;

  private CandiesAdapter adapter;
  private List<Candy> candies = new ArrayList<>();
  private Snackbar snackbar;
  private boolean isTopView;
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
    setContentView(R.layout.activity_vertical_snap);
    ButterKnife.bind(this);

    if (null != getSupportActionBar()) getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    String extra = getIntent().getStringExtra(EXTRA_PARAM_SNAP_TYPE);
    if(!TextUtils.isEmpty(extra) && extra.equalsIgnoreCase("TOP")){
      isTopView = true;
      snapTitle.setText(getResources().getString(R.string.vertical_snap_top));
    }else {
      isTopView = false;
      snapTitle.setText(getResources().getString(R.string.vertical_snap_bottom));
    }

    initializeRecyclerViews();
    getActivityComponent().inject(this);
    candyPresenter.getCandies();
  }

  private void initializeRecyclerViews() {
    GridLayoutManager gridLayoutManager =
        new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
    recyclerView.setLayoutManager(gridLayoutManager);
    adapter = new CandiesAdapter();
    recyclerView.setAdapter(adapter);
    SnapHelper snapHelper = isTopView ? new SnapToStartHelper() : new SnapToEndHelper();
    snapHelper.attachToRecyclerView(recyclerView);
  }

  @Override public void showLoading() {
    snapProgress.setVisibility(View.VISIBLE);
  }

  @Override public void hideLoading() {
    snapProgress.setVisibility(View.GONE);
  }

  @Override public void showCandies(List<Candy> candies) {
    this.candies.addAll(candies);
    adapter.setCandies(this.candies);
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
