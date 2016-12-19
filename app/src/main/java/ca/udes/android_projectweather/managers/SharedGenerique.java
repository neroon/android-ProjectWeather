package ca.udes.android_projectweather.managers;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import ca.udes.android_projectweather.R;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Colombies on 19/12/2016.
 */

public class SharedGenerique extends Activity {


    public SharedGenerique() {
    }

    public String getFav() {
        /*SharedPreferences settings;
        settings = getApplicationContext().getSharedPreferences("LOCAL", MODE_PRIVATE); //1
        String save_id = settings.getString("save_fav", null);*/
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
// then you use
        String save_id = prefs.getString("save_fav", "pasbon");

       /* SharedPreferences myPrefs;
        myPrefs = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        String StoredValue=myPrefs.getString("STOREDVALUE", "");*/
        return prefs.getString("save_fav", "pasbon");

    }



// and, this is in your activity
  //  YourClass.this.getSharedPreferences(YourClass.this.getApplicationContext());
}
