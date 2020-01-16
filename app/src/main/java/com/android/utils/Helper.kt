package com.vaibhavi.android


import android.app.Activity
import android.app.Dialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.*
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.provider.MediaStore
import android.telephony.TelephonyManager
import android.text.Editable
import android.text.Html
import android.text.TextWatcher
import android.text.method.LinkMovementMethod
import android.util.Log
import android.util.Patterns
import android.view.View
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

import com.google.android.material.snackbar.Snackbar
import com.google.firebase.analytics.FirebaseAnalytics

import java.io.ByteArrayOutputStream
import java.lang.IllegalArgumentException
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern
import kotlin.collections.ArrayList

fun FragmentManager.changeFragmentForBottomNavigationView(container: Int, fragment: Fragment) {

    this.beginTransaction()
        .setCustomAnimations(
            R.anim.enter_from_left,
            R.anim.exit_to_right,
            R.anim.enter_from_right,
            R.anim.exit_to_left
        )
        .replace(container, fragment)
        .addToBackStack(null).commit()


}

fun convertDpToPx(context: Context, dp: Float): Float {
    return dp * context.resources.displayMetrics.density
}


fun extractLinks(text: String): ArrayList<String> {
    val links = ArrayList<String>()
    val m = Patterns.WEB_URL.matcher(text)
    while (m.find()) {
        val url = m.group()
        Log.e("Detected Links", "URL extracted: $url")
        links.add(url)
    }

    return links
}

fun getBitmapFromView(view: View): Bitmap {
    val returnedBitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(returnedBitmap)
    val bgDrawable = view.background
    if (bgDrawable != null)
        bgDrawable.draw(canvas)
    else
        canvas.drawColor(Color.WHITE)
    view.draw(canvas)
    return returnedBitmap
}

fun getImageUri(inContext: Context, inImage: Bitmap): Uri? {
    return try {
        val bytes = ByteArrayOutputStream()
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path =
            MediaStore.Images.Media.insertImage(inContext.contentResolver, inImage, "Title", null)
        Uri.parse(path)

    } catch (e: IllegalStateException) {
        null
    }

}

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

fun Context?.getBitmapfromDrawable(image: Int): Bitmap {
    return BitmapFactory.decodeResource(this?.resources, image)
}


fun Context.drawOverlayBottom(
    bmp1: Bitmap,
    bmp2: Bitmap,
    bmp3: Bitmap,
    bmp4: Bitmap,
    backgroundColor: Int
): Bitmap {
    val bmOverlay =
        Bitmap.createBitmap(
            Math.max(bmp1.width, bmp2.width) + 40,
            bmp1.height + bmp2.height + bmp4.height + 120,
            bmp1.config
        )
    val canvas = Canvas(bmOverlay)
    val paint = Paint()
    paint.color =
        ContextCompat.getColor(this, backgroundColor)//設定文字顏色  paint2.setColor(Color.WHITE);
    paint.style = Paint.Style.FILL
    canvas.drawPaint(paint)
    canvas.drawBitmap(bmp4, ((bmp1.width / 2) - (bmp4.width / 2)).toFloat(), 20.toFloat(), null)
    canvas.drawBitmap(bmp1, 20.toFloat(), (bmp4.height + 60).toFloat(), null)
    canvas.drawBitmap(
        bmp2,
        (canvas.width / 2 - 20 - bmp2.width).toFloat(),
        (bmp1.height + bmp4.height + 80).toFloat(),
        null
    )
    canvas.drawBitmap(
        bmp3,
        (canvas.width / 2 + 20).toFloat(),
        (bmp1.height + bmp4.height + 80).toFloat(),
        null
    )
    return bmOverlay
}



fun Context.setEnterAnimation(viewToAnimate: View) {
    // If the bound view wasn't previously displayed on screen, it's animated
    val animation = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left)
    viewToAnimate.startAnimation(animation)
}


fun detectHashTag(text: String): ArrayList<String> {
    val MY_PATTERN = Pattern.compile("#(\\S+)")
    val mat = MY_PATTERN.matcher(text)
    val strs = mutableListOf<String>()
    while (mat.find()) {
        strs.add(mat.group(1))
    }
    return strs as ArrayList<String>
}

fun String.getFirstName(): String? {
    return this.split("\\s".toRegex())[0]
}


fun Context.hideKeyBoardFromFragment(view: View) {
    val imm = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Context.logAnalyticsEvent(event: String, bundle: Bundle?) {
    try {
        FirebaseAnalytics.getInstance(this).logEvent(event, bundle)
    } catch (e: NullPointerException) {

    }
}

fun Context.getCountryCode(): String? {
    return try {
        val telephonyMngr = this.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

        telephonyMngr.simCountryIso.toUpperCase()
    } catch (e: java.lang.NullPointerException) {
        null
    } catch (e: IllegalArgumentException) {
        null
    }

}


fun getDateFromTimeStamp(timeStamp: String): String {
    return try {
        val simpleDateFormat = SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault())
        simpleDateFormat.format(Date(timeStamp.toLong()))
    } catch (e: NumberFormatException) {
        " "
    }
}

fun Context.openPlaystore() {
    try {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("market://details?id=com.profoundly.android")
            )
        )
    } catch (anfe: android.content.ActivityNotFoundException) {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://play.google.com/store/apps/details?id=com.profoundly.android")
            )
        )
    }
}






fun getFacebookPageURL(context: Context): String {
    val FACEBOOK_URL = "https://www.facebook.com"
    val packageManager = context.packageManager
    return try {
        val versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode
        if (versionCode >= 3002850) {
            "fb://facewebmodal/f"
        } else { //older versions of fb app
            "fb://page"
        }
    } catch (e: PackageManager.NameNotFoundException) {
        FACEBOOK_URL
    }
}



fun View.showSnackbar(message: String, snackbarLength: Int) {
    val snackbar = Snackbar.make(
        this, message,
        snackbarLength
    )
    snackbar.setActionTextColor(Color.BLUE)
    val snackbarView = snackbar.view
    snackbar.setTextColor(Color.WHITE)
    snackbarView.setBackgroundColor(Color.BLACK)
    snackbar.show()
}

fun Activity.disableInteraction() {
    this.window.setFlags(
        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
    )
}

fun Activity.enableInteraction() {
    this.window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
}


fun Long.showTime(): String {
    val calender = Calendar.getInstance()
    val calendarNow = Calendar.getInstance()
    calendarNow.time = Date()
    calender.time = Date(this)
    val year = calender.get(Calendar.YEAR)
    val month = calender.get(Calendar.MONTH)
    val day = calender.get(Calendar.DAY_OF_MONTH)
    val yearNow = calendarNow.get(Calendar.YEAR)
    val monthNow = calendarNow.get(Calendar.MONTH)
    val dayNow = calendarNow.get(Calendar.DAY_OF_MONTH)
    val yearDiff = yearNow - year
    val monthDiff = monthNow - month
    val dayDiff = dayNow - day

    return if (yearDiff != 0) {
        SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(Date(this))
    } else {
        if (monthDiff != 0) {
            SimpleDateFormat("dd MMM", Locale.getDefault()).format(Date(this))
        } else {
            when (dayDiff) {
                0 -> SimpleDateFormat("hh:mm a", Locale.getDefault()).format(Date(this))
                1 -> "Yesterday"
                else -> SimpleDateFormat("dd MMM", Locale.getDefault()).format(Date(this))
            }
        }
    }
}







fun Context.isOnline(): Boolean {

    val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val netInfo = cm.activeNetworkInfo
    //should check null because in airplane mode it will be null
    return netInfo != null && netInfo.isConnected
}























