package com.maghraby.hyperonenews.ui.login.viewmodel

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
class LoginViewModel @Inject constructor(private val dao: dao) : ViewModel() {

    private val _user = MutableLiveData<UserEntity>()
    val user : LiveData<UserEntity>
    get() = _user

    fun login(username: String, password: String){
        viewModelScope.launch {
            dao.getUsers(username, password).let {
                println(it.value)
                _user.postValue(it.value)
            }

        }
    }

}