package co.cdmunoz.snaphelperexample.data.common;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Based on Android's SnapHelper Doc: https://developer.android.com/reference/android/support/v7/widget/SnapHelper.html
 * Code taken from https://blog.mindorks.com/using-snaphelper-in-recyclerview-fc616b6833e8
 * This SnapHelper is customized to snap to the start of the list both horizontally and vertically
 * taking into account approximations in the calculations
 *
 * Note: LinearSnapHelper class already has an implementation by default to snap to the center
 */
public class SnapToStartHelper extends LinearSnapHelper {

  private OrientationHelper mVerticalHelper;
  private OrientationHelper mHorizontalHelper;

  public SnapToStartHelper() {
  }

  @Override public void attachToRecyclerView(@Nullable RecyclerView recyclerView)
      throws IllegalStateException {
    super.attachToRecyclerView(recyclerView);
  }

  @Override
  public int[] calculateDistanceToFinalSnap(@NonNull RecyclerView.LayoutManager layoutManager,
      @NonNull View targetView) {
    int[] out = new int[2];
    //scroll horizontal
    if (layoutManager.canScrollHorizontally()) {
      out[0] = distanceToStart(targetView, getHorizontalHelper(layoutManager));
    } else {
      out[0] = 0;
    }
    //scroll vertical
    if (layoutManager.canScrollVertically()) {
      out[1] = distanceToStart(targetView, getVerticalHelper(layoutManager));
    } else {
      out[1] = 0;
    }
    return out;
  }

  private OrientationHelper getHorizontalHelper(RecyclerView.LayoutManager layoutManager) {
    if (mHorizontalHelper == null) {
      mHorizontalHelper = OrientationHelper.createHorizontalHelper(layoutManager);
    }
    return mHorizontalHelper;
  }

  private OrientationHelper getVerticalHelper(RecyclerView.LayoutManager layoutManager) {
    if (mVerticalHelper == null) {
      mVerticalHelper = OrientationHelper.createVerticalHelper(layoutManager);
    }
    return mVerticalHelper;
  }

  private int distanceToStart(View targetView, OrientationHelper helper) {
    return helper.getDecoratedStart(targetView) - helper.getStartAfterPadding();
  }

  @Override public View findSnapView(RecyclerView.LayoutManager layoutManager) {
    if (layoutManager instanceof LinearLayoutManager) {
      if (layoutManager.canScrollHorizontally()) {
        return getStartView(layoutManager, getHorizontalHelper(layoutManager));
      } else {
        return getStartView(layoutManager, getVerticalHelper(layoutManager));
      }
    }
    return super.findSnapView(layoutManager);
  }

  private View getStartView(RecyclerView.LayoutManager layoutManager, OrientationHelper helper) {
    if (layoutManager instanceof LinearLayoutManager) {
      int firstChild = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
      int offset = 1;

      if (layoutManager instanceof GridLayoutManager) {
        offset += ((GridLayoutManager) layoutManager).getSpanCount() - 1;
      }

      boolean isLastItem =
          ((LinearLayoutManager) layoutManager).findLastCompletelyVisibleItemPosition()
              == layoutManager.getItemCount() - 1;

      if (firstChild == RecyclerView.NO_POSITION || isLastItem) {
        return null;
      }

      View child = layoutManager.findViewByPosition(firstChild);

      if (helper.getDecoratedEnd(child) >= helper.getDecoratedMeasurement(child) / 2
          && helper.getDecoratedEnd(child) > 0) {
        return child;
      } else {
        if (((LinearLayoutManager) layoutManager).findLastCompletelyVisibleItemPosition()
            == layoutManager.getItemCount() - 1) {
          return null;
        } else {
          return layoutManager.findViewByPosition(firstChild + offset);
        }
      }
    }
    return super.findSnapView(layoutManager);
  }
}
