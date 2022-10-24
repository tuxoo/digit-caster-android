package com.tuxoo.digit_caster_android.sources

import com.squareup.moshi.Moshi
import com.tuxoo.digit_caster_android.Const
import com.tuxoo.digit_caster_android.model.SourcesProvider
import com.tuxoo.digit_caster_android.sources.base.RetrofitConfig
import com.tuxoo.digit_caster_android.sources.base.RetrofitSourcesProvider
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object SourceProviderHolder {

    val sourcesProvider: SourcesProvider by lazy<SourcesProvider> {
        val moshi = Moshi.Builder().build()
        val config = RetrofitConfig(
            retrofit = createRetrofit(moshi),
            moshi = moshi
        )
        RetrofitSourcesProvider(config)
    }

    private fun createRetrofit(moshi: Moshi): Retrofit =
        Retrofit.Builder()
            .baseUrl(Const.BASE_URL)
            .client(createOkHttpClient())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

    private fun createOkHttpClient(): OkHttpClient =
        OkHttpClient().newBuilder()
            .addInterceptor(createLoggingInterceptor())
            .build()

    private fun createLoggingInterceptor(): Interceptor =
        HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
}