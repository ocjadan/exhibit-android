package com.ocjadan.exhibitandroid.dependencyinjection

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.ocjadan.exhibitandroid.common.screensNavigator.NavControllerHelperMock
import com.ocjadan.exhibitandroid.common.screensNavigator.NavControllerWrapperMock
import com.ocjadan.exhibitandroid.common.TimeProviderMock
import com.ocjadan.exhibitandroid.common.navdrawer.NavDrawerHelper
import com.ocjadan.exhibitandroid.common.screensNavigator.INavControllerWrapper
import com.ocjadan.exhibitandroid.common.screensNavigator.NavControllerHelper
import com.ocjadan.exhibitandroid.common.screensNavigator.ScreensNavigator
import com.ocjadan.exhibitandroid.database.questions.QuestionsCacheMock
import com.ocjadan.exhibitandroid.database.questions.QuestionsDaoMock
import com.ocjadan.exhibitandroid.database.owners.OwnersCacheMock
import com.ocjadan.exhibitandroid.database.owners.OwnersDaoMock
import com.ocjadan.exhibitandroid.database.updates.UpdatesCacheMock
import com.ocjadan.exhibitandroid.database.updates.UpdatesDao
import com.ocjadan.exhibitandroid.database.updates.UpdatesDaoMock
import com.ocjadan.exhibitandroid.networking.StackOverflowApiMock
import com.ocjadan.exhibitandroid.networking.questions.FetchQuestionAnswersEndpointMock
import com.ocjadan.exhibitandroid.questions.questionDetails.FetchQuestionAnswersUseCase
import com.ocjadan.exhibitandroid.questions.questionDetails.FetchQuestionAnswersUseCaseMock
import com.ocjadan.exhibitandroid.networking.questions.FetchQuestionsEndpointMock
import com.ocjadan.exhibitandroid.questions.questionsList.FetchQuestionsUseCaseMock
import com.ocjadan.exhibitandroid.questions.questionsList.views.IQuestionsListViewController
import com.ocjadan.exhibitandroid.questions.questionsList.views.QuestionsListViewControllerMock
import com.ocjadan.exhibitandroid.questions.questionsList.QuestionsListViewModelMock

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import org.mockito.Mockito.*
import org.mockito.kotlin.anyOrNull

class CompositionRoot {
    private val context: Context by lazy {
        mock(Context::class.java)
    }

    val layoutInflater: LayoutInflater by lazy {
        val inflater = mock(LayoutInflater::class.java)
        `when`(inflater.inflate(anyInt(), anyOrNull(), anyBoolean())).thenReturn(View(context))
        inflater
    }

    @ExperimentalCoroutinesApi
    fun getStackOverflowApiMock(): StackOverflowApiMock {
        return StackOverflowApiMock()
    }

    @ExperimentalCoroutinesApi
    fun getTestDispatcher(): CoroutineDispatcher {
        return StandardTestDispatcher()
    }

    @ExperimentalCoroutinesApi
    fun getFetchQuestionsEndpointMock(): FetchQuestionsEndpointMock {
        return FetchQuestionsEndpointMock(getStackOverflowApiMock().mock, getTestDispatcher())
    }

    @ExperimentalCoroutinesApi
    fun getFetchQuestionsUseCaseMock(): FetchQuestionsUseCaseMock {
        return FetchQuestionsUseCaseMock(
            getFetchQuestionsEndpointMock(),
            getQuestionsCacheMock(),
            getOwnersCacheMock(),
            getUpdatesCacheMock(),
            getTimeProviderMock(),
            getTestDispatcher()
        )
    }

//    val layoutInflater: LayoutInflaterMock
//        get() = LayoutInflaterMock(context)

    @ExperimentalCoroutinesApi
    fun getFetchQuestionAnswersEndpointMock(): FetchQuestionAnswersEndpointMock {
        return FetchQuestionAnswersEndpointMock(getStackOverflowApiMock().mock, getTestDispatcher())
    }

    @ExperimentalCoroutinesApi
    fun getFetchQuestionAnswersUseCaseMock(): FetchQuestionAnswersUseCase {
        return FetchQuestionAnswersUseCaseMock(
            getFetchQuestionAnswersEndpointMock(),
            getTestDispatcher()
        )
    }

    @ExperimentalCoroutinesApi
    fun getQuestionsListViewModelMock(): QuestionsListViewModelMock {
        return QuestionsListViewModelMock(getFetchQuestionsUseCaseMock())
    }

    fun getQuestionsListViewControllerMock(): IQuestionsListViewController {
        return QuestionsListViewControllerMock()
    }

    fun getNavDrawerHelper(): NavDrawerHelper {
        return object : NavDrawerHelper {
            override fun openDrawer() {
                TODO("Not yet implemented")
            }

            override fun closeDrawer() {
                TODO("Not yet implemented")
            }

            override fun isDrawerOpen(): Boolean {
                TODO("Not yet implemented")
            }
        }
    }

    fun getScreensNavigator(): ScreensNavigator {
        return ScreensNavigator(getNavControllerHelper())
    }

    @ExperimentalCoroutinesApi
    fun getQuestionsCacheMock(): QuestionsCacheMock {
        return QuestionsCacheMock(getQuestionsDaoMock(), getTestDispatcher())
    }

    @ExperimentalCoroutinesApi
    fun getOwnersCacheMock(): OwnersCacheMock {
        return OwnersCacheMock(getOwnersDaoMock().mock, getTestDispatcher())
    }

    @ExperimentalCoroutinesApi
    fun getUpdatesCacheMock(): UpdatesCacheMock {
        return UpdatesCacheMock(getUpdatesDaoMock(), getTestDispatcher())
    }

    fun getTimeProviderMock(): TimeProviderMock {
        return TimeProviderMock()
    }

    private fun getNavControllerHelper(): NavControllerHelper {
        return NavControllerHelperMock(getNavControllerWrapper())
    }

    private fun getNavControllerWrapper(): INavControllerWrapper {
        return NavControllerWrapperMock().mock
    }

    fun getQuestionsDaoMock(): QuestionsDaoMock {
        return QuestionsDaoMock()
    }

    fun getOwnersDaoMock(): OwnersDaoMock {
        return OwnersDaoMock()
    }

    private fun getUpdatesDaoMock(): UpdatesDao {
        return UpdatesDaoMock().mock
    }
}