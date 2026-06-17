package biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.ui.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.viewbinding.ViewBinding

/**
 * Utilitário para facilitar o uso de ViewBinding e DataBinding
 */
object ViewBindingUtils {

    /**
     * Infla um layout com ViewBinding
     * @param inflater LayoutInflater
     * @param layoutId ID do layout
     * @param parent ViewGroup pai
     * @param attachToParent Se deve anexar ao pai
     * @return ViewBinding do tipo T
     */
    inline fun <reified T : ViewBinding> inflate(
        inflater: LayoutInflater,
        @LayoutRes layoutId: Int,
        parent: ViewGroup? = null,
        attachToParent: Boolean = false
    ): T {
        return T::class.java.getMethod(
            "inflate",
            LayoutInflater::class.java,
            ViewGroup::class.java,
            Boolean::class.java
        ).invoke(null, inflater, parent, attachToParent) as T
    }

    /**
     * Obtém o binding de um item de RecyclerView
     */
    inline fun <reified T : ViewBinding> bindView(view: View): T {
        return T::class.java.getMethod("bind", View::class.java)
            .invoke(null, view) as T
    }

    /**
     * Configura um RecyclerView com ViewBinding
     */
    inline fun <reified T : ViewBinding> createBinding(
        inflater: LayoutInflater,
        @LayoutRes layoutId: Int,
        parent: ViewGroup
    ): T {
        return inflate(inflater, layoutId, parent, false)
    }

    /**
     * Obtém o ID do layout a partir do binding
     */
    fun getLayoutId(binding: ViewBinding): Int {
        return binding.root.context.resources.getIdentifier(
            binding.root.javaClass.simpleName.replace("Binding", "").lowercase(),
            "layout",
            binding.root.context.packageName
        )
    }
}