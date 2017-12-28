package com.meng.craftsmen.kotlindemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import org.jetbrains.anko.*

/**
 * Created by wangchengm
 * on 2017/12/27.
 */
class FoundActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        relativeLayout {
            textView(intent.extras["key"]?.toString() ?: "hello world") {
                //在lamda表达式中可以直接访问TextView的方法

                gravity = Gravity.CENTER
                textSize = 20f
                lparams(matchParent, matchParent)
                backgroundColor = 990000

                onClick { view ->
                    text = "onclick---hello"
                }
            }
        }
    }
}