package com.example.assign2.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assign2.data.network.model.Response
import com.example.assign2.data.repositories.AuthRepository
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authRepository: AuthRepository
): ViewModel() {

    private val _userProfile = MutableSharedFlow<Response<Boolean>>()
    val userProfile = _userProfile.asSharedFlow()

    private val _userProfileDetails = MutableStateFlow<FirebaseUser?>(null)
    val userProfileDetails = _userProfileDetails.asStateFlow()

    init {
        getUser()
    }

    private fun getUser() = viewModelScope.launch {
        _userProfileDetails.value = authRepository.getUser()
    }

    fun signOutUser() = viewModelScope.launch {
        _userProfile.emit(Response.Loading)
        _userProfile.emit(authRepository.signOutUser())
    }

    fun deleteUser() = viewModelScope.launch{
        _userProfile.emit(Response.Loading)
        _userProfile.emit(authRepository.deleteAccount())
    }
}