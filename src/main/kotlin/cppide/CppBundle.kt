/* AdvancedTools, 2007, all rights reserved */
package cppide

import com.intellij.CommonBundle
import com.intellij.reference.SoftReference
import org.jetbrains.annotations.NonNls
import org.jetbrains.annotations.PropertyKey

import java.lang.ref.Reference
import java.util.ResourceBundle

/**
 * @author maxim
 * Date: 21.09.2006
 * Time: 5:05:57
 */
object CppBundle {

    private var ourBundle: Reference<ResourceBundle>? = null

    private const val BUNDLE = "cppide.CppBundle"

    private val bundle: ResourceBundle
        get() {
            var bundle: ResourceBundle? = null
            if (ourBundle != null) bundle = ourBundle!!.get()
            if (bundle == null) {
                bundle = ResourceBundle.getBundle(BUNDLE)
                ourBundle = SoftReference(bundle)
            }
            return bundle!!
        }

    fun message(@NonNls @PropertyKey(resourceBundle = BUNDLE) key: String, vararg params: Any): String {
        return CommonBundle.message(bundle, key, *params)
    }
}
