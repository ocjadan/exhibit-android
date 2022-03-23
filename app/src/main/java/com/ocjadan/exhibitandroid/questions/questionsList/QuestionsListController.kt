package com.ocjadan.exhibitandroid.questions.questionsList

import com.ocjadan.exhibitandroid.common.IBackPressedListener
import com.ocjadan.exhibitandroid.common.navdrawer.NavDrawerHelper
import com.ocjadan.exhibitandroid.common.screensNavigator.ScreensNavigator
import com.ocjadan.exhibitandroid.questions.questionsList.items.QuestionsListItem
import com.ocjadan.exhibitandroid.questions.questionsList.list.IQuestionsListViewController

class QuestionsListController(
    private val viewModel: QuestionsListViewModel,
    private val viewController: IQuestionsListViewController,
    private val navDrawerHelper: NavDrawerHelper,
    private val screensNavigator: ScreensNavigator
) : IQuestionsListViewController.Listener,
    IBackPressedListener {

    fun bindQuestions(questions: List<QuestionsListItem>) {
        viewController.bindQuestions(questions)
    }

    fun onStart() {
        viewController.addListener(this)
        viewModel.loadQuestions()
    }

    fun onStop() {
        viewController.removeListener(this)
    }

    override fun onQuestionsListItemClicked(questionsListItem: QuestionsListItem) {
        screensNavigator.toQuestionDetails(questionsListItem)
    }

    override fun onToolbarAvatarClicked() {
        if (!navDrawerHelper.isDrawerOpen()) {
            navDrawerHelper.openDrawer()
        }
    }

    override fun onBackPressed(): Boolean {
        if (navDrawerHelper.isDrawerOpen()) {
            navDrawerHelper.closeDrawer()
            return true
        }
        return false
    }
}