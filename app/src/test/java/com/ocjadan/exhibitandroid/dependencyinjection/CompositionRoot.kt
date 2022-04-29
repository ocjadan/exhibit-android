package com.ocjadan.exhibitandroid.dependencyinjection

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

import com.ocjadan.exhibitandroid.common.TimeProviderMock
import com.ocjadan.exhibitandroid.common.navdrawer.NavDrawer
import com.ocjadan.exhibitandroid.common.navdrawer.NavDrawerViewController
import com.ocjadan.exhibitandroid.common.screensNavigator.NavigationHelper
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
import com.ocjadan.exhibitandroid.questionDetails.FetchQuestionAnswersUseCaseMock
import com.ocjadan.exhibitandroid.networking.questions.FetchQuestionsEndpointMock
import com.ocjadan.exhibitandroid.questions.FetchQuestionsUseCaseMock
import com.ocjadan.exhibitandroid.questions.views.QuestionsViewController
import com.ocjadan.exhibitandroid.questions.views.QuestionsViewControllerMock
import com.ocjadan.exhibitandroid.questions.QuestionsViewModelMock

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher

import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.anyInt
import org.mockito.Mockito.anyBoolean
import org.mockito.kotlin.anyOrNull

class CompositionRoot {
    val layoutInflater: LayoutInflater by lazy {
        val inflater = mock(LayoutInflater::class.java)
        val viewMock = view // passing view directly into thenReturn doesn't work
        `when`(inflater.inflate(anyInt(), anyOrNull(), anyBoolean())).thenReturn(viewMock)
        inflater
    }

    val navDrawer: NavDrawer by lazy {
        mock(NavDrawer::class.java)
    }

    val screensNavigator: ScreensNavigator by lazy {
        ScreensNavigator(navigationHelper)
    }

    private val view: View by lazy {
        val view = mock(View::class.java)
        `when`(view.context).thenReturn(context)
        view
    }

    private val context: Context by lazy {
        mock(Context::class.java)
    }

    private val navigationHelper: NavigationHelper by lazy {
        mock(NavigationHelper::class.java)
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
    fun getFetchQuestionsUseCaseMock() = FetchQuestionsUseCaseMock()

    @ExperimentalCoroutinesApi
    fun getFetchQuestionAnswersEndpointMock(): FetchQuestionAnswersEndpointMock {
        return FetchQuestionAnswersEndpointMock(getStackOverflowApiMock().mock, getTestDispatcher())
    }

    @ExperimentalCoroutinesApi
    fun getFetchQuestionAnswersUseCaseMock() = FetchQuestionAnswersUseCaseMock()

    @ExperimentalCoroutinesApi
    fun getQuestionsListViewModelMock(): QuestionsViewModelMock {
        return QuestionsViewModelMock(getFetchQuestionsUseCaseMock())
    }

    fun getQuestionsListViewControllerMock(): QuestionsViewController {
        return QuestionsViewControllerMock()
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