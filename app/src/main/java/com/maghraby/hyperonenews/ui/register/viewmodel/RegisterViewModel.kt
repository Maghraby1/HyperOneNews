package com.maghraby.hyperonenews.ui.register.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maghraby.hyperonenews.data.database.dao.dao
import com.maghraby.hyperonenews.data.database.entity.UserEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val dao: dao) : ViewModel(){

    var isSuccess = MutableLiveData(false)
    fun addUser(userEntity: UserEntity){
        viewModelScope.launch {
            dao.registerUser(userEntity).let {
//                print(it)
//                println(it)
                if (it!=null){
                    isSuccess.postValue(true)
                }else{
                    isSuccess.postValue(false)
                }
            }

        }
    }
}