package ru.gb.mytranslator.model

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.gb.mytranslator.model.data.DataModel
import ru.gb.mytranslator.model.retrofit.RetrofitImpl
import ru.gb.mytranslator.presenter.Repository
import ru.gb.mytranslator.presenter.RepositoryCallback

class RepositoryImpl(
    private val remoteProvider: RetrofitImpl = RetrofitImpl()
) : Repository {

    override fun getData(word: String, callback: RepositoryCallback) {
        val call = remoteProvider.getData(word)
        call?.enqueue(object : Callback<List<DataModel>> {

            override fun onResponse(
                call: Call<List<DataModel>>,
                response: Response<List<DataModel>>
            ) {
                callback.handleGitHubResponse(response)
            }

            override fun onFailure(
                call: Call<List<DataModel>>,
                t: Throwable
            ) {
                callback.handleGitHubError(t)
            }
        })
    }

}
