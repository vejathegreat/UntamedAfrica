package com.velaphi.untamed.features.home

import androidx.lifecycle.ViewModel
import com.velaphi.untamed.injection.UntamedAfricaComponent

class HomeViewModel : ViewModel(), UntamedAfricaComponent.Injectable {

    override fun inject(untamedAfricaComponent: UntamedAfricaComponent) {
        untamedAfricaComponent.inject(this)
    }
}