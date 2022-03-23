package com.ocjadan.exhibitandroid.questions.questionDetails

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.ocjadan.benchmarkable.questionDetails.IQuestionDetailsViewController
import com.ocjadan.benchmarkable.questionDetails.QuestionDetails
import com.ocjadan.exhibitandroid.common.BaseFragment
import com.ocjadan.exhibitandroid.common.viewcontroller.ViewControllerFactory
import com.ocjadan.exhibitandroid.common.viewmodel.ViewModelFactory
import java.lang.RuntimeException
import javax.inject.Inject

class QuestionDetailsFragment : BaseFragment() {

    companion object {
        const val QUESTION_DETAILS = "QUESTION_DETAILS"

        fun newInstance(details: QuestionDetails): QuestionDetailsFragment {
            val fragment = QuestionDetailsFragment()
            val args = Bundle(1)
            args.putSerializable(QUESTION_DETAILS, details)
            fragment.arguments = args
            return fragment
        }
    }

    @Inject
    lateinit var viewControllerFactory: ViewControllerFactory

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var questionDetailsVM: QuestionDetailsViewModel
    private lateinit var questionDetailsVC: IQuestionDetailsViewController
    private lateinit var details: QuestionDetails

    override fun onAttach(context: Context) {
        fragmentComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val arguments = arguments ?: throw RuntimeException("QuestionDetailsFragment needs a question ID.")
        details = arguments.getSerializable(QUESTION_DETAILS) as QuestionDetails

        questionDetailsVM = ViewModelProvider(this, viewModelFactory)[QuestionDetailsViewModel::class.java]
        questionDetailsVM.answers.observe(this) {
            it?.let {
                if (it.count() > 0) {
                    questionDetailsVC.bindAnswers(it)
                }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        questionDetailsVC = viewControllerFactory.getQuestionDetailsViewController(container)
        questionDetailsVC.bindQuestionDetails(details)
        return questionDetailsVC.getRootView()
    }

    override fun onStart() {
        super.onStart()
        if (details.isAnswered) {
            questionDetailsVM.loadAnswers(details.id)
        }
    }

    override fun onBackPressed(): Boolean {
        TODO("Not yet implemented")
    }
}