package space.stanton.technicaltask.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import space.stanton.technicaltask.data.local.PostDAO
import space.stanton.technicaltask.data.network.ApiService
import space.stanton.technicaltask.data.repositories.PostRepository
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {
    @Singleton
    @Provides
    fun providePostRepository(apiService: ApiService, dao: PostDAO) =
        PostRepository(apiService, dao)
}