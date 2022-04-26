package ru.gb.mytranslator.presenter

import retrofit2.Call
import ru.gb.mytranslator.model.data.DataModel

interface Repository {
    fun getData(word: String): Call<List<DataModel>>
}
