package com.ocjadan.exhibitandroid.questions

import com.ocjadan.exhibitandroid.common.BackPressedListener
import com.ocjadan.exhibitandroid.common.navdrawer.NavDrawer
import com.ocjadan.exhibitandroid.common.screensNavigator.ScreensNavigator
import com.ocjadan.exhibitandroid.questions.views.QuestionsViewController

class QuestionsController(
    private val viewModel: QuestionsViewModel,
    private val viewController: QuestionsViewController,
    private val navDrawer: NavDrawer,
    private val screensNavigator: ScreensNavigator
) : QuestionsViewController.Listener, BackPressedListener {

    fun showQuestions(questions: List<Question>) {
        viewController.showQuestions(questions)
    }

    fun onStart() {
        viewController.addListener(this)
        viewModel.loadQuestions()
    }

    fun onStop() {
        viewController.removeListener(this)
    }

    override fun onQuestionClicked(question: Question) {
        screensNavigator.toQuestionDetails(question)
    }

    override fun onToolbarAvatarClicked() {
        navDrawer.openDrawer()
    }

    override fun handledBackPress(): Boolean {
        return navDrawer.handledBackPress()
    }
}