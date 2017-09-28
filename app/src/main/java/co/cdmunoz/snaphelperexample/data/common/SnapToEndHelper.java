package co.cdmunoz.snaphelperexample.data.common;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class SnapToEndHelper extends LinearSnapHelper {

  private OrientationHelper mVerticalHelper;
  private OrientationHelper mHorizontalHelper;

  public SnapToEndHelper() {
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
      out[0] = distanceToEnd(targetView, getHorizontalHelper(layoutManager));
    } else {
      out[0] = 0;
    }
    //scroll vertical
    if (layoutManager.canScrollVertically()) {
      out[1] = distanceToEnd(targetView, getVerticalHelper(layoutManager));
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

  private int distanceToEnd(View targetView, OrientationHelper helper) {
    return helper.getDecoratedEnd(targetView) - helper.getEndAfterPadding();
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
      int lastChild = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
      int offset = 1;
      boolean isLastItem =
          ((LinearLayoutManager) layoutManager).findLastCompletelyVisibleItemPosition()
              == layoutManager.getItemCount() - 1;

      if (lastChild == RecyclerView.NO_POSITION/* || isLastItem*/) {
        return null;
      }

      View child = layoutManager.findViewByPosition(lastChild);

      /*if (helper.getDecoratedEnd(child) >= helper.getDecoratedMeasurement(child) / 2
          && helper.getDecoratedEnd(child) > 0) {
        return child;
      } else {
        if (((LinearLayoutManager) layoutManager).findLastCompletelyVisibleItemPosition()
            == layoutManager.getItemCount() - 1) {
          return null;
        } else {
          return layoutManager.findViewByPosition(lastChild + 1);
        }
      }
    }
    return super.findSnapView(layoutManager);*/
      float visibleWidth;

      if (/*isRtlHorizontal*/true) {
        visibleWidth = (float) helper.getDecoratedEnd(child)
            / helper.getDecoratedMeasurement(child);
      } else {
        visibleWidth = (float) (helper.getTotalSpace() - helper.getDecoratedStart(child))
            / helper.getDecoratedMeasurement(child);
      }

      // If we're at the start of the list, we shouldn't snap
      // to avoid having the first item not completely visible.
      boolean startOfList = ((LinearLayoutManager) layoutManager)
          .findFirstCompletelyVisibleItemPosition() == 0;

      if (visibleWidth > 0.5f && !startOfList) {
        return child;
      } /*else if (snapLastItem && startOfList) {
        return child;
      }*/ else if (startOfList) {
        return null;
      } else {
        // If the child wasn't returned, we need to return the previous view
        return layoutManager.findViewByPosition(lastChild - offset);
      }
    }
    return null;
  }
}
