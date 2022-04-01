package com.ocjadan.exhibitandroid.questions.questionsList

import com.ocjadan.exhibitandroid.dependencyinjection.CompositionRoot
import com.ocjadan.exhibitandroid.questions.questionsList.views.IQuestionsListViewController

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain

import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class QuestionsListControllerTest {

    private lateinit var SUT: QuestionsListController
    private lateinit var questionsListViewModel: QuestionsListViewModel
    private lateinit var questionsListViewController: IQuestionsListViewController

    @Before
    fun setUp() {
        val compositionRoot = CompositionRoot()
        val navDrawerHelper = compositionRoot.getNavDrawerHelper()
        val screensNavigator = compositionRoot.getScreensNavigator()
        questionsListViewModel = compositionRoot.getQuestionsListViewModelMock()
        questionsListViewController = compositionRoot.getQuestionsListViewControllerMock()
        SUT = QuestionsListController(questionsListViewModel, questionsListViewController, navDrawerHelper, screensNavigator)
        Dispatchers.setMain(compositionRoot.getTestDispatcher())
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun onStart_NoExceptions() {
        SUT.onStart()
    }

    @Test
    fun onStart_viewControllerListenersNotEmpty() {
        SUT.onStart()
        assert(questionsListViewController.getListeners().isNotEmpty())
    }

    @Test
    fun onStop_viewControllerListenersIsEmpty() {
        SUT.onStop()
        assert(questionsListViewController.getListeners().isEmpty())
    }
}