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

//        var editText = findViewById<EditText>(R.id.edit_text)
//        var button = findViewById<Button>(R.id.button)

        val intent = Intent(this, TextWidget::class.java)
        intent.action = "ACTIVITY_ACTION"

        //This action will send broadcast to update the widget
        AppWidgetManager.getInstance(application).getAppWidgetIds(ComponentName
        (application,TextWidget::class.java))
//        intent.putExtra("name", editText.text.toString())
        sendBroadcast(intent)

        val intentTwo = Intent(this, ImageWidget::class.java)
        intentTwo.action = "ACTIVITY_ACTION"
        AppWidgetManager.getInstance(application).getAppWidgetIds(ComponentName
        (application,ImageWidget::class.java))
//        intent.putExtra("name", editText.text.toString())
        sendBroadcast(intentTwo)
    }
}
