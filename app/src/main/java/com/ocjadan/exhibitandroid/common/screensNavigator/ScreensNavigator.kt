package com.ocjadan.exhibitandroid.common.screensNavigator

import android.os.Bundle
import com.ocjadan.benchmarkable.questionDetails.QuestionDetails
import com.ocjadan.exhibitandroid.R
import com.ocjadan.exhibitandroid.questions.questionDetails.QuestionDetailsFragment
import com.ocjadan.exhibitandroid.questions.questionsList.QuestionsListItem

class ScreensNavigator(private val navigationHelper: NavControllerHelper) {
    fun toQuestionDetails(question: QuestionsListItem) {
        val details = QuestionDetails(
            question.id,
            question.isAnswered,
            question.owner.userId,
            question.owner.name,
            question.owner.profileImage
        )
        val args = Bundle(1)
        args.putSerializable(QuestionDetailsFragment.QUESTION_DETAILS, details)
        navigationHelper.navigateTo(R.id.nav_frag_questiondetails, args)
    }
}