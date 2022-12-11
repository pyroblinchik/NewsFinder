package com.pyroblinchik.newsfinder.di.base

import androidx.lifecycle.ViewModel
import com.pyroblinchik.newsfinder.presentation.menu.MenuActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MenuActivityViewModel::class)
    fun bindMenuViewModel(viewModel: MenuActivityViewModel): ViewModel

}
