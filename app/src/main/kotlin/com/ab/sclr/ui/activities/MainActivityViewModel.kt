package com.ab.sclr.ui.activities

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ab.sclr.R

class MainActivityViewModel: ViewModel() {
    val selectedTab: MutableLiveData<Tab> = MutableLiveData(Tab.HOME)
}

enum class Tab(val title: String, val icon: Int) {
    HOME("Templates", R.drawable.ic_home_templates),
    SAVED("Saved", R.drawable.ic_home_saved)
}
