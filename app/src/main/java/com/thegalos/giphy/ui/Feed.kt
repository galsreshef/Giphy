package com.thegalos.giphy.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.thegalos.giphy.adapter.Adapter
import com.thegalos.giphy.databinding.FragmentFeedBinding
import com.thegalos.giphy.util.MyApplication
import com.thegalos.giphy.viewmodel.FeedViewModel2
import com.thegalos.giphy.viewmodel.FeedViewModelFactory
import timber.log.Timber

/**
 * Created by Gal Reshef on 2/21/2022.
 */

class Feed : Fragment() {

    private lateinit var binding: FragmentFeedBinding

    private  val viewModel : FeedViewModel2 by viewModels {
        FeedViewModelFactory((requireActivity().application as MyApplication))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFeedBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val adapter = Adapter(Adapter.GifClickListener { gif ->
            viewModel.gifDetails(gif)
        })

        binding.rvGifs.adapter = adapter

        binding.ivSearch.setOnClickListener{
            val a = binding.etSearch.text.toString()
            Timber.i("Searching for $a in Giphy")
            if (a.isNotEmpty())
                viewModel.searchGif(a)
        }

        viewModel.search.observe(viewLifecycleOwner) {
            Timber.i("viewModel observe search")
        }

        viewModel.gifList.observe(viewLifecycleOwner) {
            Timber.i("viewModel observe gifList")
            it?.let {
                adapter.submitList(it)
                adapter.notifyDataSetChanged()
            }
            viewModel.gifSearchComplete()
        }

        viewModel.selectedGif.observe(viewLifecycleOwner) {
            if (it != null) {
                Timber.i("Item in recycler clicked, attempting opening")
                val action = FeedDirections.actionFeedToGif(it)
                findNavController()
                    .navigate(action)
                viewModel.gifDetailsComplete()
            }
        }

        return binding.root
    }
}
