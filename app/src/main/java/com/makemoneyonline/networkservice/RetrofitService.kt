package com.makemoneyonline.networkservice

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class RetrofitService {
    companion object {
        private const val BASE_URL = "https://adsandurlapp.com/driverapp/"
    }

    private fun getClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().addInterceptor(HeaderIntercepter())
            .readTimeout(2, TimeUnit.MINUTES).writeTimeout(2, TimeUnit.MINUTES).build()
    }


    class HeaderIntercepter : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response = chain.run {
            proceed(
                request()
                    .newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Appversion", "1.0")
                    .addHeader("Ostype","Android")
                    .addHeader("Accesstoken","20813813795e020a181d81b1.62428545")
                    .addHeader("Userid","7")
                    .build()
            )
        }
    }


    fun getServiceApi(retrofit: Retrofit) = retrofit.create(ApiService::class.java)

    fun getRetrofit(baseurl: String) = Retrofit.Builder()
        .baseUrl(baseurl)
        .client(getClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()


//    val apiService=getServiceApi(getRetrofit(BASE_URL))
//
//
//    val apiFeed=getServiceApi(getRetrofit(BASE_URL_FEED))
//
//    val apiLog=getServiceApi(getRetrofit(BASE_URL_LOG))
//
//    val dmTest=getServiceApi(getRetrofit(BASE_TEST_DM))
//
//    val paymentTest=getServiceApi(getRetrofit(BASE_PAYMENT_TEST))
//
//    val nearGroupBase=getServiceApi(getRetrofit(BASE_NEARGROUP))
//
//    val paymentTestNew=getServiceApi(getRetrofit(BASE_PAYMENT))

      val neargroupTest: ApiService = getServiceApi(getRetrofit(BASE_URL))

    // val testBaseChat=getServiceApi(getRetrofit(TEST_BASE_CHAT))


}
