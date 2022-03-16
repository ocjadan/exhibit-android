package com.ocjadan.exhibitandroid.common.viewcontroller

interface INavDrawerViewController: IObservableViewController<INavDrawerViewController.Listener> {
    interface Listener {
        fun onQuestionsListClicked()
    }

    fun onStart()
    fun onStop()
    fun openDrawer()
    fun closeDrawer()
    fun isDrawerOpen(): Boolean
}