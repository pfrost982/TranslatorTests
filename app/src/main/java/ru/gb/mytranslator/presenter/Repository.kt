package ru.gb.mytranslator.presenter

import retrofit2.Response
import ru.gb.mytranslator.model.data.DataModel

interface Repository {
    fun getData(word: String, callback: RepositoryCallback)
}

interface RepositoryCallback {
    fun handleGitHubResponse(response: Response<List<DataModel>>?)
    fun handleGitHubError(t: Throwable)
}
