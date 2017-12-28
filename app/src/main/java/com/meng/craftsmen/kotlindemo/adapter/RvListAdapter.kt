package com.meng.craftsmen.kotlindemo.adapter

import android.content.Context
import com.meng.craftsmen.kotlindemo.R
import com.meng.craftsmen.kotlindemo.base.BaseAdapter
import com.meng.craftsmen.kotlindemo.base.BaseViewHolder
import com.meng.craftsmen.kotlindemo.bean.ListBean
import kotlinx.android.synthetic.main.item_list.view.*

/**
 * Created by wangchengm
 * on 2017/12/27.
 */
class RvListAdapter(private val mContext: Context, private val mLayout: Int, private val mDatas: List<ListBean>) : BaseAdapter<ListBean>(mContext, mLayout, mDatas) {

    constructor(context: Context, listData: ArrayList<ListBean>) : this(context, R.layout.item_list, listData)


    override fun onChildBindViewHolder(holder: BaseViewHolder, data: ListBean?) {
        //方式1 - 使用base中的方法获取View
//        holder.retrieveView<TextView>(R.id.mTvName).text = data?.name ?: "chenxiaipie"
//        holder.retrieveView<TextView>(R.id.mTvSex).text = data?.sex ?: "woman"
//        holder.retrieveView<TextView>(R.id.mTvAge).text = data?.age?.toString() ?: ""

        //方式2
        holder.itemView.mTvName.text = data?.name ?: "chenxiaopie"
        holder.itemView.mTvSex.text = data?.sex ?: "woman"
        holder.itemView.mTvAge.text = data?.age?.toString() ?: ""
    }
}