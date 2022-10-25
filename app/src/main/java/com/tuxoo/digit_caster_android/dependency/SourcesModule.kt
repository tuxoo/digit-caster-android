package com.tuxoo.digit_caster_android.dependency

import com.tuxoo.digit_caster_android.model.calculation.CalculationSource
import com.tuxoo.digit_caster_android.sources.calculation.RetrofitCalculationSource
import dagger.Binds
import dagger.Module

@Module(includes = [SourcesBindModule::class])
class SourcesModule

@Module
abstract class SourcesBindModule {

    @Binds
    abstract fun bindCalculationSource(
        retrofitCalculationSource: RetrofitCalculationSource
    ): CalculationSource
}