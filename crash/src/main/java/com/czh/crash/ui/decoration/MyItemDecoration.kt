package com.czh.crash.ui.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

internal class MyItemDecoration : RecyclerView.ItemDecoration() {

    private val size = 24

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.left = size
        outRect.right = size
        outRect.bottom = size
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = size
        }
    }
}