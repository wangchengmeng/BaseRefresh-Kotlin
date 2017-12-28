package com.meng.craftsmen.kotlindemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.onClick
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        initListener()
    }

    private fun initListener() {
        mBtnJump2Main.onClick { view ->
            Toast.makeText(this, "点击进入了", Toast.LENGTH_LONG).show()
            start2Main()
        }

        mBtnJump2List.onClick { view ->
            startActivity<RvCommonListActivity>()
        }
    }

    private fun initViews() {
        setContent("Hello-World")
        mBtnJump2Main.text = "点击进入"
    }

    private fun setContent(content: String) {
        mTvContent.text = content
    }

    private fun start2Main() {
        startActivity<FoundActivity>("key" to "from mainactivity")
    }
}
