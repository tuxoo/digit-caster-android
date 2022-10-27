package com.tuxoo.digit_caster_android.screens.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.tuxoo.digit_caster_android.HistoryAdapter
import com.tuxoo.digit_caster_android.databinding.FragmentHistoryBinding
import com.tuxoo.digit_caster_android.model.history.HistoryListener
import com.tuxoo.digit_caster_android.model.history.HistoryService
import com.tuxoo.digit_caster_android.util.HistoryViewModelFactory
import com.tuxoo.digit_caster_android.util.appComponent
import javax.inject.Inject

class HistoryFragment : Fragment() {

    private lateinit var binding: FragmentHistoryBinding
    private lateinit var adapter: HistoryAdapter

    @Inject
    lateinit var historyService: HistoryService

    private val viewModel: HistoryViewModel by viewModels {
        HistoryViewModelFactory(historyService)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHistoryBinding.inflate(inflater, container, false)

        requireContext().applicationContext.appComponent.inject(this)

        adapter = HistoryAdapter()

        viewModel.history.observe(viewLifecycleOwner) {
            adapter.history = it
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        return binding.root
    }
}