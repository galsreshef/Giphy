package com.thegalos.giphy.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.thegalos.giphy.data.Gif
import timber.log.Timber

/**
 * Created by Gal Reshef on 2/21/2022.
 */
class ViewGifViewModel (gif: Gif): ViewModel() {
    var item = gif

    private val _item = MutableLiveData<Gif>().apply { postValue(gif) }
    val liveItem: LiveData<Gif>
        get() = _item

    init {
        Timber.i("ViewAccessory ViewModel created: ${item.title}")
    }

    class ViewGifViewModelFactory(private val gif: Gif) :
        ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ViewGifViewModel::class.java)) {
                return ViewGifViewModel(gif) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }

    }
}