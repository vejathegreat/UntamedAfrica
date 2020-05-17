package com.velaphi.untamed.injection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.velaphi.untamed.UntamedAfricaApp

class UntamedFactory(private val application: UntamedAfricaApp) : ViewModelProvider.NewInstanceFactory()  {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val t = super.create(modelClass)
        if (t is UntamedAfricaComponent.Injectable) {
            (t as UntamedAfricaComponent.Injectable).inject(application.untamedAfricaComponent)
        }
        return t
    }

}