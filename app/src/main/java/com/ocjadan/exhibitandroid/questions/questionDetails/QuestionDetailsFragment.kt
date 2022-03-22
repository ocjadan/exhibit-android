package com.ocjadan.exhibitandroid.questions.questionDetails

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.ocjadan.benchmarkable.questionDetails.IQuestionDetailsViewController
import com.ocjadan.exhibitandroid.common.BaseFragment
import com.ocjadan.exhibitandroid.common.viewcontroller.ViewControllerFactory
import com.ocjadan.exhibitandroid.common.viewmodel.ViewModelFactory
import javax.inject.Inject

class QuestionDetailsFragment : BaseFragment() {

    @Inject
    lateinit var viewControllerFactory: ViewControllerFactory

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var questionDetailsVM: QuestionDetailsViewModel
    private lateinit var questionDetailsVC: IQuestionDetailsViewController

    override fun onAttach(context: Context) {
        fragmentComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        questionDetailsVM = ViewModelProvider(this, viewModelFactory)[QuestionDetailsViewModel::class.java]

        questionDetailsVM.questionDetailsItem.observe(this) {
            it?.let {
                questionDetailsVC.bindQuestionDetailsItem(it)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        val viewController = viewControllerFactory.getQuestionDetailsViewController(container)
        return viewController.getRootView()
    }

    override fun onBackPressed(): Boolean {
        TODO("Not yet implemented")
    }
}