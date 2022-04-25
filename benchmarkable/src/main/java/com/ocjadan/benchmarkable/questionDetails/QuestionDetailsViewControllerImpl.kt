package com.ocjadan.benchmarkable.questionDetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import com.ocjadan.benchmarkable.R
import java.lang.RuntimeException

class QuestionDetailsViewControllerImpl(layoutInflater: LayoutInflater, viewGroup: ViewGroup?) :
    QuestionDetailsViewController {

    private val rootView = layoutInflater.inflate(R.layout.compose_view, viewGroup, false)

    override fun show(details: QuestionDetails, answers: List<QuestionAnswer>) {
        composeDetails(details, answers)
    }

    private fun composeDetails(details: QuestionDetails, answers: List<QuestionAnswer>) {
        getComposeView().setContent { QuestionDetailsView(details, answers) }
    }

    private fun getComposeView(): ComposeView {
        return getRootView() as? ComposeView ?: throw RuntimeException("QuestionDetails is not a ComposeView")
    }

    override fun getRootView(): View {
        return rootView
    }
}