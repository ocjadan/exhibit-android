package com.ocjadan.exhibitandroid.dependencyinjection

import com.ocjadan.exhibitandroid.common.NavControllerHelperMock
import com.ocjadan.exhibitandroid.common.NavControllerWrapperMock
import com.ocjadan.exhibitandroid.common.TimeProviderMock
import com.ocjadan.exhibitandroid.common.navdrawer.NavDrawerHelper
import com.ocjadan.exhibitandroid.common.screensNavigator.INavControllerWrapper
import com.ocjadan.exhibitandroid.common.screensNavigator.NavControllerHelper
import com.ocjadan.exhibitandroid.common.screensNavigator.ScreensNavigator
import com.ocjadan.exhibitandroid.database.questions.QuestionsCacheMock
import com.ocjadan.exhibitandroid.database.questions.QuestionsDaoMock
import com.ocjadan.exhibitandroid.database.owners.OwnersCacheMock
import com.ocjadan.exhibitandroid.database.owners.OwnersDao
import com.ocjadan.exhibitandroid.database.owners.OwnersDaoMock
import com.ocjadan.exhibitandroid.database.questions.QuestionsDao
import com.ocjadan.exhibitandroid.database.updates.UpdatesCacheMock
import com.ocjadan.exhibitandroid.database.updates.UpdatesDao
import com.ocjadan.exhibitandroid.database.updates.UpdatesDaoMock
import com.ocjadan.exhibitandroid.networking.StackOverflowApiMock
import com.ocjadan.exhibitandroid.questions.questionDetails.FetchQuestionAnswersEndpointMock
import com.ocjadan.exhibitandroid.questions.questionDetails.FetchQuestionAnswersUseCase
import com.ocjadan.exhibitandroid.questions.questionDetails.FetchQuestionAnswersUseCaseMock
import com.ocjadan.exhibitandroid.questions.questionsList.FetchQuestionsUseCase
import com.ocjadan.exhibitandroid.questions.questionsList.items.FetchQuestionsEndpointMock
import com.ocjadan.exhibitandroid.questions.questionsList.items.FetchQuestionsUseCaseMock
import com.ocjadan.exhibitandroid.questions.questionsList.views.IQuestionsListViewController
import com.ocjadan.exhibitandroid.questions.questionsList.views.QuestionsListViewControllerMock
import com.ocjadan.exhibitandroid.questions.questionsList.QuestionsListViewModelMock
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class CompositionRoot {
    fun getStackOverflowApiMock(): StackOverflowApiMock {
        return StackOverflowApiMock()
    }

    fun getFetchQuestionsEndpointMock(): FetchQuestionsEndpointMock {
        return FetchQuestionsEndpointMock(getStackOverflowApiMock().mock)
    }

    fun getFetchQuestionsUseCaseMock(): FetchQuestionsUseCase {
        return FetchQuestionsUseCaseMock(
            getFetchQuestionsEndpointMock(),
            getQuestionsCacheMock(),
            getOwnersCacheMock(),
            getUpdatesCacheMock(),
            getTimeProviderMock()
        )
    }

    private fun getFetchQuestionAnswersEndpointMock(): FetchQuestionAnswersEndpointMock {
        return FetchQuestionAnswersEndpointMock(getStackOverflowApiMock().mock)
    }

    fun getFetchQuestionAnswersUseCaseMock(): FetchQuestionAnswersUseCase {
        return FetchQuestionAnswersUseCaseMock(
            getFetchQuestionAnswersEndpointMock()
        )
    }

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

    fun getQuestionsCacheMock(): QuestionsCacheMock {
        return QuestionsCacheMock(getQuestionsDaoMock())
    }

    fun getOwnersCacheMock(): OwnersCacheMock {
        return OwnersCacheMock(getOwnersDaoMock())
    }

    fun getUpdatesCacheMock(): UpdatesCacheMock {
        return UpdatesCacheMock(getUpdatesDaoMock())
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

    private fun getQuestionsDaoMock(): QuestionsDao {
        return QuestionsDaoMock().mock
    }

    private fun getOwnersDaoMock(): OwnersDao {
        return OwnersDaoMock().mock
    }

    private fun getUpdatesDaoMock(): UpdatesDao {
        return UpdatesDaoMock().mock
    }
}