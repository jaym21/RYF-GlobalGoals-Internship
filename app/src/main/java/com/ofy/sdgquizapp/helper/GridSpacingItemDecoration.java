package com.ofy.sdgquizapp.helper;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {
    private int spanCount;
    private int spacing;
    public GridSpacingItemDecoration(int columnCnt,int spacing){
        this.spanCount = columnCnt;
        this.spacing = spacing;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {

        int position = parent.getChildAdapterPosition(view);
        int column = position%spanCount;
        /*
        column=0
        position=4
        left = 0*60/3 = 0
        right = 60 - (1)* 60/3 => 60 - 20 = 40
         */

        outRect.left = parent.getPaddingStart()+column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
        outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)

//        if (position >= spanCount) { To get top shadow
            outRect.top = spacing; // item top
        //}
    }
}
