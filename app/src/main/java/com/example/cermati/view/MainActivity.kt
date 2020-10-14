package com.example.cermati.view

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.cermati.R
import com.example.cermati.adapter.ListUsersAdapter
import com.example.cermati.contract.MainContract
import com.example.cermati.model.githubusersresponse.GitHubUsersResponse
import com.example.cermati.presenter.MainPresenter
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.search_user_inner_layout.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() ,MainContract.View{
    lateinit var presenter : MainPresenter
    var page = 1
    private lateinit var listAdapter : ListUsersAdapter
    private lateinit var searchItem : Observable<String>
    private var loadmore = true
    var keywords = "a"
    lateinit var mSwipeRefreshLayout : SwipeRefreshLayout

    override fun success() {
    }

    override fun error() {
        clearList()
        layout_shimmer.visibility = View.GONE
        progressBarLoadData.visibility = View.GONE
        Toast.makeText(this,"Something went wrong! Swipe to refresh",Toast.LENGTH_LONG).show()
    }

    override fun usersNotFound() {
        clearList()
        layout_shimmer.visibility = View.GONE
        progressBarLoadData.visibility = View.GONE
        Toast.makeText(this,"Users Not Found",Toast.LENGTH_LONG).show()
    }

    override fun bindData(d: GitHubUsersResponse, currentPage : Int, totalPage : Int) {
        runOnUiThread {
            loadmore = page != totalPage
            progressBarLoadData.visibility = View.GONE
            layout_shimmer.visibility = View.VISIBLE
            layout_shimmer.startShimmerAnimation()
            when(currentPage){
                1 ->{
                    listAdapter.updateList(d.items)
                }
                else ->{
                    val before = listAdapter.itemCount
                    listAdapter.d.addAll(d.items)
                    val after = listAdapter.itemCount
                    listAdapter.notifyItemRangeChanged(before + 1, after)
                }
            }
            rv_user_list.adapter = listAdapter
        }
    }

    fun clearList(){
        listAdapter.d.clear()
        listAdapter.notifyDataSetChanged()
    }

    fun initialize(){
        presenter = MainPresenter()
        presenter.attachView(this)
        listAdapter = ListUsersAdapter()
        rv_user_list.isNestedScrollingEnabled = false
        rv_user_list.setHasFixedSize(true)
        rv_user_list.setItemViewCacheSize(20)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mSwipeRefreshLayout = swipe_refresh_layout as SwipeRefreshLayout
        initialize()
        observeItem(et_search_user)
        nested_scroll_view.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { _, _, _, _, _ ->
            if (nested_scroll_view.getChildAt(0).bottom <= (nested_scroll_view.height + nested_scroll_view.scrollY)) {
                if (loadmore) {
                    loadmore = false
                    page++
                    loadData(keywords,page)
                }
            }
            mSwipeRefreshLayout.isEnabled = nested_scroll_view.scrollY == 0
        })

        mSwipeRefreshLayout.setOnRefreshListener {
            mSwipeRefreshLayout.isRefreshing = false
            listAdapter.d.clear()
            loadData(keywords,1)
            page = 1
        }
    }

    fun loadData(keywords : String, page : Int){
        presenter.fetchData(keywords,page)
    }

    override fun showLoading(){
    }

    override fun dismissLoading(){
    }

    private fun observeItem(itemName : EditText){
        searchItem = RxTextView.textChanges(itemName).debounce (200, TimeUnit.MILLISECONDS).map {
                charSequence -> charSequence.toString()
        }

        val itemObserver = object : Observer<String> {
            override fun onComplete() {

            }

            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(t: String) {
                page = 1
                if(t.isNotEmpty()){
                    progressBarLoadData.visibility = View.VISIBLE
                    layout_shimmer.visibility = View.GONE
                    mSwipeRefreshLayout.isEnabled = true
                    keywords = t
                    loadData(t,page)
                }
                else{
                    progressBarLoadData.visibility = View.GONE
                    layout_shimmer.visibility = View.GONE
                    mSwipeRefreshLayout.isEnabled = false
                    et_search_user.clearFocus()
                    clearList()
                }
            }

            override fun onError(e: Throwable) {

            }
        }
        (searchItem as Observable<String>?)?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeOn(AndroidSchedulers.mainThread())?.subscribe(itemObserver)

    }
}
