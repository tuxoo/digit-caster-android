package com.tuxoo.digit_caster_android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tuxoo.digit_caster_android.databinding.ActivityMainBinding
import com.tuxoo.digit_caster_android.screens.calculation.CalculationFragment
import com.tuxoo.digit_caster_android.screens.history.HistoryFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.history?.setOnClickListener { // TODO : check ?.
//            val vis = binding.fragmentContainer.visibility
//            if (vis == View.VISIBLE) binding.fragmentContainer.visibility = View.GONE
//            else binding.fragmentContainer.visibility = View.VISIBLE
//        }

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragmentContainer, CalculationFragment())
            .commit()

        binding.historyNav?.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainer, HistoryFragment())
                .commit()
        }

        binding.calculatorNav?.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainer, CalculationFragment())
                .commit()
        }
    }

}