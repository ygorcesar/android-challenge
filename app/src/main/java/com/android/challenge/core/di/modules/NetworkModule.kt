package com.android.challenge.core.di.modules

import com.android.challenge.BuildConfig
import com.android.challenge.base.common.exception.HttpError
import com.android.challenge.core.ApiConfig
import com.android.challenge.core.di.scopes.ApplicationScope
import com.android.challenge.home.data.ApiServiceGateway
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @ApplicationScope
    fun provideRetrofitRefreshToken(
        converter: Converter.Factory,
        apiConfig: ApiConfig.Sandbox,
        httpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(apiConfig.baseUrl)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(converter)
        .client(httpClient)
        .build()

    @Provides
    @Singleton
    fun provideGoogleApiConfig(): ApiConfig.Sandbox = ApiConfig.Sandbox()

    @Provides
    @ApplicationScope
    fun provideConverter(moshi: Moshi): Converter.Factory = MoshiConverterFactory.create(moshi)

    @Provides
    @ApplicationScope
    fun provideLogger(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) = Timber.d(message)
        })
    }

    @Provides
    @ApplicationScope
    fun provideGoogleClient(
        logger: HttpLoggingInterceptor,
        interceptor: RequestInterceptor,
        sandboxConfig: ApiConfig.Sandbox
    ): OkHttpClient {

        val okHttpClientBuilder = OkHttpClient.Builder()
        okHttpClientBuilder.readTimeout(sandboxConfig.networkTimeout, TimeUnit.SECONDS)
        okHttpClientBuilder.connectTimeout(sandboxConfig.networkTimeout, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            logger.level = HttpLoggingInterceptor.Level.BODY
            okHttpClientBuilder.addInterceptor(logger)
        }

        okHttpClientBuilder.addInterceptor(interceptor)

        return okHttpClientBuilder.build()
    }

    @Provides
    @ApplicationScope
    fun provideApiService(retrofit: Retrofit): ApiServiceGateway = retrofit.create()

    /**
     * Custom interceptor to handle every request and map correct response
     * <b>No-Authentication</b> header parameter is missing
     */
    @Singleton
    class RequestInterceptor @Inject constructor() : Interceptor {

        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()
            val response = chain.proceed(request)
            when (response.code) {
                in 200..206 -> Timber.d("Request success!")
                else -> throw HttpError
            }
            return response
        }
    }
}
