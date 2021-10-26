package com.example.mydictionary.presenter

import com.example.mydictionary.view.MainView
import com.example.mydictionary.model.data.AppState

interface BasePresenterInterface<T : AppState, V : MainView> {

        fun attachView(view: V)

        fun detachView(view: V)

        fun getDataFromInteractor(word: String, isOnline: Boolean)

}