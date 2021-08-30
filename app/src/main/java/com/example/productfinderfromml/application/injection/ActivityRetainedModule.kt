package com.example.productfinderfromml.application.injection

import com.example.productfinderfromml.domain.Repository
import com.example.productfinderfromml.domain.DefaultRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ActivityRetainedModule {
    @Binds
    abstract fun dataSource(impl: DefaultRepository): Repository
}