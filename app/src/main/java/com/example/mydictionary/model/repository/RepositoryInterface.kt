package com.example.mydictionary.model.repository

import io.reactivex.Observable

interface RepositoryInterface<T> {
    fun getData(word: String): Observable<T>
}