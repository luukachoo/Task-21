package com.example.task21.domain.remote.model

data class GetProduct (
    val cover: String,
    val favorite: Boolean,
    val id: Int,
    val price: String,
    val title: String
)