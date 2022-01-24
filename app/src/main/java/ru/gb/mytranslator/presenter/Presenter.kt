package ru.gb.mytranslator.presenter

interface Presenter {

    fun attachView(view: View)

    fun detachView(view: View)

    fun getData(word: String, isOnline: Boolean)
}
