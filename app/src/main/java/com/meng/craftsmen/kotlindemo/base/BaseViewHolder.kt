package com.meng.craftsmen.kotlindemo.base

import android.content.Context
import android.support.annotation.IdRes
import android.support.v7.widget.RecyclerView
import android.util.SparseArray
import android.view.View
import android.widget.TextView
import org.jetbrains.anko.find

/**
 * Created by wangchengm
 * on 2017/12/28.
 * BaseViewHolder封装
 */
open class BaseViewHolder(item: View?) : RecyclerView.ViewHolder(item) {

    private lateinit var mContext: Context
    private lateinit var mContentView: View
    private lateinit var mCacheViews: SparseArray<View>

    constructor(context: Context, item: View?) : this(item) {
        mContext = context
        mContentView = item ?: View(context)
        mCacheViews = SparseArray()
    }

    /**
     * 根据viewID获取对应的View
     */
    fun <T : View> retrieveView(@IdRes viewID: Int): T {
        var view = mCacheViews.get(viewID)

        if (null == view) {
            view = mContentView.find(viewID)
            mCacheViews.put(viewID, view)
        }
        return view as T
    }

    fun setText(@IdRes viewID: Int, text: String) {
        val textView = retrieveView<TextView>(viewID)
        textView.text = text
    }

    fun setVisiable(viewID: Int, visiable: Boolean) {
        val view = retrieveView<View>(viewID)
        view.visibility = if (visiable) View.VISIBLE else View.GONE
    }

}