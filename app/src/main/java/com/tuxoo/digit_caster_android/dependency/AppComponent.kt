package com.tuxoo.digit_caster_android.dependency

import com.tuxoo.digit_caster_android.MainActivity
import com.tuxoo.digit_caster_android.model.calculation.HistoryService
import com.tuxoo.digit_caster_android.screens.HistoryFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, SourcesModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)
    fun inject(fragment: HistoryFragment)
}