package com.example.mydictionary.presenter

import io.reactivex.Observable

interface BaseInteractorInterface<T> {
    fun getDataFromDataSource(word: String, fromRemoteSource: Boolean): Observable<T>
}