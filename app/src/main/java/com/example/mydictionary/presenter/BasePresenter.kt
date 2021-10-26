package com.example.mydictionary.presenter

import com.example.mydictionary.view.MainView
import com.example.mydictionary.model.data.AppState
import com.example.mydictionary.model.datasource.RetrofitDataBase
import com.example.mydictionary.model.datasource.RoomDataBase
import com.example.mydictionary.model.repository.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class BasePresenter<T : AppState, V : MainView>(
    private val interactor: BaseInteractor = BaseInteractor(
        Repository(RetrofitDataBase()), Repository(RoomDataBase())
    ),
    protected val compositeDisposable: CompositeDisposable = CompositeDisposable(),
) : BasePresenterInterface<T, V> {

    private var currentView: V? = null

    override fun attachView(view: V) {
        if (view != currentView) {
            currentView = view
        }
    }

    override fun detachView(view: V) {
        compositeDisposable.clear()
        if (view == currentView) {
            currentView = null
        }
    }

    override fun getDataFromInteractor(word: String, isOnline: Boolean) {
        compositeDisposable.add(
            interactor.getDataFromDataSource(word, isOnline)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

                .doOnSubscribe { currentView?.renderData(AppState.Loading(null)) }
                .subscribeWith(getObserver())
        )
    }

    private fun getObserver(): DisposableObserver<AppState> {
        return object : DisposableObserver<AppState>() {

            override fun onNext(appState: AppState) {
                currentView?.renderData(appState)
            }

            override fun onError(e: Throwable) {
                currentView?.renderData(AppState.Error(e))
            }

            override fun onComplete() {
            }
        }
    }
}