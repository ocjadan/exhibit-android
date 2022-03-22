package com.ocjadan.benchmarkable

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.ocjadan.benchmarkable.questionDetails.QuestionDetailsViewController

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(FrameLayout(this))
    }

    fun initQuestionDetailsVC() {
        QuestionDetailsViewController(layoutInflater, null)
    }
}