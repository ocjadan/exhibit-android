package com.ocjadan.benchmarkable.questionDetails

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import com.ocjadan.benchmarkable.R
import com.ocjadan.benchmarkable.answers.Answer
import java.lang.RuntimeException

class QuestionDetailsViewController(layoutInflater: LayoutInflater, viewGroup: ViewGroup?) :
    IQuestionDetailsViewController {

    private val rootView = layoutInflater.inflate(R.layout.compose_view, viewGroup, false)
    private val _listenersMap: MutableSet<IQuestionDetailsViewController.Listener> by lazy { mutableSetOf() }

    private lateinit var details: QuestionDetails

    private var answers: List<Answer> = emptyList()

    override fun bindQuestionDetails(details: QuestionDetails) {
        this.details = details
        if (details.isAnswered) {
            composeDetailsLoading()
        } else {
            composeDetailsWithNoAnswers()
        }
    }

    override fun bindAnswers(answers: List<Answer>) {
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

    private fun getContext(): Context {
        return rootView.context
    }

    override fun addListener(listener: IQuestionDetailsViewController.Listener) {
        _listenersMap.add(listener)
    }

    override fun removeListener(listener: IQuestionDetailsViewController.Listener) {
        _listenersMap.remove(listener)
    }

    override fun getListeners(): Set<IQuestionDetailsViewController.Listener> {
        return _listenersMap.toSet()
    }
}