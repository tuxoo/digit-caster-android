package com.tuxoo.digit_caster_android.dependency

import com.squareup.moshi.Moshi
import com.tuxoo.digit_caster_android.model.calculation.CalculationSource
import com.tuxoo.digit_caster_android.sources.base.RetrofitConfig
import com.tuxoo.digit_caster_android.sources.calculation.RetrofitCalculationSource
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class SourcesModule {

    @Provides
    @Singleton
    fun provideRetrofitConfig(
        retrofit: Retrofit,
        moshi: Moshi
    ): RetrofitConfig = RetrofitConfig(retrofit, moshi)

    @Provides
    @Singleton
    fun provideCalculationSource(
        config: RetrofitConfig
    ): CalculationSource = RetrofitCalculationSource(config)
}