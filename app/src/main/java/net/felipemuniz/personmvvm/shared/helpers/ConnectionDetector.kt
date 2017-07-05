package net.felipemuniz.personmvvm.shared.helpers

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

/**
 * Created by Muniz on 05/07/2017.
 */
class ConnectionDetector
/**
 * This method is used to initialize the ConnectionDetector
 * @param context This is the context of activity
 */
(private val context: Context) {

    /**
     * Method utilized for check the conection of internet
     * @return boolean Thi is boolean value for internet connection
     */
    val isConnectingToInternet: Boolean
        get() {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        }
}