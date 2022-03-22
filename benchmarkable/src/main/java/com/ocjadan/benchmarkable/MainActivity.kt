package com.ocjadan.benchmarkable

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
//import com.ocjadan.common.navdrawer.NavDrawerViewController
//import com.ocjadan.exhibitandroid.questions.questionsList.view.QuestionsListViewController

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(FrameLayout(this))
    }

    fun initNavDrawer() {
//        NavDrawerViewController(layoutInflater, supportFragmentManager, null, R.navigation.drawer_graph)
    }

    fun initQuestionsListVC() {
//        QuestionsListViewController(layoutInflater, null)
    }
}