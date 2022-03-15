package com.ocjadan.exhibitandroid.questions.questionsList

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ocjadan.exhibitandroid.dependencyinjection.CompositionRoot
import com.ocjadan.exhibitandroid.questions.questionsList.view.IQuestionsListViewController
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
        SUT = QuestionsListController()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    // onStart; no bindings; property access exception
    @Test(expected = UninitializedPropertyAccessException::class)
    fun onStart_noBindings_UninitializedPropertyAccessException() {
        SUT.onStart()
    }

    // onStart; one binding; property access exception
    @Test(expected = UninitializedPropertyAccessException::class)
    fun onStart_bindViewController_UninitializedPropertyAccessException() {
        oneBinding()
        SUT.onStart()
    }

    // onStart; all bindings; no exceptions
    @Test
    fun onStart_allBindings_NoExceptions() {
        allBindings()
        SUT.onStart()
    }

    @Test
    fun onStart_allBindings_viewControllerListenersNotEmpty() {
        allBindings()
        SUT.onStart()
        assert(questionsListViewController.listenersMap.isNotEmpty())
    }

    @Test
    fun onStop_allBindings_viewControllerListenersIsEmpty() {
        allBindings()
        SUT.onStop()
        assert(questionsListViewController.listenersMap.isEmpty())
    }

    // ------------------------------------------------------------------------------------------------------------------
    // Region: Helper Methods
    // ------------------------------------------------------------------------------------------------------------------

    private fun oneBinding() {
        SUT.bindViewController(questionsListViewController)
    }

    private fun allBindings() {
        oneBinding()
        SUT.bindViewModel(questionsListViewModel)
    }
}