package co.cdmunoz.snaphelperexample.data.common

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearSnapHelper
import android.support.v7.widget.OrientationHelper
import android.support.v7.widget.RecyclerView
import android.view.View


class SnapToEndHelper : LinearSnapHelper() {

  private lateinit var mVerticalHelper: OrientationHelper
  private lateinit var mHorizontalHelper: OrientationHelper

  @Throws(IllegalStateException::class)
  override fun attachToRecyclerView(recyclerView: RecyclerView?) {
    super.attachToRecyclerView(recyclerView)
  }

  override fun calculateDistanceToFinalSnap(layoutManager: RecyclerView.LayoutManager,
      targetView: View): IntArray? {
    val out = IntArray(2)
    //scroll horizontal
    if (layoutManager.canScrollHorizontally()) {
      out[0] = distanceToEnd(targetView, getHorizontalHelper(layoutManager))
    } else {
      out[0] = 0
    }
    //scroll vertical
    if (layoutManager.canScrollVertically()) {
      out[1] = distanceToEnd(targetView, getVerticalHelper(layoutManager))
    } else {
      out[1] = 0
    }
    return out
  }

  private fun getHorizontalHelper(layoutManager: RecyclerView.LayoutManager): OrientationHelper {
    mHorizontalHelper = OrientationHelper.createHorizontalHelper(layoutManager)
    return mHorizontalHelper
  }

  private fun getVerticalHelper(layoutManager: RecyclerView.LayoutManager): OrientationHelper {
    mVerticalHelper = OrientationHelper.createVerticalHelper(layoutManager)
    return mVerticalHelper
  }

  private fun distanceToEnd(targetView: View, helper: OrientationHelper): Int =
      helper.getDecoratedEnd(targetView) - helper.endAfterPadding

  override fun findSnapView(layoutManager: RecyclerView.LayoutManager): View? {
    return if (layoutManager is LinearLayoutManager) {
      if (layoutManager.canScrollHorizontally()) {
        getStartView(layoutManager, getHorizontalHelper(layoutManager))
      } else {
        getStartView(layoutManager, getVerticalHelper(layoutManager))
      }
    } else super.findSnapView(layoutManager)
  }

  private fun getStartView(layoutManager: RecyclerView.LayoutManager,
      helper: OrientationHelper): View? {
    if (layoutManager is LinearLayoutManager) {
      val lastChild = layoutManager.findLastVisibleItemPosition()
      var offset = 1

      if (layoutManager is GridLayoutManager) {
        offset += layoutManager.spanCount - 1
      }

      if (lastChild == RecyclerView.NO_POSITION) {
        return null
      }

      val child = layoutManager.findViewByPosition(lastChild)

      val visibleWidth = helper.getDecoratedEnd(child).toFloat() / helper.getDecoratedMeasurement(
          child)

      // If we're at the start of the list, we shouldn't snap
      // to avoid having the first item not completely visible.
      val startOfList = layoutManager.findFirstCompletelyVisibleItemPosition() == 0

      return if (visibleWidth > 0.5f && !startOfList) {
        child
      } else if (startOfList) {
        null
      } else {
        // If the child wasn't returned, we need to return the previous view
        layoutManager.findViewByPosition(lastChild - offset)
      }
    }
    return null
  }
}
