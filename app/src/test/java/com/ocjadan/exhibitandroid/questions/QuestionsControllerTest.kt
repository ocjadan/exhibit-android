package com.ocjadan.exhibitandroid.questions

import com.ocjadan.exhibitandroid.dependencyinjection.CompositionRoot
import com.ocjadan.exhibitandroid.questions.views.QuestionsViewController

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain

import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class QuestionsControllerTest {

    private lateinit var SUT: QuestionsController
    private lateinit var questionsViewModel: QuestionsViewModel
    private lateinit var questionsViewController: QuestionsViewController

    @Before
    fun setUp() {
        val compositionRoot = CompositionRoot()
        questionsViewModel = compositionRoot.getQuestionsListViewModelMock()
        questionsViewController = compositionRoot.getQuestionsListViewControllerMock()
        SUT = QuestionsController(
            questionsViewModel,
            questionsViewController,
            compositionRoot.navDrawer,
            compositionRoot.screensNavigator
        )
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
        assert(questionsViewController.getListeners().isNotEmpty())
    }

    @Test
    fun onStop_viewControllerListenersIsEmpty() {
        SUT.onStop()
        assert(questionsViewController.getListeners().isEmpty())
    }
}