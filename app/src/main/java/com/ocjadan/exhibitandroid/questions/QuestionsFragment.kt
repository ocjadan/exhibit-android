package com.ocjadan.exhibitandroid.questions

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.ocjadan.exhibitandroid.common.BaseFragment
import com.ocjadan.exhibitandroid.common.navdrawer.NavDrawer
import com.ocjadan.exhibitandroid.common.screensNavigator.ScreensNavigator
import com.ocjadan.exhibitandroid.common.viewcontroller.ViewControllerFactory
import com.ocjadan.exhibitandroid.common.viewmodel.ViewModelFactory
import javax.inject.Inject

class QuestionsFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var viewControllerFactory: ViewControllerFactory

    @Inject
    lateinit var navDrawer: NavDrawer

    @Inject
    lateinit var screensNavigator: ScreensNavigator

    private lateinit var questionsViewModel: QuestionsViewModel
    private lateinit var questionsController: QuestionsController

    override fun onAttach(context: Context) {
        fragmentComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        questionsViewModel = ViewModelProvider(this, viewModelFactory)[QuestionsViewModel::class.java]

        questionsViewModel.questions.observe(this) {
            questionsController.showQuestions(it)
        }

        questionsViewModel.error.observe(this) { }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        val questionsListViewController = viewControllerFactory.getQuestionsListViewController(container)

        questionsController =
            QuestionsController(questionsViewModel, questionsListViewController, navDrawer, screensNavigator)

        return questionsListViewController.getRootView()
    }

    override fun onStart() {
        super.onStart()
        questionsController.onStart()
    }

    override fun onStop() {
        questionsController.onStop()
        super.onStop()
    }

    override fun handledBackPress(): Boolean {
        return questionsController.handledBackPress()
    }
}
