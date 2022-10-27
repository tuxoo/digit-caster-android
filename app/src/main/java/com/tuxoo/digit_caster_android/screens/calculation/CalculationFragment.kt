package com.tuxoo.digit_caster_android.screens.calculation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.forEach
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.tuxoo.digit_caster_android.databinding.FragmentCalculatorBinding
import com.tuxoo.digit_caster_android.util.CalculationViewModelFactory
import com.tuxoo.digit_caster_android.util.GenericSavedStateViewModelFactory
import com.tuxoo.digit_caster_android.util.appComponent
import com.tuxoo.digit_caster_android.util.observeEvent
import javax.inject.Inject

class CalculationFragment : Fragment() {

    private lateinit var binding: FragmentCalculatorBinding
    private val viewModel: CalculationViewModel by viewModels {
        GenericSavedStateViewModelFactory(calculationViewModelFactory, this)
    }

    @Inject
    lateinit var calculationViewModelFactory: CalculationViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCalculatorBinding.inflate(inflater, container, false)

        requireContext().applicationContext.appComponent.inject(this)

        viewModel.calculation.observe(viewLifecycleOwner) {
            binding.currentNumber.setText(it.currentNum)
            binding.operation.text = it.operation
            binding.previousNumber.text = it.previousNum
        }

        viewModel.showToastEvent.observeEvent(viewLifecycleOwner) {
            Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
        }

        binding.delete.setOnClickListener {
            viewModel.eraseOne()
        }

        binding.erase.setOnClickListener {
            viewModel.erase()
        }

        binding.equal.setOnClickListener {
            viewModel.getResult()
        }

        setClickListeners()

        return binding.root
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