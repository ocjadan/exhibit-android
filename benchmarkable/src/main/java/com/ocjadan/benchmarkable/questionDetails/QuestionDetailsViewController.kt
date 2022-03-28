package com.ocjadan.benchmarkable.questionDetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import com.ocjadan.benchmarkable.R
import java.lang.RuntimeException

class QuestionDetailsViewController(layoutInflater: LayoutInflater, viewGroup: ViewGroup?) :
    IQuestionDetailsViewController {

    private val rootView = layoutInflater.inflate(R.layout.compose_view, viewGroup, false)

    private lateinit var details: QuestionDetails

    private var answers: List<QuestionAnswer> = emptyList()

    override fun bindDetails(details: QuestionDetails) {
        this.details = details
        if (details.isAnswered) {
            composeDetailsLoading()
        } else {
            composeDetailsWithNoAnswers()
        }
    }

    override fun bindAnswers(answers: List<QuestionAnswer>) {
        if (answers.count() < 1) {
            throw RuntimeException("No answers.")
        }

        this.answers = answers
        composeDetailsWithAnswers()
    }

    private fun composeDetailsLoading() {
        getComposeView().setContent {
            QuestionDetailsView(details, answers, QuestionDetailsViewState.LOADING)
        }
    }

    private fun composeDetailsWithNoAnswers() {
        getComposeView().setContent {
            QuestionDetailsView(details, answers, QuestionDetailsViewState.NO_ANSWERS)
        }
    }

    private fun composeDetailsWithAnswers() {
        getComposeView().setContent {
            QuestionDetailsView(details, answers, QuestionDetailsViewState.ANSWERS)
        }
    }

    private fun getComposeView(): ComposeView {
        return getRootView() as? ComposeView ?: throw RuntimeException("QuestionDetails is not a Compose View")
    }

    override fun getRootView(): View {
        return rootView
    }
}