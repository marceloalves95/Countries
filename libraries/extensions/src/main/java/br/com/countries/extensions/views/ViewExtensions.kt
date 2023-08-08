package br.com.countries.extensions.views

import android.app.Activity
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import com.facebook.shimmer.ShimmerFrameLayout

fun View.visible(){
    visibility = View.VISIBLE
}
fun View.gone(){
    visibility = View.GONE
}
fun View.hideSoftKeyboard() {
    val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}
fun View.clickWithDebounce(debounceTime: Long = 600L, action: () -> Unit) {

    with(this){

        setOnClickListener(object : View.OnClickListener {

            private var lastClickTime: Long = 0

            override fun onClick(v: View) {
                if (SystemClock.elapsedRealtime() - lastClickTime < debounceTime) return
                else action()

                lastClickTime = SystemClock.elapsedRealtime()
            }
        })
    }
}

fun View.getLayoutContainer(): LayoutInflater {
    return LayoutInflater.from(context)
}

fun ShimmerFrameLayout.shimmerVisible(isVisible:Boolean){
    this.isVisible = isVisible
    if (isVisible){
        startShimmerAnimation()
    }else{
        stopShimmerAnimation()
    }
}