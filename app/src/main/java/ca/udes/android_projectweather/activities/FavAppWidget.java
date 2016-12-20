package ca.udes.android_projectweather.activities;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;
import android.widget.Toast;

import java.util.Random;

import ca.udes.android_projectweather.R;

import static android.content.Context.MODE_PRIVATE;


/**
 * Implementation of App Widget show fav
 */
public class FavAppWidget extends AppWidgetProvider {



    private static final String RED_CLICKED = "FAVORIS";


    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.fav_app_widget);
        views.setTextViewText(R.id.appwidget_text, widgetText);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.fav_app_widget);
        ComponentName watchWidget = new ComponentName(context, FavAppWidget.class);

        remoteViews.setOnClickPendingIntent(
                R.id.appwidget_text,
                getPendingSelfIntent(context, RED_CLICKED)
        );

        appWidgetManager.updateAppWidget(watchWidget, remoteViews);

        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    // Action is important to catch the view which is clicked
    protected PendingIntent getPendingSelfIntent(Context context, String action) {
        Intent intent = new Intent(context, getClass());
        intent.setAction(action);

        Toast.makeText(context, "Clicked: "+action, Toast.LENGTH_SHORT).show();
        return PendingIntent.getBroadcast(context, 0, intent, 0);
    }

    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        super.onReceive(context, intent);

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.fav_app_widget);
        ComponentName watchWidget = new ComponentName(context, FavAppWidget.class);

        // Display which View is clicked don't delete
        //Toast.makeText(context,"onReceive: "+intent.getAction(),Toast.LENGTH_LONG).show();

        // Generate a random number
        Random rand = new Random();
        Integer randomNumber = rand.nextInt(25);
        //remoteViews.setTextViewText(R.id.appwidget_text, "" + randomNumber);

            /*
                String getAction()
                Retrieve the general action to be performed, such as ACTION_VIEW.
            */

        if (RED_CLICKED.equals(intent.getAction())) {

            // If the Red TextView clicked, then do that
            //remoteViews.setTextViewText(R.id.appwidget_text, "" + randomNumber);
            String name = getSharedPrefName(context);
            String fav = getSharedPrefFav(context);
            String msg = "Vos favoris sont : "+fav;
            if(fav==null){
                msg="Veillez vous connecter";
            }
            remoteViews.setTextViewText(R.id.appwidget_text, msg);

            Toast.makeText(context, "Rafraichissement", Toast.LENGTH_SHORT).show();
        }

        /*if (GREEN_CLICKED.equals(intent.getAction())) {
            // If the Green TextView clicked, then do that
            remoteViews.setTextViewText(R.id.tv_green, "" + randomNumber);
        }*/
        appWidgetManager.updateAppWidget(watchWidget, remoteViews);
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }


    public static String getSharedPrefName(Context context){
        SharedPreferences settings;
        settings = context.getSharedPreferences("LOCAL", MODE_PRIVATE); //1
        String save_id = settings.getString("save_name", null);
        return save_id;
    }

    public static String getSharedPrefFav(Context context){
        SharedPreferences settings;
        settings = context.getSharedPreferences("LOCAL", MODE_PRIVATE); //1
        String save_id = settings.getString("save_fav", null);
        return save_id;
    }
}

