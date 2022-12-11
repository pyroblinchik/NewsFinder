package com.pyroblinchik.newsfinder.util

import android.app.Activity
import android.app.PendingIntent.getActivity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent


fun Context?.getActivity(): Activity? {
    if (this == null) {
        return null
    } else if (this is ContextWrapper) {
        return if (this is Activity) {
            this
        } else {
            this.baseContext.getActivity()
        }
    }
    return null
}