package biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.core.di

import biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.core.network.RetrofitInstance
import biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.core.security.TokenManager
import biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.data.repository.AuthRepositoryImpl
import biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.data.repository.ItemRepositoryImpl
import biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.domain.repository.AuthRepository
import biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.domain.repository.ItemRepository
import biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.domain.usecase.*
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    // Security
    single { TokenManager(androidContext()) }

    // Network
    single { RetrofitInstance.createApiService(get()) }

    // Repositories
    single<AuthRepository> { AuthRepositoryImpl(get(), get()) }
    single<ItemRepository> { ItemRepositoryImpl(get()) }

    // UseCases - Auth
    factory { LoginUseCase(get()) }

    // UseCases - Items
    factory { GetItemsUseCase(get()) }
    factory { CreateItemUseCase(get()) }
    factory { UpdateItemUseCase(get()) }
    factory { DeleteItemUseCase(get()) }
}