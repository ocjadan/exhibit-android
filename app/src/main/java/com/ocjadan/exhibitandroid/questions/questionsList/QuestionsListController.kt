package com.ocjadan.exhibitandroid.questions.questionsList

import com.ocjadan.exhibitandroid.questions.Question
import com.ocjadan.exhibitandroid.questions.questionsList.view.IQuestionsListViewController

class QuestionsListController : IQuestionsListViewController.Listener {

    private lateinit var viewModel: QuestionsListViewModel
    private lateinit var viewController: IQuestionsListViewController

    fun bindViewModel(viewModel: QuestionsListViewModel) {
        this.viewModel = viewModel
    }

    fun bindViewController(controller: IQuestionsListViewController) {
        viewController = controller
    }

    fun bindQuestions(questions: List<Question>) {
        viewController.bindQuestions(questions)
    }

    fun onStart() {
        viewController.addListener(this)
        viewModel.loadQuestions()
    }

    fun onStop() {
        viewController.removeListener(this)
    }

    override fun onQuestionClicked(question: Question) {
        // Nothing to do yet
    }
}