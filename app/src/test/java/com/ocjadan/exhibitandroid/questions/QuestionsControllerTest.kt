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
    private lateinit var questionsVC: QuestionsViewController
    private lateinit var questionsVM: QuestionsViewModel

    @Before
    fun setUp() {
        val compositionRoot = CompositionRoot()
        questionsVM = compositionRoot.questionsViewModel
        questionsVC = compositionRoot.getQuestionsListViewControllerMock()
        SUT = QuestionsController(
            questionsVM,
            questionsVC,
            compositionRoot.navDrawer,
            compositionRoot.screensNavigator
        )

        SUT.onStart()
        Dispatchers.setMain(compositionRoot.testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun onStart_listening() {
        assert(questionsVC.getListeners().contains(SUT))
    }

    @Test
    fun onStop_notListening() {
        SUT.onStop()
        assert(!questionsVC.getListeners().contains(SUT))
        assert(questionsVC.getListeners().isEmpty())
    }
}