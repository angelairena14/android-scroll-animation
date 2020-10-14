package com.example.cermati.setup

import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter<T : MainView> :
    BaseView<T> {

    private val isViewAttached: Boolean get() = mvpView != null
    var mvpView: T? = null
    var disposables: CompositeDisposable? = null

    override fun attachView(baseInterface: T) {
        this.mvpView = baseInterface
        disposables = CompositeDisposable()
    }

    override fun detachView() {
        mvpView = null
        disposables?.dispose()
        disposables?.clear()
    }
}