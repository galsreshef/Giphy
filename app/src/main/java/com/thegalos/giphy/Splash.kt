package com.thegalos.giphy

import android.animation.Animator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import timber.log.Timber
import androidx.fragment.app.Fragment
import com.thegalos.giphy.databinding.SplashFragmentBinding

/**
 * Created by Gal Reshef on 2/21/2022.
 */
class Splash : Fragment() {
    private lateinit var binding: SplashFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = SplashFragmentBinding.inflate(inflater, container, false)
        binding.tvAppVersion.text = String.format(getString(R.string.version),BuildConfig.VERSION_NAME)
        binding.lottie.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator?) {
                Timber.i("onAnimationStart") }

            override fun onAnimationEnd(animation: Animator?) {
                findNavController().navigate(R.id.action_splash_to_feed)
                Timber.i("No user navigating to Login")
            }
            override fun onAnimationCancel(animation: Animator?) {
                Timber.i("onAnimationCancel") }

            override fun onAnimationRepeat(animation: Animator?) {
                Timber.i("onAnimationRepeat") }
        })
        return binding.root
    }
}