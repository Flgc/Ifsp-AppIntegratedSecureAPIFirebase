package biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase

import android.app.Application
import biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.core.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppIntegratedSecureApiFirebase : Application() {
    override fun onCreate() {
        super.onCreate()

        // Inicializa o Koin (Injeção de Dependência)
        startKoin {
            androidContext(this@AppIntegratedSecureApiFirebase)
            modules(appModule)
        }
    }
}