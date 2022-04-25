package com.ocjadan.exhibitandroid.common.viewcontroller

import com.ocjadan.exhibitandroid.common.observable.Observable

interface ObservableViewController<Listener> : Observable<Listener>, ViewController