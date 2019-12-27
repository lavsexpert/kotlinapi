package com.example.kotlinapi

data class Result (
    val total_count: Int,
    val incomplete_results: Boolean,
    val items: List<User>
)