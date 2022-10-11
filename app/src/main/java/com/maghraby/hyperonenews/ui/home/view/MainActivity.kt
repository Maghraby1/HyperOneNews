package com.maghraby.hyperonenews.ui.home.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.maghraby.hyperonenews.R
import com.maghraby.hyperonenews.data.models.NewModel
import com.maghraby.hyperonenews.ui.home.adapter.NewsAdapter
import com.maghraby.hyperonenews.ui.home.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var newsRv: RecyclerView
    private val adapter : NewsAdapter by lazy { NewsAdapter(arrayListOf()) }
    private val viewModel : MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.getNews()
        setupUI()
        loadData()
    }
    lateinit var mLayoutManger: LinearLayoutManager
    private fun setupUI() {
        newsRv = findViewById(R.id.newsRv)
        mLayoutManger = LinearLayoutManager(this)
        val xLayoutManger = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        mLayoutManger.isSmoothScrollbarEnabled = true
        newsRv.layoutManager = mLayoutManger
        newsRv.adapter = adapter
    }

    private fun loadData() {
        viewModel.news.observe(this){
            renderNews(it.articles)
        }
    }
    private fun renderNews(news: List<NewModel>){
        adapter.addNews(news)
        adapter.notifyDataSetChanged()
    }
}