package com.android.challenge.core

import com.android.challenge.BuildConfig

interface ApiConfig {

    val baseUrl: String
    val networkTimeout: Long

    class Sandbox : ApiConfig {
        override val baseUrl: String get() = BuildConfig.API_URL
        override val networkTimeout: Long get() = 30L
    }
}
