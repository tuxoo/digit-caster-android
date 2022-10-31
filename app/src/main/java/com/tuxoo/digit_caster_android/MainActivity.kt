package com.tuxoo.digit_caster_android

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.tuxoo.digit_caster_android.databinding.ActivityMainBinding
import com.tuxoo.digit_caster_android.screens.calculation.CalculationFragment
import com.tuxoo.digit_caster_android.screens.contract.Navigation
import com.tuxoo.digit_caster_android.screens.help.HelpFragment
import com.tuxoo.digit_caster_android.screens.history.HistoryFragment

class MainActivity : AppCompatActivity(), Navigation {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainer, CalculationFragment())
                .commit()
        }

        binding.calculatorNav?.setOnClickListener {
            showCalculator()
        }

        binding.historyNav?.setOnClickListener {
            showHistory()
        }

        binding.helpNav?.setOnClickListener {
            showHelp()
        }
    }

    override fun showCalculator() {
        launchFragment(CalculationFragment.newInstance())
    }

    override fun showHistory() {
        launchFragment(HistoryFragment.newInstance())
    }

    override fun showHelp() {
        launchFragment(HelpFragment.newInstance())
    }

    private fun launchFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}