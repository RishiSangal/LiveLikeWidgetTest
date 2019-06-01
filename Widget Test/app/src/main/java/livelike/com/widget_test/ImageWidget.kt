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
        for (appWidgetId in appWidgetIds) {
            val views = RemoteViews(context.packageName, R.layout.image_widget)
            val startActivityIntent = Intent(context, ImageWidget::class.java)
            val startActivityPendingIntent = PendingIntent.getActivity(context, 0,
                    startActivityIntent, PendingIntent.FLAG_UPDATE_CURRENT)
            views.setPendingIntentTemplate(R.id.lstImages, startActivityPendingIntent);

            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
        super.onUpdate(context, appWidgetManager, appWidgetIds)
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

        if(intent.action.equals("ListClick")){
            val views = RemoteViews(context.packageName, R.layout.image_widget)

            views.setInt(R.id.layImage,"setBackgroundResource", R.drawable.cell_border_color);

            val appWidget = ComponentName(context, TextWidget::class.java)
            val appWidgetManager = AppWidgetManager.getInstance(context)
            appWidgetManager.updateAppWidget(appWidget, views)
        }
    }


    @RequiresApi(Build.VERSION_CODES.M)
    private fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int) {

        val views = RemoteViews(context.packageName, R.layout.image_widget)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            setRemoteAdapter(context, views)
        } else {
            setRemoteAdapterV11(context, views)
        }

        appWidgetManager.updateAppWidget(appWidgetId, views)
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    private fun setRemoteAdapter(context: Context, views: RemoteViews) {
        views.setRemoteAdapter(R.id.lstImages,
                Intent(context, WidgetService::class.java))
    }
    private fun setRemoteAdapterV11(context: Context, views: RemoteViews) {
        views.setRemoteAdapter(0, R.id.lstImages,
                Intent(context, WidgetService::class.java))
    }

}


