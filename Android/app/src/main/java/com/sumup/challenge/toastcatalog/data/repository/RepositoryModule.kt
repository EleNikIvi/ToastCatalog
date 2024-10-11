package com.sumup.challenge.toastcatalog.data.repository

import com.sumup.challenge.toastcatalog.domain.ItemRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {
    @Binds
    @ViewModelScoped
    fun bindAssetsRepository(repository: ItemRepositoryImpl): ItemRepository

}