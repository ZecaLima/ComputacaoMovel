package com.example.web_services.api

data class User(
    val id: Int,
    val name: String,
    val email: String,
    val address: Address
)
data class Address(
    val city: String,
    val zipcode: String
)

