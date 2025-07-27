package com.jpn.feature.pwd.di

import com.jpn.domain.profile.repository.PwdRepository
import com.jpn.domain.profile.usecase.AddPwdUseCase
import com.jpn.domain.profile.usecase.GetPwdByIdUseCase
import com.jpn.domain.profile.usecase.GetPwdsUseCase
import com.jpn.domain.profile.usecase.UpdatePwdUseCase
import com.jpn.feature.pwd.presentation.list.PwdsViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PwdModule {
  /*  @Provides
    @Singleton
    fun provideLogManager(
    ): LogManager {
        return TimberLogManager()
    }*/

/*    @Provides
    @Singleton
    fun providePwdRepository(
        pwdDao: PwdDao,
        logManager: LogManager
    ): PwdRepository {
        return PwdRepositoryImpl(pwdDao, logManager)
    }*/

    @Provides
    @Singleton
    fun provideGetPwdsUseCase(
        repository: PwdRepository
    ): GetPwdsUseCase {
        return GetPwdsUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideAddPwdUseCase(
        repository: PwdRepository
    ): AddPwdUseCase {
        return AddPwdUseCase(repository)
    }

    @Provides
    fun providePwdsViewModel(
        getPwdUseCase: GetPwdsUseCase,
        addPwdUseCase: AddPwdUseCase
    ): PwdsViewModel {
        return PwdsViewModel(getPwdUseCase, addPwdUseCase)
    }

    @Provides
    @Singleton
    fun provideUpdatePwdUseCase(
        repository: PwdRepository
    ): UpdatePwdUseCase {
        return UpdatePwdUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetPwdByIdUseCase(
        repository: PwdRepository
    ): GetPwdByIdUseCase {
        return GetPwdByIdUseCase(repository)
    }

    /*@Provides
    @Singleton
    fun providePwdDetailViewModel(
        getPwdByIdUseCase: GetPwdByIdUseCase,
        updatePwdUseCase: UpdatePwdUseCase,
        resourceManager: ResourceManager,
        logManager: LogManager
    ): PwdDetailViewModel {
        return PwdDetailViewModel(getPwdByIdUseCase, updatePwdUseCase, resourceManager,logManager)
    }


    @Provides
    @Singleton
    fun provideEditPwdViewModel(
        getPwdByIdUseCase: GetPwdByIdUseCase,
        updatePwdUseCase: UpdatePwdUseCase,
        resourceManager: ResourceManager,
        logManager: LogManager
    ): EditPwdViewModel {
        return EditPwdViewModel(getPwdByIdUseCase, updatePwdUseCase, resourceManager,logManager)
    }*/
}
