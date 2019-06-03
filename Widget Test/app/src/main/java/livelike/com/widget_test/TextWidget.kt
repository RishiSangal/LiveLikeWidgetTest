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
import android.view.View


@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class TextWidget : AppWidgetProvider() {

    private val WIDGET_TAG = "SAMPLE_WIDGET_TAG"
    private val FirstClick = "FirstClick"
    private val SecondClick = "SecondClick"
    private val ThirdClick = "ThirdClick"
    private val FourthClick = "FourthClick"

    @TargetApi(Build.VERSION_CODES.M)
    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
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

        if(intent.action.equals(FirstClick)){
            val views = RemoteViews(context.packageName, R.layout.text_widget)
            setbackGround(views, intent.action)
            val appWidget = ComponentName(context, TextWidget::class.java)
            val appWidgetManager = AppWidgetManager.getInstance(context)
            appWidgetManager.updateAppWidget(appWidget, views)
        } else if (intent.action.equals(SecondClick)){
            val views = RemoteViews(context.packageName, R.layout.text_widget)

            setbackGround(views, intent.action);
            val appWidget = ComponentName(context, TextWidget::class.java)
            val appWidgetManager = AppWidgetManager.getInstance(context)
            appWidgetManager.updateAppWidget(appWidget, views)

        }else if (intent.action.equals(ThirdClick)){
            val views = RemoteViews(context.packageName, R.layout.text_widget)
            setbackGround(views, intent.action);

            val appWidget = ComponentName(context, TextWidget::class.java)
            val appWidgetManager = AppWidgetManager.getInstance(context)
            appWidgetManager.updateAppWidget(appWidget, views)

        }else if (intent.action.equals(FourthClick)){
            val views = RemoteViews(context.packageName, R.layout.text_widget)
            setbackGround(views, intent.action);

            val appWidget = ComponentName(context, TextWidget::class.java)
            val appWidgetManager = AppWidgetManager.getInstance(context)
            appWidgetManager.updateAppWidget(appWidget, views)

        }
    }

    private fun setbackGround(views: RemoteViews, action: String?) {

        views.setInt(R.id.layFirst,"setBackgroundResource", R.drawable.cell_border_trans);
        views.setInt(R.id.laySec,"setBackgroundResource", R.drawable.cell_border_trans);
        views.setInt(R.id.layThird,"setBackgroundResource", R.drawable.cell_border_trans);
        views.setInt(R.id.layFourth,"setBackgroundResource", R.drawable.cell_border_trans);

        views.setInt(R.id.firstProgressBar,"setVisibility", View.INVISIBLE);
        views.setInt(R.id.secondProgressBar,"setVisibility", View.INVISIBLE);
        views.setInt(R.id.thirdProgressBar,"setVisibility", View.INVISIBLE);
        views.setInt(R.id.fourthProgressBar,"setVisibility", View.INVISIBLE);
        when (action){
            FirstClick ->{
                views.setInt(R.id.layFirst,"setBackgroundResource", R.drawable.cell_border_color);
                views.setInt(R.id.firstProgressBar,"setVisibility", View.VISIBLE);
            }
            SecondClick ->{
                views.setInt(R.id.laySec,"setBackgroundResource", R.drawable.cell_border_color);
                views.setInt(R.id.secondProgressBar,"setVisibility", View.VISIBLE);
            }
            ThirdClick -> {
                views.setInt(R.id.layThird,"setBackgroundResource", R.drawable.cell_border_color);
                views.setInt(R.id.thirdProgressBar,"setVisibility", View.VISIBLE);
            }
            FourthClick -> {
                views.setInt(R.id.layFourth,"setBackgroundResource", R.drawable.cell_border_color);
                views.setInt(R.id.fourthProgressBar,"setVisibility", View.VISIBLE);
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int) {

        val views = RemoteViews(context.packageName, R.layout.text_widget)


        views.setOnClickPendingIntent(R.id.layFirst, getPendingSelfIntent(context, FirstClick));
        views.setOnClickPendingIntent(R.id.laySec, getPendingSelfIntent(context, SecondClick));
        views.setOnClickPendingIntent(R.id.layThird, getPendingSelfIntent(context, ThirdClick));
        views.setOnClickPendingIntent(R.id.layFourth, getPendingSelfIntent(context, FourthClick));

        appWidgetManager.updateAppWidget(appWidgetId, views)
    }

    protected fun getPendingSelfIntent(context: Context, action: String): PendingIntent {
        val intent = Intent(context, TextWidget::class.java)
        intent.action = action
        return PendingIntent.getBroadcast(context, 0, intent, 0)
    }
}


