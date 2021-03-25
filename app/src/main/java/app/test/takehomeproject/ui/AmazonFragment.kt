package app.test.takehomeproject.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import app.test.takehomeproject.databinding.FragmentAmazonBinding
import app.test.takehomeproject.models.AmazonItem
import app.test.takehomeproject.ui.adapters.AmazonAdapter
import app.test.takehomeproject.viewmodels.AmazonViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


@AndroidEntryPoint
class AmazonFragment : Fragment() {
    private val viewModel: AmazonViewModel by viewModels()
    private val adapter: AmazonAdapter = AmazonAdapter(::moveToNext)
    private var currentJob: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentAmazonBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.adapter = adapter
        binding.lifecycleOwner = viewLifecycleOwner

        setupData()
        return binding.root
    }

    private fun setupData() {
        currentJob?.cancel()
        currentJob = lifecycleScope.launch {
            viewModel.readAmazonData().observe(viewLifecycleOwner, {
                adapter.submitList(it?.toMutableList())
            })
        }
    }

    private fun moveToNext(item: AmazonItem) {
        findNavController().navigate(AmazonFragmentDirections.nextAction(item))
    }

}