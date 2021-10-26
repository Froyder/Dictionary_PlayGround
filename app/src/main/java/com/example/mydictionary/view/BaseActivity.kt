package com.example.mydictionary.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mydictionary.model.data.AppState
import com.example.mydictionary.presenter.BasePresenterInterface

abstract class BaseActivity<T : AppState> : AppCompatActivity(), MainView {

    protected lateinit var presenter: BasePresenterInterface<T, MainView>

    protected abstract fun createPresenter(): BasePresenterInterface<T, MainView>

    abstract override fun renderData(appState: AppState)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = createPresenter()
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView(this)
    }
}