package com.thegalos.giphy.viewmodel

import androidx.lifecycle.*
import com.thegalos.giphy.data.Gif
import com.thegalos.giphy.util.MyApplication
import com.thegalos.giphy.network.ApiRequests
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

/**
 * Created by Gal Reshef on 2/21/2022.
 */
class FeedViewModel2(application: MyApplication): AndroidViewModel(application) {

    private val baseUrl = "https://api.giphy.com/"
    private val apiKey = "Zz7XnA0RZzJJetQAQv1e2c7ErivA9F5u"
    var list : MutableList<Gif> = arrayListOf()
    private val _gifList = MutableLiveData<List<Gif>>()
    val gifList: LiveData<List<Gif>>
        get() = _gifList

    private val _selectedGif: MutableLiveData<Gif> = MutableLiveData()
    val selectedGif: LiveData<Gif>
        get() = _selectedGif

    private val _search: MutableLiveData<String> = MutableLiveData()
    val search: LiveData<String>
        get() = _search

    init {
        Timber.i("Main screen ViewModel created")
        getGifs(q = "Monkey")
    }

    private fun getGifs(q: String) {
        val gfRequest = Retrofit.Builder()
            .baseUrl(baseUrl)

            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiRequests::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            Timber.i("Starting coroutines")
            list.clear()
            val response = gfRequest.getGif(apiKey = apiKey, q = q).awaitResponse()
            if (response.isSuccessful) {
                val gifItem = response.body()!!
                for (i in 1..49) {
                    val gf = Gif()
                    gf.id = gifItem.data[i].id
                    gf.title = gifItem.data[i].title
                    gf.type = gifItem.data[i].type
                    gf.fullSizeGif = gifItem.data[i].images.original.url
                    gf.downsizedGif = gifItem.data[i].images.downsized.url
                    Timber.i("item number $i is:\n$gf")
                    list.add(gf)
                }

                withContext(Dispatchers.Main){
                    Timber.i("Updating UI here")
                    _gifList.value = list
                }
            } else
                Timber.i ("data is not received")
        }

    }

    fun gifDetails(gif: Gif) {
        _selectedGif.value = gif
    }

    fun gifDetailsComplete() {
        _selectedGif.value = null
    }

    fun searchGif(a: String) {
        _search.value = a
        getGifs(a)
    }

    fun gifSearchComplete() {
        _search.value = null
    }

}

class FeedViewModelFactory(private val application: MyApplication) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FeedViewModel2::class.java)) {
            return FeedViewModel2(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}