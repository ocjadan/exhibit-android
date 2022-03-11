package com.ocjadan.exhibitandroid.questions

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.ocjadan.exhibitandroid.BaseFragment
import com.ocjadan.exhibitandroid.ViewControllerFactory
import com.ocjadan.exhibitandroid.ViewModelFactory
import kotlinx.coroutines.launch
import javax.inject.Inject

class QuestionsListFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var viewControllerFactory: ViewControllerFactory

    private lateinit var viewModel: QuestionsListViewModel
    private lateinit var viewController: QuestionsListViewController

    override fun onAttach(context: Context) {
        fragmentComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[QuestionsListViewModel::class.java]

        viewModel.questions.observe(this) {
            viewController.bindQuestions(it)
        }

        viewModel.error.observe(this) {
            it?.let {
                viewController.bindError(it)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        viewController = viewControllerFactory.getQuestionsListViewController(container)
        return viewController.rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            viewModel.loadQuestions()
        }
    }
}
