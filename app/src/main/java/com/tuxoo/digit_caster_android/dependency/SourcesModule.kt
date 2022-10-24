package com.tuxoo.digit_caster_android.dependency

import com.tuxoo.digit_caster_android.model.calculation.CalculationSource
import com.tuxoo.digit_caster_android.sources.calculation.RetrofitCalculationSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SourcesModule {

    @Binds
    abstract fun bindCalculationSource(
        retrofitCalculationSource: RetrofitCalculationSource
    ): CalculationSource
}