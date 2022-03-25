package com.ocjadan.exhibitandroid.questions.questionsList

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ocjadan.exhibitandroid.dependencyinjection.CompositionRoot
import com.ocjadan.exhibitandroid.questions.questionsList.views.IQuestionsListViewController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After

import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class QuestionsListControllerTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule() // Required for LivaData `set`/`postValue`

    private val mainThreadSurrogate = newSingleThreadContext("Ui thread")

    private lateinit var SUT: QuestionsListController
    private lateinit var questionsListViewModel: QuestionsListViewModel
    private lateinit var questionsListViewController: IQuestionsListViewController

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)

        val compositionRoot = CompositionRoot()
        questionsListViewModel = compositionRoot.getQuestionsListViewModelMock()
        questionsListViewController = compositionRoot.getQuestionsListViewControllerMock()
        val navDrawerHelper = compositionRoot.getNavDrawerHelper()
        val screensNavigator = compositionRoot.getScreensNavigator()
        SUT = QuestionsListController(questionsListViewModel, questionsListViewController, navDrawerHelper, screensNavigator)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
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