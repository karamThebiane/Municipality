package com.karam.municipality.Models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.karam.municipality.Repositorie.UserRepositorie

class UserViewModel: ViewModel() {
    private val repositorie : UserRepositorie
    private val _allUsers = MutableLiveData<List<User>>()
    val allUsers : LiveData<List<User>> = _allUsers


    init {
        repositorie = UserRepositorie().getInstance()
        repositorie.loadUsers(_allUsers)
    }
}