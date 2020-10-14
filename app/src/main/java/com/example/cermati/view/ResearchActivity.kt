package com.example.cermati.view

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import com.example.cermati.R
import com.example.cermati.model.researchmodel.Data
import com.example.cermati.model.researchmodel.ResearchModel
import com.example.cermati.model.researchmodel.ResearchModelItem
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_research.*
import kotlinx.android.synthetic.main.item_indicator.view.*
import kotlinx.android.synthetic.main.item_list_research.view.*
import kotlinx.android.synthetic.main.list_research.view.*

class ResearchActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_research)
        var json = "[{\"title\":\"Research A\",\"data\":[{\"title\":\"Sub Research A\",\"desc\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.\"},{\"title\":\"Sub Research B\",\"desc\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.\"},{\"title\":\"Sub Research C\",\"desc\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.\"},{\"title\":\"Sub Research D\",\"desc\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.\"}]},{\"title\":\"Research B\",\"data\":[{\"title\":\"Sub Research A\",\"desc\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.\"},{\"title\":\"Sub Research B\",\"desc\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.\"},{\"title\":\"Sub Research C\",\"desc\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.\"},{\"title\":\"Sub Research D\",\"desc\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.\"}]},{\"title\":\"Research C\",\"data\":[{\"title\":\"Sub Research A\",\"desc\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.\"},{\"title\":\"Sub Research B\",\"desc\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.\"},{\"title\":\"Sub Research C\",\"desc\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.\"},{\"title\":\"Sub Research D\",\"desc\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.\"}]},{\"title\":\"Research D\",\"data\":[{\"title\":\"Sub Research A\",\"desc\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.\"},{\"title\":\"Sub Research B\",\"desc\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.\"},{\"title\":\"Sub Research C\",\"desc\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.\"},{\"title\":\"Sub Research D\",\"desc\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.\"},{\"title\":\"Sub Research D\",\"desc\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.\"},{\"title\":\"Sub Research D\",\"desc\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.\"},{\"title\":\"Sub Research D\",\"desc\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.\"}]},{\"title\":\"Research E\",\"data\":[{\"title\":\"Sub Research A\",\"desc\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.\"},{\"title\":\"Sub Research B\",\"desc\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.\"},{\"title\":\"Sub Research C\",\"desc\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.\"},{\"title\":\"Sub Research D\",\"desc\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.\"},{\"title\":\"Sub Research D\",\"desc\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.\"},{\"title\":\"Sub Research D\",\"desc\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.\"},{\"title\":\"Sub Research D\",\"desc\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.\"},{\"title\":\"Sub Research D\",\"desc\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.\"}]},{\"title\":\"Research F\",\"data\":[{\"title\":\"Sub Research A\",\"desc\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.\"},{\"title\":\"Sub Research B\",\"desc\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.\"},{\"title\":\"Sub Research C\",\"desc\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.\"},{\"title\":\"Sub Research D\",\"desc\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.\"},{\"title\":\"Sub Research D\",\"desc\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.\"},{\"title\":\"Sub Research D\",\"desc\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.\"},{\"title\":\"Sub Research D\",\"desc\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.\"},{\"title\":\"Sub Research D\",\"desc\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.\"}]},{\"title\":\"Research G\",\"data\":[{\"title\":\"Sub Research A\",\"desc\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.\"},{\"title\":\"Sub Research B\",\"desc\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.\"},{\"title\":\"Sub Research C\",\"desc\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.\"},{\"title\":\"Sub Research D\",\"desc\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.\"},{\"title\":\"Sub Research D\",\"desc\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.\"},{\"title\":\"Sub Research D\",\"desc\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.\"},{\"title\":\"Sub Research D\",\"desc\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.\"},{\"title\":\"Sub Research D\",\"desc\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.\"}]},{\"title\":\"Research H\",\"data\":[{\"title\":\"Sub Research A\",\"desc\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.\"},{\"title\":\"Sub Research B\",\"desc\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.\"},{\"title\":\"Sub Research C\",\"desc\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.\"},{\"title\":\"Sub Research D\",\"desc\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.\"},{\"title\":\"Sub Research D\",\"desc\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.\"},{\"title\":\"Sub Research D\",\"desc\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.\"},{\"title\":\"Sub Research D\",\"desc\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.\"},{\"title\":\"Sub Research D\",\"desc\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.\"}]},{\"title\":\"Research I\",\"data\":[{\"title\":\"Sub Research A\",\"desc\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.\"},{\"title\":\"Sub Research B\",\"desc\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.\"},{\"title\":\"Sub Research C\",\"desc\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.\"},{\"title\":\"Sub Research D\",\"desc\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.\"},{\"title\":\"Sub Research D\",\"desc\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.\"}]}]"
        var model = Gson().fromJson(json,ResearchModel::class.java)
        var layoutManagerHorizontal = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
        rv_indicator.layoutManager = layoutManagerHorizontal
        var adapterInd = ListIndicator()
        adapterInd.onViewScheduleClicked = {position ->

        }
        adapterInd.d = model
        rv_indicator.adapter = adapterInd
        var layoutManager = rv_research.layoutManager as LinearLayoutManager
        var layoutManager2 = rv_indicator.layoutManager as LinearLayoutManager
        val smoothScroller = object : LinearSmoothScroller(this) {
            override fun getVerticalSnapPreference(): Int {
                return SNAP_TO_START
            }
        }

        adapterInd.onViewScheduleClicked = { position ->
            smoothScroller.targetPosition = position
            layoutManager.startSmoothScroll(smoothScroller)
            layoutManager2.scrollToPosition(position)
        }
        val onScrollListener = object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                var position = 0
                position = if (rv_research.canScrollVertically(1)){
                    layoutManager.findFirstVisibleItemPosition()
                }else {
                    layoutManager.findLastVisibleItemPosition()
                }
                adapterInd.positioning = position
                scrollToPosition(layoutManager2,position)
                adapterInd.notifyDataSetChanged()
            }
        }
        var adapter = ListResearchAdapter()
        rv_research.isNestedScrollingEnabled = false
        adapter.d = model
        rv_research.addOnScrollListener(onScrollListener)
        rv_research.adapter = adapter
    }
    fun scrollToPosition(lm : LinearLayoutManager, position: Int){
        lm.scrollToPositionWithOffset(position - 1,0)
    }
    inner class ListIndicator : RecyclerView.Adapter<ListUsersHolder>() {
        var d = ArrayList<ResearchModelItem>()
        var positioning = 0
        var onViewScheduleClicked:(position : Int)->Unit = { _ -> }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListUsersHolder {
            return ListUsersHolder(
                LayoutInflater.from(parent.context)
                    .inflate(
                        R.layout.item_indicator,
                        parent,
                        false
                    )
            )
        }

        override fun getItemCount(): Int {
            return d.size
        }

        override fun onBindViewHolder(holder: ListUsersHolder, position: Int) {
            if (position == positioning){
                holder.itemView.tv_indc.setTextColor(
                    ContextCompat.getColor(
                        holder.itemView.context,
                        R.color.blueLightColor
                    )
                )
                holder.itemView.bar_indc.visibility = View.VISIBLE
                holder.itemView.tv_indc.setTypeface(null,Typeface.BOLD)
            } else {
                holder.itemView.tv_indc.setTextColor(Color.parseColor("#cfcfcf"))
                holder.itemView.bar_indc.visibility = View.GONE
                holder.itemView.tv_indc.setTypeface(null,Typeface.NORMAL)
            }
            if(d.isNotEmpty()){
                holder.itemView.apply {
                    tv_indc.text = d[position].title
                }
            }
            holder.itemView.setOnClickListener {
                onViewScheduleClicked(position)
            }
        }
    }

    inner class ListResearchAdapter : RecyclerView.Adapter<ListResearchAdapter.ListHolderParent>() {
        var d = ArrayList<ResearchModelItem>()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListHolderParent {
            return ListHolderParent(
                LayoutInflater.from(parent.context)
                    .inflate(
                        R.layout.list_research,
                        parent,
                        false
                    )
            )
        }

        override fun getItemCount(): Int {
            return d.size
        }

        override fun onBindViewHolder(holder: ListHolderParent, position: Int) {

            if(d.isNotEmpty()){
                holder.itemView.apply {
                    tv_title_research.text = d[position].title
                }
                holder.adapter.d = d[position].data
            }
        }

        inner class ListHolderParent(itemView: View) : RecyclerView.ViewHolder(itemView){
            var adapter = ListHolderChild()
            init {
                itemView.rv_list_research.adapter = adapter
            }
        }
    }

    inner class ListHolderChild : RecyclerView.Adapter<ListHolderChild.ListHolderChild>() {
        var d = ArrayList<Data>()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListHolderChild {
            return ListHolderChild(
                LayoutInflater.from(parent.context)
                    .inflate(
                        R.layout.item_list_research,
                        parent,
                        false
                    )
            )
        }

        override fun getItemCount(): Int {
            return d.size
        }

        override fun onBindViewHolder(holder: ListHolderChild, position: Int) {
            if(d.isNotEmpty()){
                holder.itemView.apply {
                    tv_title.text = d[position].title
                    tv_desc.text = d[position].desc
                    if (position == itemCount.minus(1)) bar_separator.visibility = View.GONE
                }
            }
        }

        inner class ListHolderChild(itemView: View) : RecyclerView.ViewHolder(itemView)
    }

    class ListUsersHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
