package com.ocjadan.exhibitandroid.questions

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import com.ocjadan.exhibitandroid.BaseActivity
import com.ocjadan.exhibitandroid.ui.theme.ExhibitAndroidTheme

class QuestionsListActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Move out of Activity
        setContent {
            ExhibitAndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    Text("QuestionsListActivity")
                }
            }
        }
    }
}