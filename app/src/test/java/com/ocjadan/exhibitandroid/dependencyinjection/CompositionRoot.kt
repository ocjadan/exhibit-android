package com.ocjadan.exhibitandroid.dependencyinjection

import com.ocjadan.exhibitandroid.common.NavControllerHelperMock
import com.ocjadan.exhibitandroid.common.NavControllerWrapperMock
import com.ocjadan.exhibitandroid.common.navdrawer.NavDrawerHelper
import com.ocjadan.exhibitandroid.common.screensNavigator.INavControllerWrapper
import com.ocjadan.exhibitandroid.common.screensNavigator.NavControllerHelper
import com.ocjadan.exhibitandroid.common.screensNavigator.ScreensNavigator
import com.ocjadan.exhibitandroid.networking.StackOverflowApiMock
import com.ocjadan.exhibitandroid.questions.questionsList.items.FetchQuestionsListItemsEndpointMock
import com.ocjadan.exhibitandroid.questions.questionsList.items.FetchQuestionsListItemsUseCaseMock
import com.ocjadan.exhibitandroid.questions.questionsList.list.IQuestionsListViewController
import com.ocjadan.exhibitandroid.questions.questionsList.list.QuestionsListViewControllerMock
import com.ocjadan.exhibitandroid.questions.questionsList.QuestionsListViewModelMock
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class CompositionRoot {
    fun getStackOverflowApiMock(): StackOverflowApiMock {
        return StackOverflowApiMock()
    }

    fun getFetchQuestionsEndpointMock(): FetchQuestionsListItemsEndpointMock {
        return FetchQuestionsListItemsEndpointMock(getStackOverflowApiMock().mock)
    }

    fun getFetchQuestionsUseCaseMock(): FetchQuestionsListItemsUseCaseMock {
        return FetchQuestionsListItemsUseCaseMock(getFetchQuestionsEndpointMock())
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

    private fun getNavControllerHelper(): NavControllerHelper {
        return NavControllerHelperMock(getNavControllerWrapper())
    }

    private fun getNavControllerWrapper(): INavControllerWrapper {
        return NavControllerWrapperMock().mock
    }
}