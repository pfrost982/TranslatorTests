package ru.gb.mytranslator.model

import retrofit2.Call
import ru.gb.mytranslator.model.data.DataModel
import ru.gb.mytranslator.model.retrofit.RetrofitImpl
import ru.gb.mytranslator.presenter.Repository

class RepositoryImpl(
    private val remoteProvider: RetrofitImpl = RetrofitImpl()
) : Repository {

    override fun getData(word: String): Call<List<DataModel>> {
        return remoteProvider.getData(word)
    }
}
