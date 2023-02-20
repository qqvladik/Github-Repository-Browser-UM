package pl.mankevich.githubrepositorybrowserum.core.utils.extensions

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

fun Context.isNetworkAvailable(): Boolean {
    return getSystemService(ConnectivityManager::class.java)?.let { conManager ->
        conManager.getNetworkCapabilities(conManager.activeNetwork)
            ?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    } ?: false
}