package com.meng.craftsmen.kotlindemo.base

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by wangchengm
 * on 2017/12/28.
 *
 * Adapter封装
 */
abstract class BaseAdapter<T>() : RecyclerView.Adapter<BaseViewHolder>() {

    enum class ITEM_TYPE {
        HEADER, FOOTER, NORMAL
    }

    //头部
    private var mHeaderView: View? = null
    //尾部
    private var mFooterView: View? = null

    private lateinit var mContext: Context
    private var mLayoutID: Int = 0
    private lateinit var mDatas: List<T>

    constructor(context: Context, layout: Int, datas: List<T>) : this() {
        mContext = context
        mLayoutID = layout
        mDatas = datas
    }


    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        if (hasHeader() && 0 == position) {
            return
        }
        var count = mDatas.size
        if (hasHeader()) {
            count++
        }
        if (hasFooter()) {
            count++
        }
        if (hasFooter() && position == count - 1) {
            return
        }
        if (hasHeader()) {
            onChildBindViewHolder(holder, getData(position - 1))
        } else {
            onChildBindViewHolder(holder, getData(position))
        }
    }

    fun addFooter(view: View) {
        if (!hasFooter()) {
            mFooterView = view
        }
    }

    fun removeFooter() {
        if (hasFooter()) {
            mFooterView = null
        }
    }

    fun addHeader(view: View) {
        if (!hasHeader()) {
            mHeaderView = view
        }
    }

    fun removeHeader() {
        if (hasHeader()) {
            mHeaderView = null
        }
    }

    override fun getItemViewType(position: Int): Int {
        var count: Int = mDatas.size
        if (hasHeader()) {
            count++
        }
        //表达式
        return if (hasHeader() && 0 == position) {
            BaseAdapter.ITEM_TYPE.HEADER.ordinal
        } else if (hasFooter() && position == count) {
            BaseAdapter.ITEM_TYPE.FOOTER.ordinal
        } else {
            BaseAdapter.ITEM_TYPE.NORMAL.ordinal
        }
    }

    fun hasFooter(): Boolean = null != mFooterView

    fun hasHeader(): Boolean = null != mHeaderView

    /**
     * 获取item数据
     */
    fun getData(position: Int): T? = mDatas[position]

    override fun getItemCount(): Int {
        var count: Int = mDatas.size
        if (hasHeader()) {
            count++
        }
        if (hasFooter()) {
            count++
        }
        return count
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseViewHolder =
            when (viewType) {
                BaseAdapter.ITEM_TYPE.HEADER.ordinal -> BaseViewHolder(mContext, mHeaderView)
                BaseAdapter.ITEM_TYPE.FOOTER.ordinal -> BaseViewHolder(mContext, mFooterView)
                else -> BaseViewHolder(mContext, LayoutInflater.from(mContext).inflate(mLayoutID, parent, false))
            }


    protected abstract fun onChildBindViewHolder(holder: BaseViewHolder, data: T?)
}