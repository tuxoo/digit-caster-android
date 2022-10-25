package com.tuxoo.digit_caster_android.dependency

import com.squareup.moshi.Moshi
import com.tuxoo.digit_caster_android.model.calculation.CalculationService
import com.tuxoo.digit_caster_android.model.calculation.CalculationSource
import dagger.Component
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, SourcesModule::class, ServiceModule::class])
interface AppComponent {

    val httpClient: OkHttpClient

    val retrofit: Retrofit

    val moshi: Moshi

    val calculationSource: CalculationSource

    val calculationService: CalculationService
}