package com.thegalos.giphy

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.thegalos.giphy.databinding.GifViewFragmentBinding
import com.thegalos.giphy.viewmodel.ViewGifViewModel
import timber.log.Timber

/**
 * Created by Gal Reshef on 2/21/2022.
 */
class ViewGif : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = GifViewFragmentBinding.inflate(inflater, container, false)
        val viewModelFactory =
            ViewGifViewModel.ViewGifViewModelFactory(ViewGifArgs.fromBundle(requireArguments()).gif)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(ViewGifViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.btnShare.setOnClickListener{
            viewModel.item.fullSizeGif?.let { it1 -> shareGif(it1) }
        }
        return binding.root
    }

    private fun shareGif(url: String) {

        val packageManager = requireContext().packageManager
        val isInstalled = try {
            packageManager.getPackageInfo("com.whatsapp", 0)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            Timber.i("com.whatsapp: NameNotFoundException")
            Snackbar.make(requireView(), "Whatsapp not found", Snackbar.LENGTH_SHORT)
                .setAnimationMode(Snackbar.ANIMATION_MODE_SLIDE)
                .show()
            false
        }
        if (isInstalled) {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(
                "http://api.whatsapp.com/send?" + "text=Check This amazing " +
                        "Gif I found" + "\n" + url
            )
            startActivity(intent)
        } else {
            val uri = Uri.parse("smsto:")
            val it = Intent(Intent.ACTION_SENDTO, uri)
            it.putExtra("sms_body", "http://api.whatsapp.com/send?" +
                    "text=Check This amazing Gif I found" + "\n" + url)
            startActivity(it)
        }
    }
}