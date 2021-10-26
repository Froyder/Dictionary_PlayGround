package com.example.mydictionary.presenter

import com.example.mydictionary.DataModel
import com.example.mydictionary.model.data.AppState
import com.example.mydictionary.model.repository.RepositoryInterface
import io.reactivex.Observable

class BaseInteractor(
    private val remoteRepository: RepositoryInterface<List<DataModel>>,
    private val localRepository: RepositoryInterface<List<DataModel>>
) : BaseInteractorInterface<AppState> {

    override fun getDataFromDataSource(word: String, fromRemoteSource: Boolean): Observable<AppState> {
        return if (fromRemoteSource) {
            remoteRepository.getData(word).map { AppState.Success(it) }
        } else {
            localRepository.getData(word).map { AppState.Success(it) }
        }
    }
}