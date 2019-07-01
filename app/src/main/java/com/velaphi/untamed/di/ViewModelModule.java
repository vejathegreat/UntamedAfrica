package com.velaphi.untamed.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.velaphi.untamed.features.main.MainViewModel;
import com.velaphi.untamed.utils.ViewModelFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    abstract ViewModel bindMainViewModel(MainViewModel viewModel);

//    @Binds
//    @IntoMap
//    @ViewModelKey(RatingViewModel.class)
//    abstract ViewModel bindRatingViewModel(RatingViewModel viewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}