package com.ocjadan.benchmarkable

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.ocjadan.benchmarkable.questionDetails.QuestionDetails
import com.ocjadan.benchmarkable.questionDetails.QuestionDetailsViewControllerImpl

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(FrameLayout(this))
    }

    fun initQuestionDetailsVC() {
        val viewController = QuestionDetailsViewControllerImpl(layoutInflater, null)
        val details = QuestionDetails(0, false, 0, "OWNER", "IMAGE")
        viewController.show(details, listOf())
    }
}