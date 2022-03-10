package com.ocjadan.exhibitandroid.questions

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.ocjadan.exhibitandroid.BaseActivity
import com.ocjadan.exhibitandroid.ViewModelFactory
import com.ocjadan.exhibitandroid.ui.theme.ExhibitAndroidTheme
import kotlinx.coroutines.launch
import javax.inject.Inject

class QuestionsListActivity : BaseActivity() {

    lateinit var questionsListViewModel: QuestionsListViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        activityComponent.inject(this)
        super.onCreate(savedInstanceState)

        questionsListViewModel = ViewModelProvider(this, viewModelFactory)[QuestionsListViewModel::class.java]

        // Move out of Activity
        setContent {
            ExhibitAndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    Text("QuestionsListActivity")
                }
            }
        }

        lifecycleScope.launch {
            questionsListViewModel.loadQuestions()
        }
    }
}