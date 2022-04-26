package ru.gb.mytranslator.presenter

import retrofit2.Response
import ru.gb.mytranslator.model.RepositoryImpl
import ru.gb.mytranslator.model.data.AppState
import ru.gb.mytranslator.model.data.DataModel

class Presenter(
    private val repository: Repository = RepositoryImpl(),
    private var currentView: View? = null
): RepositoryCallback {

    fun attachView(view: View) {
        if (view != currentView) {
            currentView = view
        }
    }

    fun detachView(view: View) {
        if (view == currentView) {
            currentView = null
        }
    }

    fun getData(word: String) {
        currentView?.renderData(AppState.Loading(null))
        repository.getData(word, this)
    }

    override fun handleResponse(response: Response<List<DataModel>?>?) {
        if (response != null) {
            currentView?.renderData(AppState.Success(response.body()))
        } else currentView?.renderData(AppState.Error(Throwable("Empty response")))
    }

    override fun handleError(t: Throwable) {
        currentView?.renderData(AppState.Error(t))
    }
}
