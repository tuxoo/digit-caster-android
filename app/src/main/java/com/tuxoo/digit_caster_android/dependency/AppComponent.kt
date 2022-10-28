package com.tuxoo.digit_caster_android.dependency

import android.content.Context
import com.tuxoo.digit_caster_android.screens.calculation.CalculationFragment
import com.tuxoo.digit_caster_android.screens.history.HistoryFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class,
        DatabaseModule::class,
        SourcesModule::class
    ]
)
interface AppComponent {

    fun inject(fragment: HistoryFragment)
    fun inject(fragment: CalculationFragment)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun applicationContext(context: Context): Builder

        fun build(): AppComponent
    }
}