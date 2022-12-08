package com.iivremos.splendiddescription.other

import com.test.factsapp.data.FactApi
import com.iivremos.splendiddescription.viewmodel.FactListViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {
    single<FactApi> {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder().addInterceptor {
            val originalRequest = it.request()
            val newHttpUrl =
                originalRequest.url.newBuilder().build()
            val newRequest = originalRequest.newBuilder().url(newHttpUrl).build()
            it.proceed(newRequest)
        }.addInterceptor(httpLoggingInterceptor).build()

        val retrofit = Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create()).build()
        retrofit.create(FactApi::class.java)
    }
}
val viewModelModule = module {
    viewModel { FactListViewModel(get()) }
}