package com.czq.kotlin_arch.basePage.base

import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.czq.kotlin_arch.component.cover.CoverFrameLayout
import kotlinx.android.synthetic.main.activity_base.*

abstract class BaseActivity<T:IBasePrensenter> : AppCompatActivity(), IBaseView {

    open lateinit var mPresenter: T

    override fun getContext(): Context {
        return this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!needTitle()) {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
        }else{
            getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        }
        setContentView(getLayoutId())
        initView()
        mPresenter = createPresenter()
        mPresenter.start()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.getItemId() == android.R.id.home){
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    open fun needTitle(): Boolean {
        return true
    }

    abstract fun createPresenter(): T

    abstract fun getLayoutId(): Int

    open fun initView() {
        title = "BaseActivity"
        coverLayout?.coverFrameListener = object : CoverFrameLayout.CoverFrameListener {
            override fun onReload() {
                mPresenter?.start()
            }
        }

    }


    override fun showContent() {
        coverLayout.showContent()
    }

    override fun showLoading() {
        coverLayout.showLoading()
    }

    override fun showEmpty() {
        coverLayout.showEmpty()
    }

    override fun showError() {
        coverLayout.showError()
    }


}