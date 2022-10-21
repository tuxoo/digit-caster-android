package com.tuxoo.digit_caster_android

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.forEach
import com.tuxoo.digit_caster_android.databinding.ActivityMainBinding
import com.tuxoo.digit_caster_android.screens.CalculationViewModel
import com.tuxoo.digit_caster_android.screens.factory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: CalculationViewModel by viewModels { factory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.calculation.observe(this) {
            binding.currentNumber.setText(it.currentNum)
        }

        binding.erase.setOnClickListener {
            viewModel.eraseOne()
        }

        binding.erase.setOnLongClickListener {
            viewModel.erase()
        }

        digitButtonClickListener()
    }

    private fun digitButtonClickListener(): Unit =
        binding.gridLayout.forEach {
            if (it is AppCompatButton && it.tag == "digit") {
                with(it.text.toString()) {
                    it.setOnClickListener {
                        viewModel.addDigit(this)
                    }
                }
            }
        }

}