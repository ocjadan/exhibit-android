package com.ocjadan.benchmarkable.questionDetails

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ocjadan.benchmarkable.R

class QuestionDetailsViewController(layoutInflater: LayoutInflater, viewGroup: ViewGroup?) :
    IQuestionDetailsViewController {

    private val rootView = layoutInflater.inflate(R.layout.layout_compose_view, viewGroup, false)
    private val _listenersMap: MutableSet<IQuestionDetailsViewController.Listener> by lazy { mutableSetOf() }

    override fun bindQuestionDetailsItem(item: QuestionDetailsItem) {

    }

    override fun getRootView(): View {
        return rootView
    }

    private fun getContext(): Context {
        return rootView.context
    }

    override fun addListener(listener: IQuestionDetailsViewController.Listener) {
        _listenersMap.add(listener)
    }

    override fun removeListener(listener: IQuestionDetailsViewController.Listener) {
        _listenersMap.remove(listener)
    }

    override fun getListeners(): Set<IQuestionDetailsViewController.Listener> {
        return _listenersMap.toSet()
    }
}