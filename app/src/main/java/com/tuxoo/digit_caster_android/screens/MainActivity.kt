package com.tuxoo.digit_caster_android.screens

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.forEach
import com.tuxoo.digit_caster_android.databinding.ActivityMainBinding
import com.tuxoo.digit_caster_android.util.factory
import com.tuxoo.digit_caster_android.util.observeEvent

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: CalculationViewModel by viewModels { factory(this) } // TODO: now work state handle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.calculation.observe(this) {
            binding.currentNumber.setText(it.currentNum)
            binding.operation.text = it.operation
            binding.previousNumber.text = it.previousNum
        }

        viewModel.showToastEvent.observeEvent(this) {
            Toast.makeText(this.applicationContext, it, Toast.LENGTH_SHORT).show()
        }

        binding.erase.setOnClickListener {
            viewModel.eraseOne()
        }

        binding.erase.setOnLongClickListener {
            viewModel.erase()
        }

        binding.equal.setOnClickListener {
            viewModel.getResult()
        }

        setClickListeners()
    }

    private fun setClickListeners(): Unit =
        binding.gridLayout.forEach {
            if (it is AppCompatButton) {
                with(it.text.toString()) {
                    when (it.tag) {
                        DIGIT -> it.setOnClickListener {
                            viewModel.addDigit(this)
                        }
                        OPERATION -> it.setOnClickListener {
                            viewModel.setOperation(this)
                        }
                    }
                }
            }
        }

    companion object {
        private const val DIGIT = "digit"
        private const val OPERATION = "operation"
    }
}