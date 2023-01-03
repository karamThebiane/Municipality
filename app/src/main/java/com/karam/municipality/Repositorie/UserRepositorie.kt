package com.karam.municipality.Repositorie

import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.*
import com.karam.municipality.Models.User

class UserRepositorie {

    private val databaseReference : DatabaseReference = FirebaseDatabase.getInstance().getReference("Users")

    @Volatile private var INSTANCE : UserRepositorie ?= null

    fun getInstance() : UserRepositorie{
        return INSTANCE ?: synchronized(this){
            val instance = UserRepositorie()
            INSTANCE = instance
            instance
        }
    }
    fun loadUsers(userList: MutableLiveData<List<User>>){
        databaseReference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                try {
                    val _userList : List<User> = snapshot.children.map {dataSnapshot ->
                        dataSnapshot.getValue(User ::class.java)!!
                    }
                    userList.postValue(_userList)

                }catch (e : Exception){

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })



    }
}