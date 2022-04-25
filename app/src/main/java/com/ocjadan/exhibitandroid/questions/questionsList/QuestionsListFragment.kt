package com.ocjadan.exhibitandroid.questions.questionsList

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

class QuestionsListFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var viewControllerFactory: ViewControllerFactory

    @Inject
    lateinit var navDrawer: NavDrawer

    @Inject
    lateinit var screensNavigator: ScreensNavigator

    private lateinit var questionsListViewModel: QuestionsListViewModel
    private lateinit var questionsListController: QuestionsListController

    override fun onAttach(context: Context) {
        fragmentComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        questionsListViewModel = ViewModelProvider(this, viewModelFactory)[QuestionsListViewModel::class.java]

        questionsListViewModel.questions.observe(this) {
            questionsListController.bindQuestions(it)
        }

        questionsListViewModel.error.observe(this) { }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        val viewController = viewControllerFactory.getQuestionsListViewController(container)

        questionsListController =
            QuestionsListController(questionsListViewModel, viewController, navDrawer, screensNavigator)

        return viewController.getRootView()
    }

    override fun onStart() {
        super.onStart()
        questionsListController.onStart()
    }

    override fun onStop() {
        questionsListController.onStop()
        super.onStop()
    }

    override fun onBackPressed(): Boolean {
        return questionsListController.onBackPressed()
    }
}
