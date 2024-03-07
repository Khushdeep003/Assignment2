package com.example.assign2.data.network.model

data class Auth(
    val password: String,
    val authGoogle: Boolean,
    val authEmail: Boolean
)