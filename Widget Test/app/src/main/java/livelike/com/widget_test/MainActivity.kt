package livelike.com.widget_test

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Intent

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val intent = Intent(this, TextWidget::class.java)
        intent.action = "ACTIVITY_ACTION"

        AppWidgetManager.getInstance(application).getAppWidgetIds(ComponentName
        (application,TextWidget::class.java))
        sendBroadcast(intent)

        val intentTwo = Intent(this, ImageWidget::class.java)
        intentTwo.action = "ACTIVITY_ACTION"
        AppWidgetManager.getInstance(application).getAppWidgetIds(ComponentName
        (application,ImageWidget::class.java))
        sendBroadcast(intentTwo)
    }
}
