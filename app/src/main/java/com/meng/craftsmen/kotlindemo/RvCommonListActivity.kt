package com.meng.craftsmen.kotlindemo

import android.os.Bundle
import android.os.SystemClock
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.meng.craftsmen.kotlindemo.adapter.RvListAdapter
import com.meng.craftsmen.kotlindemo.bean.ListBean
import kotlinx.android.synthetic.main.activity_rv_list.*
import kotlinx.android.synthetic.main.item_footer.view.*
import kotlinx.android.synthetic.main.item_header.view.*

/**
 * Created by wangchengm
 * on 2017/12/27.
 */
class RvCommonListActivity : AppCompatActivity() {

    private lateinit var mListDatas: ArrayList<ListBean>
    private lateinit var mRvAdapter: RvListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rv_list)

        initVariables()
        initViews()
    }

    private fun initVariables() {
        mListDatas = ArrayList()
        mRvAdapter = RvListAdapter(this, mListDatas)
        addData(0)
    }

    private fun addData(status: Int) {
        if (0 == status) {
            //下拉刷新
            mListDatas.clear()
        }
        (0..50).map {
            ListBean("wangchengmeng$it", "man", 22 + it)
        }.forEach {
            mListDatas.add(it)
        }
        mRvAdapter.notifyDataSetChanged()
    }

    private fun initViews() {
        mRvCommonList.layoutManager = LinearLayoutManager(this)

        //添加head footer
        val footer = View.inflate(this, R.layout.item_footer, null)
        val header = View.inflate(this, R.layout.item_header, null)

        footer.mTvFooter.layoutParams.width = 1080
        footer.mTvFooter.layoutParams.height = 200

        header.mTvHeader.layoutParams.width = 1080
        header.mTvHeader.layoutParams.height = 200

        mRvAdapter.addFooter(footer)
        mRvAdapter.addHeader(header)

        mRvCommonList.adapter = mRvAdapter

        mSwipeRefresh.setOnRefreshListener {
            //模拟刷新
            Thread(Runnable {
                SystemClock.sleep(3000)
                runOnUiThread({
                    mSwipeRefresh.isRefreshing = false
                })
            }).start()
        }

        //上啦加载更多
        mRvCommonList.setOnTouchListener { _, _ ->

            if (!mRvCommonList.canScrollVertically(1)) {
                mSwipeRefresh.isRefreshing = true
                Log.d("aaa", "上啦加载")
                //模拟刷新
                Thread(Runnable {
                    SystemClock.sleep(3000)
                    runOnUiThread({
                        addData(1)
                        mSwipeRefresh.isRefreshing = false
                    })
                }).start()
            }
            false
        }
    }
}