package co.cdmunoz.snaphelperexample.data.common

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearSnapHelper
import android.support.v7.widget.OrientationHelper
import android.support.v7.widget.RecyclerView
import android.view.View
import android.support.v7.widget.GridLayoutManager



/**
 * Based on Android's SnapHelper Doc: https://developer.android.com/reference/android/support/v7/widget/SnapHelper.html
 * Code taken from https://blog.mindorks.com/using-snaphelper-in-recyclerview-fc616b6833e8
 * This SnapHelper is customized to snap to the star of the list both horizontally and vertically
 * taking into account approximations in the calculations
 *
 * Note: LinearSnapHelper class already has an implementation by default to snap to the center
 */
class SnapToStartHelper : LinearSnapHelper() {

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
      out[0] = distanceToStart(targetView, getHorizontalHelper(layoutManager))
    } else {
      out[0] = 0
    }
    //scroll vertical
    if (layoutManager.canScrollVertically()) {
      out[1] = distanceToStart(targetView, getVerticalHelper(layoutManager))
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

  private fun distanceToStart(targetView: View, helper: OrientationHelper): Int =
      helper.getDecoratedStart(targetView) - helper.startAfterPadding

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
      val firstChild = layoutManager.findFirstVisibleItemPosition()
      val isLastItem = layoutManager.findLastCompletelyVisibleItemPosition() == layoutManager.getItemCount() - 1

      var offset = 1
      if (layoutManager is GridLayoutManager) {
        offset += layoutManager.spanCount - 1
      }

      if (firstChild == RecyclerView.NO_POSITION || isLastItem) {
        return null
      }

      val child = layoutManager.findViewByPosition(firstChild)

      return if (helper.getDecoratedEnd(child) >= helper.getDecoratedMeasurement(
          child) / 2 && helper.getDecoratedEnd(child) > 0) {
        child
      } else {
        if (layoutManager.findLastCompletelyVisibleItemPosition() == layoutManager.getItemCount() - 1) {
          null
        } else {
          layoutManager.findViewByPosition(firstChild + offset)
        }
      }
    }
    return super.findSnapView(layoutManager)
  }
}
