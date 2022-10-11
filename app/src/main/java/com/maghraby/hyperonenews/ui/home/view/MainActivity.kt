package com.maghraby.hyperonenews.ui.home.view

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.maghraby.hyperonenews.R
import com.maghraby.hyperonenews.data.database.entity.NewsEntity
import com.maghraby.hyperonenews.data.models.NewModel
import com.maghraby.hyperonenews.data.models.Source
import com.maghraby.hyperonenews.ui.home.adapter.NewsAdapter
import com.maghraby.hyperonenews.ui.home.adapter.SourcesAdapter
import com.maghraby.hyperonenews.ui.home.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var newsRv: RecyclerView
    private lateinit var sourcesRv: RecyclerView
    private val adapter : NewsAdapter by lazy { NewsAdapter(arrayListOf()) }
    private val sourcesAdapter : SourcesAdapter by lazy { SourcesAdapter(arrayListOf()) }
    private val viewModel : MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(isNetworkConnected()){
            viewModel.getNews()
        }else{
            viewModel.getNewsOffline()
        }
        setupUI()
        loadData()
    }
    lateinit var mLayoutManger: LinearLayoutManager
    private fun setupUI() {
        newsRv = findViewById(R.id.newsRv)
        sourcesRv = findViewById(R.id.sourcesRv)
        mLayoutManger = LinearLayoutManager(this)
        mLayoutManger.isSmoothScrollbarEnabled = true
        newsRv.layoutManager = mLayoutManger
        newsRv.adapter = adapter
        val xLayoutManger = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        sourcesRv.layoutManager = xLayoutManger
        sourcesRv.adapter = sourcesAdapter
    }

    private fun loadData() {
        viewModel.news.observe(this){

            renderNews(it?: arrayListOf())
        }
    }
    private fun renderNews(news: List<NewsEntity>){
        adapter.addNews(news)
        val sources = arrayListOf<Source>()
        news.forEach {
            if(it.sourceId!= null && it.sourceName!=null) {
                val source = Source(it.sourceId, it.sourceName)
                sources.add(source)
            }
        }
        sourcesAdapter.addSources(sources)
        sourcesAdapter.notifyDataSetChanged()
        adapter.notifyDataSetChanged()
    }

        private fun isNetworkConnected(): Boolean {
        var result = false
        val connectivityManager =
            this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val activeNetwork =
                connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
            result = when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.run {
                connectivityManager.activeNetworkInfo?.run {
                    result = when (type) {
                        ConnectivityManager.TYPE_WIFI -> true
                        ConnectivityManager.TYPE_MOBILE -> true
                        ConnectivityManager.TYPE_ETHERNET -> true
                        else -> false
                    }

                }
            }
        }

        return result
    }
}