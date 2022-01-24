package ru.gb.mytranslator.presenter

import ru.gb.mytranslator.model.data.AppState

interface View {

    fun renderData(appState: AppState)

}
