package com.example.attractortest.di


import com.example.attractortest.ui.images.ImagesViewModel
import com.example.attractortest.ui.profile.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


fun getAppModules() = listOf(
    viewModelModule,
)

private val viewModelModule = module {
    viewModel { ProfileViewModel() }
    viewModel { ImagesViewModel() }
}

