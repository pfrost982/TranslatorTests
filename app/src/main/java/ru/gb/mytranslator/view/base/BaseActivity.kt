package ru.gb.mytranslator.view.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.gb.mytranslator.model.data.AppState
import ru.gb.mytranslator.presenter.Presenter
import ru.gb.mytranslator.presenter.View

abstract class BaseActivity<T : AppState> : AppCompatActivity(), View {

    protected lateinit var presenter: Presenter

    protected abstract fun createPresenter(): Presenter

    abstract override fun renderData(appState: AppState)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = createPresenter()
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView(this)
    }
}
