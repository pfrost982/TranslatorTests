package ru.gb.mytranslator.presenter

import ru.gb.mytranslator.model.data.AppState
import ru.gb.mytranslator.model.RepositoryImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.gb.mytranslator.model.data.DataModel

class Presenter(
    private val repository: Repository = RepositoryImpl(),
    private var currentView: View? = null
) {


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
        val call = repository.getData(word)
        call.enqueue(object : Callback<List<DataModel>> {

            override fun onResponse(
                call: Call<List<DataModel>>,
                response: Response<List<DataModel>>
            ) {
                currentView?.renderData(AppState.Success(response.body()))
            }

            override fun onFailure(
                call: Call<List<DataModel>>,
                t: Throwable
            ) {
                currentView?.renderData(AppState.Error(t))
            }
        })
    }
}
