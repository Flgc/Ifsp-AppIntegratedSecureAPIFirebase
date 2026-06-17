package biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.core.di

import biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.core.network.ApiService
import biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.core.security.TokenManager
import biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.data.repository.AuthRepositoryImpl
import biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.data.repository.ItemRepositoryImpl
import biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.domain.repository.AuthRepository
import biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.domain.repository.ItemRepository
import biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.domain.usecase.*
import biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.ui.viewmodels.ItemViewModel
import biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.ui.viewmodels.LoginViewModel
import biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.ui.viewmodels.ProfileViewModel
import biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.ui.viewmodels.SettingsViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val appModule = module {
    // Network
    single {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        OkHttpClient.Builder()
            .addInterceptor(logging)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/") // Para emulador
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    // Security
    single { TokenManager(androidContext()) }

    // Repositories
    single<AuthRepository> { AuthRepositoryImpl(get(), get()) }
    single<ItemRepository> { ItemRepositoryImpl(get()) }

    // Use Cases
    factory { LoginUseCase(get()) }
    factory { GetItemsUseCase(get()) }
    factory { CreateItemUseCase(get()) }
    factory { UpdateItemUseCase(get()) }
    factory { DeleteItemUseCase(get()) }

    // ViewModels
    viewModel { LoginViewModel(get()) }
    viewModel {
        ItemViewModel(
            getItemsUseCase = get(),
            createItemUseCase = get(),
            updateItemUseCase = get(),
            deleteItemUseCase = get()
        )
    }
    viewModel { ProfileViewModel(get()) }
    viewModel { SettingsViewModel(get(), androidContext()) }
}