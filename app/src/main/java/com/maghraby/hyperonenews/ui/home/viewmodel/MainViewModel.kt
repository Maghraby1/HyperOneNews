package com.maghraby.hyperonenews.ui.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.load.engine.Resource
import com.maghraby.hyperonenews.data.models.NewsResponseModel
import com.maghraby.hyperonenews.data.repository.MainRepository
//import com.maghraby.hyperonenews.utils.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository
 ) : ViewModel(){
    private val _news = MutableLiveData<NewsResponseModel>()
    val news: LiveData<NewsResponseModel>
        get() = _news

    fun getNews(){
        viewModelScope.launch {
                mainRepository.getNews().let {
                    handleNewsReponse(it)
                }
        }
    }

    private fun handleNewsReponse(it: Response<NewsResponseModel>) {
        it.body()?.let {
            _news.postValue(it)
        }
    }
}