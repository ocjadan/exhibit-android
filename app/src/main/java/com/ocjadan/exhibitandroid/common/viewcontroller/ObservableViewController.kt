package com.ocjadan.exhibitandroid.common.viewcontroller

import com.ocjadan.exhibitandroid.common.observable.IObservable

interface ObservableViewController<Listener> : IObservable<Listener>, ViewController