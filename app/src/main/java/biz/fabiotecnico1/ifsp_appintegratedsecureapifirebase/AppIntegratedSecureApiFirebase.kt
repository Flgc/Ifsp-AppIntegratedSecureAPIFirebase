package biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase

import android.app.Application
import biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.core.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class AppIntegratedSecureApiFirebase : Application() {
    override fun onCreate() {
        super.onCreate()

        // Inicializa o Koin (Injeção de Dependência)
        startKoin {
            androidLogger(Level.DEBUG) // Para debug
            androidContext(this@AppIntegratedSecureApiFirebase)
            modules(appModule)
        }
    }
}