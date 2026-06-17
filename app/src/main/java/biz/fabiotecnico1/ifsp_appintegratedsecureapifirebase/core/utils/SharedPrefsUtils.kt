package biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.core.utils

import android.content.Context
import android.content.SharedPreferences

/**
 * Utilitário para gerenciar SharedPreferences de forma segura e eficiente
 */
object SharedPrefsUtils {

    private const val PREF_NAME = "secureapp_preferences"
    private const val MODE = Context.MODE_PRIVATE

    /**
     * Obtém uma instância de SharedPreferences
     */
    fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_NAME, MODE)
    }

    /**
     * Salva um valor booleano
     */
    fun putBoolean(context: Context, key: String, value: Boolean) {
        getPreferences(context).edit().putBoolean(key, value).apply()
    }

    /**
     * Recupera um valor booleano
     */
    fun getBoolean(context: Context, key: String, defaultValue: Boolean = false): Boolean {
        return getPreferences(context).getBoolean(key, defaultValue)
    }

    /**
     * Salva um valor String
     */
    fun putString(context: Context, key: String, value: String) {
        getPreferences(context).edit().putString(key, value).apply()
    }

    /**
     * Recupera um valor String
     */
    fun getString(context: Context, key: String, defaultValue: String = ""): String {
        return getPreferences(context).getString(key, defaultValue) ?: defaultValue
    }

    /**
     * Salva um valor Int
     */
    fun putInt(context: Context, key: String, value: Int) {
        getPreferences(context).edit().putInt(key, value).apply()
    }

    /**
     * Recupera um valor Int
     */
    fun getInt(context: Context, key: String, defaultValue: Int = 0): Int {
        return getPreferences(context).getInt(key, defaultValue)
    }

    /**
     * Salva um valor Long
     */
    fun putLong(context: Context, key: String, value: Long) {
        getPreferences(context).edit().putLong(key, value).apply()
    }

    /**
     * Recupera um valor Long
     */
    fun getLong(context: Context, key: String, defaultValue: Long = 0L): Long {
        return getPreferences(context).getLong(key, defaultValue)
    }

    /**
     * Salva um valor Float
     */
    fun putFloat(context: Context, key: String, value: Float) {
        getPreferences(context).edit().putFloat(key, value).apply()
    }

    /**
     * Recupera um valor Float
     */
    fun getFloat(context: Context, key: String, defaultValue: Float = 0f): Float {
        return getPreferences(context).getFloat(key, defaultValue)
    }

    /**
     * Remove uma chave específica
     */
    fun remove(context: Context, key: String) {
        getPreferences(context).edit().remove(key).apply()
    }

    /**
     * Limpa todas as preferências
     */
    fun clear(context: Context) {
        getPreferences(context).edit().clear().apply()
    }

    /**
     * Verifica se uma chave existe
     */
    fun contains(context: Context, key: String): Boolean {
        return getPreferences(context).contains(key)
    }
}