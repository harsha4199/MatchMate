package com.example.matchmate.model


data class CardModel(
    val results: List<ResultsParse>,
    val info: Info
)


data class ResultsParse(
    val gender: String,
    val name: Name,
    val location: Location,
    val email: String,
    val phone: String,
    val cell: String,
    val picture: Picture,
    val nat: String
)


data class Name(

    val title: String,
    val first: String,
    val last: String
)


data class Street(

    val number: String,
    val name: String
)


data class Location(
    val street: Street,
    val city: String,
    val state: String,
    val country: String,
    val postcode: String,
)


data class Picture(
    val large: String,
    )


data class Info(

    val seed: String,
    val results: String,
    val page: String,
)