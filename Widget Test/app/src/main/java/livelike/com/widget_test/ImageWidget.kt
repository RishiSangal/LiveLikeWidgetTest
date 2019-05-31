package livelike.com.widget_test

import android.annotation.TargetApi
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.util.Log
import android.widget.RemoteViews
import android.app.PendingIntent
import android.content.Intent
import android.content.ComponentName
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.DisplayMetrics




@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class ImageWidget : AppWidgetProvider() {

    private val WIDGET_TAG = "SAMPLE_WIDGET_TAG"

    @TargetApi(Build.VERSION_CODES.M)
    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            Log.d(WIDGET_TAG,"update")
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {


        Log.d(WIDGET_TAG,"enabled")
    }

    override fun onDisabled(context: Context) {
        Log.d(WIDGET_TAG,"disabled")
    }

    @TargetApi(Build.VERSION_CODES.M)
    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)

//        if(intent.action.equals(FirstClick)){
//            val views = RemoteViews(context.packageName, R.layout.image_widget)
//            val appWidget = ComponentName(context, TextWidget::class.java)
//            val appWidgetManager = AppWidgetManager.getInstance(context)
//            appWidgetManager.updateAppWidget(appWidget, views)
//        }
    }


    @RequiresApi(Build.VERSION_CODES.M)
    private fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int) {

        val views = RemoteViews(context.packageName, R.layout.image_widget)
//        val displayMetrics = DisplayMetrics()
////        windowManager.defaultDisplay.getMetrics(displayMetrics)
//        var width = displayMetrics.widthPixels
//        var height = displayMetrics.heightPixels



        appWidgetManager.updateAppWidget(appWidgetId, views)
    }

    protected fun getPendingSelfIntent(context: Context, action: String): PendingIntent {
        val intent = Intent(context, ImageWidget::class.java)
        intent.action = action
        return PendingIntent.getBroadcast(context, 0, intent, 0)
    }
}


