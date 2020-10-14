package com.example.cermati.presenter

import com.example.cermati.contract.MainContract
import com.example.cermati.model.githubusersresponse.GitHubUsersResponse
import com.example.cermati.networks.ApiClient
import com.example.cermati.setup.BasePresenter
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables
import io.reactivex.processors.PublishProcessor
import io.reactivex.schedulers.Schedulers

class MainPresenter : BasePresenter<MainContract.View>(),MainContract.Presenter{
    var disposing : Disposable = Disposables.empty()
    var api = ApiClient()
    var totalItem = 40
    private var paginator: PublishProcessor<Int>? = null

    override fun fetchData(keywords: String,page : Int) {
        paginator = PublishProcessor.create()
        paginator?.let {mPaginator ->
            disposing = mPaginator.onBackpressureDrop()
                .doOnNext { _page ->
                    mvpView?.showLoading()
                }.concatMapSingle { page ->
                    dataFromNetwork(keywords.toLowerCase(),page)
                        .subscribeOn(Schedulers.io())
                }.observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.total_count != 0) {
                        mvpView?.dismissLoading()
                        var limit = totalItem
                        val totalitem = (page - 1) * limit + 1
                        val totaling = totalitem / limit
                        mvpView?.bindData(
                            it,
                            page,
                            totaling.toDouble().toInt()
                        )
                    } else {
                        mvpView?.usersNotFound()
                    }
                }, {
                    mvpView?.error()
                })
            mPaginator.onNext(page)
        }

    }

    fun dataFromNetwork(keywords: String,page : Int) : Single<GitHubUsersResponse>{
        return Single.create {emitter ->
            api.services.getGitHubUsers(keywords,page,totalItem)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnNext {  }
                .subscribe({
                    mvpView?.dismissLoading()
                    if(it.total_count != 0) {
                        emitter.onSuccess(it)
                    }
                    else mvpView?.usersNotFound()
                },{
                    mvpView?.dismissLoading()
                    mvpView?.error()
                    emitter.onError(it)
                })
        }
    }
}