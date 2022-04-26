package com.czh.studyproject.util.rv

import androidx.recyclerview.widget.RecyclerView

abstract class DataSetChangeObserver : RecyclerView.AdapterDataObserver() {

    abstract override fun onChanged()

    final override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
        onChanged()
    }

    final override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
        onChanged()
    }

    final override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
        onChanged()
    }

    final override fun onItemRangeMoved(fromPosition: Int, toPosition: Int, itemCount: Int) {
        onChanged()
    }
}