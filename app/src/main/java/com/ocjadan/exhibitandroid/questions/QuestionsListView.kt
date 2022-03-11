package com.ocjadan.exhibitandroid.questions

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ocjadan.exhibitandroid.ui.theme.ExhibitAndroidTheme

@Composable
fun QuestionsListView(questions: List<Question>) {
    ExhibitAndroidTheme {
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            if (questions.isEmpty()) {
                Text("No questions")
            } else {
                Text("Questions!")
            }
        }
    }
}