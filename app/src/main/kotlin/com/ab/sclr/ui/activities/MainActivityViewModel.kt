package com.ab.sclr.ui.activities

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ab.sclr.R
import com.ab.sclr.data.overlays.OverlayCategory
import com.ab.sclr.data.overlays.OverlaysEndpoint
import com.ab.sclr.ui.compose.Tab
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(): ViewModel() {
    @Inject lateinit var api: OverlaysEndpoint

    val selectedTab: MutableLiveData<Tab> = MutableLiveData(Tab.HOME)
    val overlays = MutableLiveData<List<OverlayCategory>>()

    fun loadOverlays() {
       viewModelScope.launch {
           val downloaded = api.listOverlays()
           overlays.postValue(downloaded);

           Timber.i("Found ${downloaded.size} overlays")
       }
    }
}
