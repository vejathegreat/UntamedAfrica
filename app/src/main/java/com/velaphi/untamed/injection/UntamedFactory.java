package com.velaphi.untamed.injection;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.velaphi.untamed.UntamedAfricaApp;

public class UntamedFactory extends ViewModelProvider.NewInstanceFactory {

    private UntamedAfricaApp application;

    public UntamedFactory(UntamedAfricaApp application) {
        this.application = application;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        T t = super.create(modelClass);
        if (t instanceof UntamedAfricaComponent.Injectable) {
            ((UntamedAfricaComponent.Injectable) t).inject(application.getUntamedAfricaComponent());
        }
        return t;
    }
}