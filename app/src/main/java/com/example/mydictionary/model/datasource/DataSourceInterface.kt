package com.example.mydictionary.model.datasource

import io.reactivex.Observable

interface DataSourceInterface<T> {
    fun getData(word: String): Observable<T>
}