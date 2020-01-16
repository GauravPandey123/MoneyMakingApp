package com.android.utils


import android.content.Context
import androidx.annotation.Nullable
import androidx.recyclerview.widget.RecyclerView
import android.util.AttributeSet
import android.view.View


class EmptyRecyclerView : RecyclerView {
    private var mEmptyView: View? = null
    private var emptyCount = 0
    private val observer: AdapterDataObserver = object : AdapterDataObserver() {
        override fun onChanged() {
            super.onChanged()
            initEmptyView()
        }

        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
            super.onItemRangeInserted(positionStart, itemCount)
            initEmptyView()
        }

        override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
            super.onItemRangeRemoved(positionStart, itemCount)
            initEmptyView()
        }
    }

    constructor(context: Context) : super(context)
    constructor(context: Context, @Nullable attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, @Nullable attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )

    private fun initEmptyView() {
        if (mEmptyView != null) {
            mEmptyView!!.visibility =
                if (adapter == null || adapter!!.itemCount == emptyCount) View.VISIBLE else View.GONE
            this@EmptyRecyclerView.visibility =
                if (adapter == null || adapter!!.itemCount == emptyCount) View.GONE else View.VISIBLE
        }
    }

    override fun setAdapter(adapter: RecyclerView.Adapter<*>?) {
        val oldAdapter = getAdapter()
        super.setAdapter(adapter)
        oldAdapter?.unregisterAdapterDataObserver(observer)
        adapter?.registerAdapterDataObserver(observer)
    }

    fun setEmptyView(view: View) {
        this.mEmptyView = view
        initEmptyView()
    }

    fun setEmptyCount(count: Int) {
        this.emptyCount = count
    }
}