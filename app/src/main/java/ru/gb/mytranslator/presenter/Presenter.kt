package ru.gb.mytranslator.presenter

import ru.gb.mytranslator.model.data.AppState
import ru.gb.mytranslator.model.RepositoryImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class Presenter(
    private val repository: Repository = RepositoryImpl(),
    private val compositeDisposable: CompositeDisposable = CompositeDisposable(),
) {

    private var currentView: View? = null

    fun attachView(view: View) {
        if (view != currentView) {
            currentView = view
        }
    }

    fun detachView(view: View) {
        compositeDisposable.clear()
        if (view == currentView) {
            currentView = null
        }
    }

    fun getData(word: String, isOnline: Boolean) {
        compositeDisposable.add(
            repository.getData(word, isOnline).map {
                AppState.Success(it)
            }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { currentView?.renderData(AppState.Loading(null)) }
                .subscribeWith(getObserver())
        )
    }

    private fun getObserver(): DisposableObserver<AppState> {
        return object : DisposableObserver<AppState>() {

            override fun onNext(appState: AppState) {
                currentView?.renderData(appState)
            }

            override fun onError(e: Throwable) {
                currentView?.renderData(AppState.Error(e))
            }

            override fun onComplete() {
            }
        }
    }
}
