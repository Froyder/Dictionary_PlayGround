package com.example.mydictionary.model.repository

import com.example.mydictionary.DataModel
import com.example.mydictionary.model.datasource.DataSourceInterface
import io.reactivex.Observable

class Repository(private val dataSource: DataSourceInterface<List<DataModel>>) :
    RepositoryInterface<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> {
        return dataSource.getData(word)
    }
}