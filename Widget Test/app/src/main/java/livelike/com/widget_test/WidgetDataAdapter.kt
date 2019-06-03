package livelike.com.widget_test

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import java.util.ArrayList

open class WidgetDataAdapter : RemoteViewsService.RemoteViewsFactory{

    internal var mCollection: ArrayList<ImageData> = ArrayList()
    internal var mContext: Context? = null
    companion object {
        val ListClick = "ListClick"
        val ListPosition = "ListPosition"
    }

    constructor(widgetService: WidgetService, intent: Intent?){
        mContext = widgetService
    }


    override fun getLoadingView(): RemoteViews? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onDataSetChanged() {
        initData()
    }

    override fun hasStableIds(): Boolean {
        return true
    }

    override fun getViewAt(position: Int): RemoteViews {
        val view = RemoteViews(mContext!!.packageName, R.layout.cell_listview)
        view.setTextViewText(R.id.txtImageFooter, mCollection.get(position).name)
        view.setInt(R.id.imgImage,"setBackgroundResource",  mCollection.get(position).image)
        view.setTextViewText(R.id.txtFirstPercentage, mCollection.get(position).percentage)
        view.setInt(R.id.prgPercentage, "setProgress", mCollection.get(position).progress)

        val fillInIntent = Intent().apply {
            Bundle().also { extras ->
                extras.putInt(ListPosition, position)
                putExtras(extras)
            }
        }

        view.setOnClickFillInIntent(R.id.layImage, fillInIntent);
//        view.setOnClickPendingIntent(R.id.layImage, getPendingSelfIntent(mContext!!, ListClick));
        return view
    }

    override fun getCount(): Int {
        return mCollection.size
    }

    override fun getViewTypeCount(): Int {
        return 1
    }

    override fun onDestroy() {
    }

    override fun onCreate() {
        initData()
    }

    private fun initData() {
        mCollection.clear()
//        for (i in 1..4) {
//            if (i ==1)
                mCollection.add(ImageData("Android", R.drawable.android_1, "64%", 64))
        mCollection.add(ImageData("Python", R.drawable.python, "3%", 3))
        mCollection.add(ImageData("IOS", R.drawable.apple, "50%", 50))
        mCollection.add(ImageData("Scala", R.drawable.scala, "35%", 35))
//            else if (1 ==2)
//        }
    }

//    protected fun getPendingSelfIntent(context: Context, action: String): PendingIntent {
//        val intent = Intent(context, ImageWidget::class.java)
//        intent.action = action
//        return PendingIntent.getBroadcast(context, 0, intent, 0)
//    }


}