package ru.gb.mytranslator.model.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import ru.gb.mytranslator.model.data.DataModel

interface ApiService {

    @GET("words/search")
    fun search(@Query("search") wordToSearch: String): Call<List<DataModel>>?
}
