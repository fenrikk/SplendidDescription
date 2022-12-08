package com.iivremos.splendiddescription.view

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.iivremos.splendiddescription.data.model.FactResponseItem
import com.iivremos.splendiddescription.databinding.FragmentFactListBinding
import com.iivremos.splendiddescription.viewmodel.FactListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class FactListFragment : Fragment() {

    private lateinit var binding: FragmentFactListBinding
    private lateinit var response: List<FactResponseItem>
    private val model by viewModel<FactListViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFactListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        model.fetchFacts()
        super.onViewCreated(view, savedInstanceState)

        val factAdapter = FactAdapter(onEndReached = {
            model.fetchFacts()
        })
        binding.pager.apply {
            adapter = factAdapter
        }
        model.getFactData().observe(requireActivity()) {
            factAdapter.submitList(it)
            response = it
        }

        binding.btnBack.setOnClickListener {
            if (binding.pager.currentItem != 0) {
                binding.pager.currentItem = binding.pager.currentItem - 1
            }
        }
        binding.btnForward.setOnClickListener {
            binding.pager.currentItem = binding.pager.currentItem + 1
        }
        binding.btnCopy.setOnClickListener {
            val clipboardManager =
                requireActivity().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText(
                "Copied", response[binding.pager.currentItem].fact
            )
            clipboardManager.setPrimaryClip(clip)
            Toast.makeText(requireContext(), "Fact copied", Toast.LENGTH_SHORT).show()
        }
    }
}