package co.cdmunoz.snaphelperexample

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.View
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import co.cdmunoz.snaphelperexample.data.common.SnapToEndHelper
import co.cdmunoz.snaphelperexample.data.common.SnapToStartHelper
import co.cdmunoz.snaphelperexample.data.model.Candy
import java.util.ArrayList

class VerticalSnapActivity : AppCompatActivity(), CandyView {

  companion object {
    val EXTRA_PARAM_SNAP_TYPE = "EXTRA_PARAM_SNAP_TYPE"
  }

  @BindView(R.id.rootView) lateinit var rootView: RelativeLayout
  @BindView(R.id.snapTitle) lateinit var snapTitle: TextView
  @BindView(R.id.candiesContainerSnap) lateinit var recyclerView: RecyclerView
  @BindView(R.id.snapProgress) lateinit var snapProgress: ProgressBar

  lateinit var adapter: CandiesAdapter
  private val candies = ArrayList<Candy>()
  lateinit var snackbar: Snackbar
  private var isTopView: Boolean = false

  lateinit var candyPresenter: CandyPresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_vertical_snap)
    ButterKnife.bind(this)

    if (null != supportActionBar) supportActionBar!!.setDisplayHomeAsUpEnabled(true)

    val extra = intent.getStringExtra(EXTRA_PARAM_SNAP_TYPE)
    if (!TextUtils.isEmpty(extra) && extra.equals("TOP", ignoreCase = true)) {
      isTopView = true
      snapTitle.text = resources.getString(R.string.vertical_snap_top)
    } else {
      isTopView = false
      snapTitle.text = resources.getString(R.string.vertical_snap_bottom)
    }

    initializeRecyclerViews()
    candyPresenter = CandyPresenter(this)
    candyPresenter.getCandies()
  }

  private fun initializeRecyclerViews() {
    val gridLayoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
    recyclerView.layoutManager = gridLayoutManager
    adapter = CandiesAdapter()
    recyclerView.adapter = adapter
    val snapHelper = if (isTopView) SnapToStartHelper() else SnapToEndHelper()
    snapHelper.attachToRecyclerView(recyclerView)
  }

  override fun showLoading() {
    snapProgress.visibility = View.VISIBLE
  }

  override fun hideLoading() {
    snapProgress.visibility = View.GONE
  }

  override fun showCandies(candies: List<Candy>) {
    this.candies.addAll(candies)
    adapter.setCandies(this.candies)
  }

  override fun showError() {
    snackbar = Snackbar.make(rootView, R.string.error_message, Snackbar.LENGTH_SHORT)
    snackbar.show()
  }

  override fun onDestroy() {
    if (null != candyPresenter) candyPresenter.destroy()
    super.onDestroy()
  }
}