package livelike.com.widget_test

import android.content.Intent
import android.widget.RemoteViewsService

open class WidgetService : RemoteViewsService() {

    override fun onGetViewFactory(intent: Intent?): RemoteViewsFactory {
        return WidgetDataAdapter(this, intent)
    }

}