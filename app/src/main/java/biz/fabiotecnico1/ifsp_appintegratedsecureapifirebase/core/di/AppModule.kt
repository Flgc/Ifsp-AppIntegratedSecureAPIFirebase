package biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.core.di

import com.example.secureapp.core.network.RetrofitInstance
import com.example.secureapp.core.security.TokenManager
import com.example.secureapp.data.repository.AuthRepositoryImpl
import com.example.secureapp.data.repository.ItemRepositoryImpl
import com.example.secureapp.domain.repository.AuthRepository
import com.example.secureapp.domain.repository.ItemRepository
import com.example.secureapp.domain.usecase.*
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single { TokenManager(androidContext()) }
    single { RetrofitInstance.createApiService(get()) }
    single<AuthRepository> { AuthRepositoryImpl(get(), get()) }
    single<ItemRepository> { ItemRepositoryImpl(get()) }

    factory { LoginUseCase(get()) }
    factory { GetItemsUseCase(get()) }
    factory { CreateItemUseCase(get()) }
    factory { UpdateItemUseCase(get()) }
    factory { DeleteItemUseCase(get()) }
}