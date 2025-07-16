package com.malik21.malikstudentstudytime.api

import retrofit2.http.GET

data class Quote(
    val text: String,
    val author: String?
)

interface QuoteApi {
    @GET("quotes/random")
    suspend fun getRandomQuote(): Quote
}
