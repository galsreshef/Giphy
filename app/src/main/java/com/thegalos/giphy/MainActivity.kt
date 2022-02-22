package com.thegalos.giphy

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.thegalos.giphy.R
import com.thegalos.giphy.databinding.ActivityMainBinding
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mediaPlayer : MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mediaPlayer = MediaPlayer()
mediaPlayer = MediaPlayer.create(this, R.raw.dance_monkey_acustic)
        mediaPlayer.setOnPreparedListener{
            Timber.i("Media Player ready")
            mediaPlayer.setVolume(0.5f, 0.5f)
            mediaPlayer.start()
            mediaPlayer.isLooping = true
        }
    }

    override fun onStop() {
        mediaPlayer.pause()
        super.onStop()
    }

    override fun onResume() {
        mediaPlayer.start()
        super.onResume()
    }

    override fun onDestroy() {
        mediaPlayer.release()
        super.onDestroy()
    }
}