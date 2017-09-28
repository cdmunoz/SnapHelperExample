package co.cdmunoz.snaphelperexample

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearSnapHelper
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SnapHelper
import android.view.View
import android.widget.ProgressBar
import android.widget.ScrollView
import butterknife.BindView
import butterknife.ButterKnife
import co.cdmunoz.snaphelperexample.data.common.SnapToEndHelper
import co.cdmunoz.snaphelperexample.data.common.SnapToStartHelper
import co.cdmunoz.snaphelperexample.data.model.Candy
import java.util.ArrayList

class MainActivity : AppCompatActivity(), CandyView {

  @BindView(R.id.rootView) internal var rootView: ScrollView? = null
  @BindView(
      R.id.candiesContainerCenterSnap) internal var recyclerViewCenterSnap: RecyclerView? = null
  @BindView(R.id.candiesContainerStartSnap) internal var recyclerViewStartSnap: RecyclerView? = null
  @BindView(R.id.candiesContainerEndSnap) internal var recyclerViewEndSnap: RecyclerView? = null
  @BindView(R.id.startSnapProgress) internal var startSnapProgress: ProgressBar? = null
  @BindView(R.id.centerSnapProgress) internal var centerSnapProgress: ProgressBar? = null
  @BindView(R.id.endSnapProgress) internal var endSnapProgress: ProgressBar? = null

  internal var adapterCenterSnap: CandiesAdapter
  internal var adapterStartSnap: CandiesAdapter
  internal var adapterEndSnap: CandiesAdapter

  internal var candiesCenterSnap: MutableList<Candy> = ArrayList()
  internal var candiesStartSnap: MutableList<Candy> = ArrayList()
  internal var candiesEndSnap: MutableList<Candy> = ArrayList()

  internal var snackbar: Snackbar
  internal var candyPresenter: CandyPresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    ButterKnife.bind(this)

    initializeRecyclerViews()
    candyPresenter = CandyPresenter(this)
    candyPresenter.getCandies()
  }

  private fun initializeRecyclerViews() {
    val layoutManagerCenterSnap = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    recyclerViewCenterSnap!!.layoutManager = layoutManagerCenterSnap
    adapterCenterSnap = CandiesAdapter()
    recyclerViewCenterSnap!!.adapter = adapterCenterSnap
    val snapHelperCenter = LinearSnapHelper()
    snapHelperCenter.attachToRecyclerView(recyclerViewCenterSnap)

    val layoutManagerStartSnap = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    recyclerViewStartSnap!!.layoutManager = layoutManagerStartSnap
    adapterStartSnap = CandiesAdapter()
    recyclerViewStartSnap!!.adapter = adapterStartSnap
    val snapHelperStart = SnapToStartHelper()
    snapHelperStart.attachToRecyclerView(recyclerViewStartSnap)

    val layoutManagerEndSnap = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    recyclerViewEndSnap!!.layoutManager = layoutManagerEndSnap
    adapterEndSnap = CandiesAdapter()
    recyclerViewEndSnap!!.adapter = adapterEndSnap
    val snapHelperEnd = SnapToEndHelper()
    snapHelperEnd.attachToRecyclerView(recyclerViewEndSnap)

  }

  override fun showLoading() {
    startSnapProgress!!.visibility = View.VISIBLE
    centerSnapProgress!!.visibility = View.VISIBLE
    endSnapProgress!!.visibility = View.VISIBLE
  }

  override fun hideLoading() {
    startSnapProgress!!.visibility = View.GONE
    centerSnapProgress!!.visibility = View.GONE
    endSnapProgress!!.visibility = View.GONE
  }

  override fun showCandies(candies: List<Candy>) {
    candiesCenterSnap.addAll(candies)
    adapterCenterSnap.setCandies(candiesCenterSnap)
    candiesStartSnap.addAll(candies)
    adapterStartSnap.setCandies(candiesStartSnap)
    candiesEndSnap.addAll(candies)
    adapterEndSnap.setCandies(candiesEndSnap)
  }

  override fun showError() {
    snackbar = Snackbar.make(rootView!!, R.string.error_message, Snackbar.LENGTH_SHORT)
    snackbar.show()
  }

}
