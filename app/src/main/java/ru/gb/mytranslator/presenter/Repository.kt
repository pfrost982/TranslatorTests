package ru.gb.mytranslator.presenter

import ru.gb.mytranslator.model.data.DataModel
import io.reactivex.Observable

interface Repository {

    fun getData(word: String, isOnline: Boolean): Observable<List<DataModel>>
}
