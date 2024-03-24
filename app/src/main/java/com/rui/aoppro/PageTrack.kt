package com.rui.aoppro

import android.os.SystemClock
import android.util.Log
import org.aspectj.lang.JoinPoint

object PageTrack {
    private const val TAG = "PageTrack"
    private var currentPageName = ""
    private var enterTime: Long = -1
    private var exitTime: Long = -1


    fun trackOnCreate(joinPoint: JoinPoint) {
        enterTime = SystemClock.elapsedRealtime()
        currentPageName = getPagePath(joinPoint)
        Log.d(TAG, "trackOnCreate: $currentPageName")
    }


    fun trackOnResume(joinPoint: JoinPoint) {
        enterTime = SystemClock.elapsedRealtime()
        currentPageName = getPagePath(joinPoint)
        Log.d(TAG, "trackOnResume: $currentPageName")
    }


    fun trackOnPause(joinPoint: JoinPoint) {
        exitTime = SystemClock.elapsedRealtime()
        Log.d(TAG, "trackOnPause: $currentPageName stay time is ${exitTime - enterTime}")
    }


    fun trackOnDestroy(joinPoint: JoinPoint) {
        Log.d(TAG, "trackOnDestroy: $currentPageName stay time is ${exitTime - enterTime}")
    }




    private fun getPagePath(joinPoint: JoinPoint): String {
        joinPoint.target?.javaClass?.let {
            try {
                val pagePathAnnotation = it.getAnnotation(PageEvent::class.java)
                currentPageName = pagePathAnnotation?.let { pageEvent ->
                    pageEvent.pagePath
                } ?: it.simpleName
            } catch (e: Exception) {
                Log.e(TAG, "getPagePath: error page path")
            }
        }
        return currentPageName
    }

}