package com.maghraby.hyperonenews.ui.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.load.engine.Resource
import com.maghraby.hyperonenews.data.database.dao.dao
import com.maghraby.hyperonenews.data.database.entity.NewsEntity
import com.maghraby.hyperonenews.data.models.NewsResponseModel
import com.maghraby.hyperonenews.data.repository.MainRepository
//import com.maghraby.hyperonenews.utils.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val dao: dao
) : ViewModel() {
    private val _news = MutableLiveData<List<NewsEntity>>()
    val news: LiveData<List<NewsEntity>>
        get() = _news

    fun getNews() {
        viewModelScope.launch {
            mainRepository.getNews().let {
                handleNewsReponse(it)
            }
        }
    }

    fun getNewsOffline() {
        viewModelScope.launch {
            dao.getNews().let {
                _news.postValue(it.value)
            }
        }
    }


    private fun handleNewsReponse(it: Response<NewsResponseModel>) {
        it.body()?.let { it1 ->
            val newsList = arrayListOf<NewsEntity>()
            it1.articles.forEach {
                val new = NewsEntity(
                    content = it.content,
                    author = it.author,
                    title = it.title,
                    url = it.url,
                    urlToImage = it.urlToImage,
                    id = 0,
                    publishedAt = it.publishedAt,
                    description = it.description,
                    sourceId = it.source.id,
                    sourceName = it.source.name
                )
                newsList.add(new)
            }

            _news.postValue(newsList)
        }
        viewModelScope.launch {
            news.value?.forEach {
                dao.insertNews(it)
            }

        }
    }


}