package ru.gb.mytranslator

import com.nhaarman.mockito_kotlin.anyOrNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response
import ru.gb.mytranslator.model.data.DataModel
import ru.gb.mytranslator.presenter.Presenter
import ru.gb.mytranslator.presenter.Repository
import ru.gb.mytranslator.presenter.View

class PresenterTest {
    private lateinit var presenter: Presenter

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var currentView: View

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = Presenter(repository, currentView)
    }

    @Test
    fun `repository call getData verify`() {
        presenter.getData("word")
        Mockito.verify(repository, Mockito.times(1)).getData("word", presenter)
    }

    @Test
    fun `currentView call renderData on error verify`() {
        presenter.handleError(Throwable("Empty response"))
        Mockito.verify(currentView, Mockito.times(1)).renderData(anyOrNull())
    }

    @Test
    fun `currentView call renderData on response verify`() {
        val appDataModel = DataModel(null, null)
        val response: Response<List<DataModel>?> = Response.success(listOf(appDataModel))
        presenter.handleResponse(response)
        Mockito.verify(currentView, Mockito.times(1))
            .renderData(anyOrNull())
    }
}