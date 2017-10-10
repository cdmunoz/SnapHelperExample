# SnapHelperExample
This project shows how to use a SnapHelper to snap the contents of a recycler view to the start, center or end.

In this way, based on LinearSnapHelper class which always snaps to the center, two additional classes were created in order 
to support snapping both to start and to end. These classes are located in /data/common package and override *calculateDistanceToFinalSnap*
and *findSnapView* to define the snapping behavior. On each class the method *distanceToStart* and *distanceToEnd* were created 
as well respectively.

## How this would like
The first list snaps the list to the start, the second to the center and the third to the end

![Horizontal Snap](https://github.com/cdmunoz/SnapHelperExample/blob/master/RecyclerViewSnapHelperHorizGif.gif)
![Vertical Snap](https://github.com/cdmunoz/SnapHelperExample/blob/master/RecyclerViewSnapHelperVertGif.gif)
