package com.ocjadan.exhibitandroid.questions.questionsList

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.ocjadan.exhibitandroid.common.BaseFragment
import com.ocjadan.exhibitandroid.common.viewcontrollers.ViewControllerFactory
import com.ocjadan.exhibitandroid.common.ViewModelFactory
import javax.inject.Inject

class QuestionsListFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var viewControllerFactory: ViewControllerFactory

    @Inject
    lateinit var controller: QuestionsListController

    override fun onAttach(context: Context) {
        fragmentComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ViewModelProvider(this, viewModelFactory)[QuestionsListViewModel::class.java]

        viewModel.questions.observe(this) {
            controller.bindQuestions(it)
        }

        viewModel.error.observe(this) { }

        controller.bindViewModel(viewModel)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        val viewController = viewControllerFactory.getQuestionsListViewController(container)
        controller.bindViewController(viewController)
        return viewController.getRootView()
    }

    override fun onStart() {
        super.onStart()
        controller.onStart()
    }

    override fun onStop() {
        controller.onStop()
        super.onStop()
    }
}
