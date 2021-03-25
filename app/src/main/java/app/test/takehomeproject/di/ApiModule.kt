package app.test.takehomeproject.di

import app.test.takehomeproject.repository.AmazonRepository
import app.test.takehomeproject.repository.DefaultAmazonRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class ApiModule {
    @Binds
    abstract fun bindAmazonRepo(impl: DefaultAmazonRepository): AmazonRepository

}