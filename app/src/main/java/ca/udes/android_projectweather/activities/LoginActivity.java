package ca.udes.android_projectweather.activities;

import android.content.SharedPreferences;
import android.preference.Preference;
import android.support.v7.app.AppCompatActivity;
import android.os.AsyncTask;


import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;


import ca.udes.android_projectweather.R;
import ca.udes.android_projectweather.managers.SharedPreferenceManager;
import ca.udes.android_projectweather.utils.Constants;


/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {


    //Log
    private static final String LOG_TAG = "test";

    //Adresse IP + PORT
    private static final String IPMEAN = "http://192.168.0.101:3000";




    //--- UI MEAN --
    //Inscription
    private TextView mTextViewInfo;
    private AutoCompleteTextView mNameView;
    private AutoCompleteTextView mFavView;
    //Connexion
    private TextView mTextInfoConnectView;
    private AutoCompleteTextView mIdConnectView;
    //Edit
    private TextView mTextEditView;
    private AutoCompleteTextView mNameEditView;
    private AutoCompleteTextView mFavEditView;


    //SharedPreference bien vérifier avec les CONSTANTS
    String PREFS_NAME = "LOCAL";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);




        //MEAN inscription
        mTextViewInfo = (TextView) findViewById(R.id.textViewInfo);
        mNameView = (AutoCompleteTextView) findViewById(R.id.name_mean);
        mFavView = (AutoCompleteTextView) findViewById(R.id.fav_mean);
        Button mMeanSignInButton = (Button) findViewById(R.id.mean_inscription_button);
        mMeanSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(LOG_TAG, "Bouton inscription");
                Toast.makeText(getApplicationContext(), "Inscription", Toast.LENGTH_SHORT).show();

                String name = mNameView.getText().toString();
                String fav = mFavView.getText().toString();

                JsonPostRequest jsonPostRequest = new JsonPostRequest(name,fav);
                jsonPostRequest.execute();
            }
        });

        //MEAN Connexion
        mTextInfoConnectView = (TextView) findViewById(R.id.textViewInfoConnect);
        mIdConnectView = (AutoCompleteTextView) findViewById(R.id.id_mean_connect);
        Button mMeanConnectButton = (Button) findViewById(R.id.mean_connect_button);
        mMeanConnectButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(LOG_TAG, "Bouton Connexion");
                Toast.makeText(getApplicationContext(), "Connexion", Toast.LENGTH_SHORT).show();

                String myId = mIdConnectView.getText().toString();

                JsonConnectRequest jsonConnectRequest = new JsonConnectRequest(myId);
                jsonConnectRequest.execute();
            }
        });


        //MEAN edit
        mTextEditView = (TextView) findViewById(R.id.textViewInfoEdit);
        mNameEditView = (AutoCompleteTextView) findViewById(R.id.name_edit_mean);
        mFavEditView = (AutoCompleteTextView) findViewById(R.id.fav_edit_mean);
        Button mMeanEditButton = (Button) findViewById(R.id.mean_edit_button);
        mMeanEditButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(LOG_TAG, "Bouton Edit");
                Toast.makeText(getApplicationContext(), "Edit", Toast.LENGTH_SHORT).show();

                String myId = getIdSharedPreference(); //Provient de sharedPreference
                String name = mNameEditView.getText().toString();
                String fav = mFavEditView.getText().toString();

                JsonEditRequest jsonEditRequest = new JsonEditRequest(myId,name,fav);
                jsonEditRequest.execute();
            }
        });

        //affiche au lancement les données local de sharedPreference
        printFromSharedPreference();

        //Notification
        /*String myLocalId = getIdSharedPreference();
        String myLocalName = getNameSharedPreference();
        String myLocalFav = getFavSharedPreference();
        if(myLocalId!=null && myLocalName!=null){
            FavNotification.notify(getApplicationContext(),"Bonjour "+myLocalName+" Vos favoris sont : "+myLocalFav,1);
        }else{
            FavNotification.notify(getApplicationContext(),"Connectez-vous pour sauvegarder vos favoris ",1);
        }*/



        //Déconnexion (vide le sharePreference)
        Button mMeanLogoutButton = (Button) findViewById(R.id.mean_logout_button);
        mMeanLogoutButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(LOG_TAG, "Bouton Logout");
                Toast.makeText(getApplicationContext(), "Déconnexion", Toast.LENGTH_SHORT).show();
                flushSharedPreference();
            }
        });


    }



    /**
     * MEAN STACK POST
     * Asynctask pour l'inscription (bien vérifier l'adresse ip)
     */
    private class JsonPostRequest extends AsyncTask<Void, Void, String> {


        private final String mName;
        private final String mFav;

        public static final int READ_TIMEOUT = 3000;
        public static final int CONNECTION_TIMEOUT = 3000;

        //Contructeur par defaut
        JsonPostRequest(String name, String fav) {
            mName = name;
            mFav = fav;
        }

        @Override
        protected String doInBackground(Void... voids) {

            try {
                String address = IPMEAN+"/api/users";
                JSONObject json = new JSONObject();
                json.put("name", mName);
                json.put("fav", mFav);
                String requestBody = json.toString();
                URL url = new URL(address);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setReadTimeout(READ_TIMEOUT);
                urlConnection.setConnectTimeout(CONNECTION_TIMEOUT);
                urlConnection.setDoOutput(true);
                urlConnection.setRequestProperty("Content-Type", "application/json");
                OutputStream outputStream = new BufferedOutputStream(urlConnection.getOutputStream());
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "utf-8"));
                writer.write(requestBody);
                writer.flush();
                writer.close();
                outputStream.close();

                InputStream inputStream;
                // get stream
                if (urlConnection.getResponseCode() < HttpURLConnection.HTTP_BAD_REQUEST) {
                    inputStream = urlConnection.getInputStream();
                } else {
                    inputStream = urlConnection.getErrorStream();
                }
                // parse stream
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp, response = "";
                while ((temp = bufferedReader.readLine()) != null) {
                    response += temp;
                }
                // put into JSONObject
                JSONObject jsonObject = new JSONObject();
                //don't delete
                //jsonObject.put("Content", response);
                //jsonObject.put("Message", urlConnection.getResponseMessage());
                //jsonObject.put("Length", urlConnection.getContentLength());
                //jsonObject.put("Type", urlConnection.getContentType());
                //return jsonObject.toString();
                Log.i(LOG_TAG, "MESSAGE RESPONSE: " + urlConnection.getResponseMessage());


                return response;
            } catch (IOException | JSONException e) {
                return e.toString();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            mTextViewInfo.setText(result);
            super.onPostExecute(result);
            parseStringToJson(result);
            Log.i(LOG_TAG, "POST RESPONSE: " + result);
            Toast.makeText(getApplicationContext(), "Détail Inscription"+ result, Toast.LENGTH_LONG).show();
            //mTextViewInfo.setText(result);
            printFromSharedPreference();

        }
    }



    /**
     * MEAN STACK GET
     * https://medium.com/@JasonCromer/android-asynctask-http-request-tutorial-6b429d833e28#.tmg8z8c1t
     * Asynctask pour la connexion (bien vérifier l'adresse ip)
     * Void, void , string
     */
    private class JsonConnectRequest extends AsyncTask<Void, Void, String> {

        private final String mId;

        //Contructeur par defaut
        JsonConnectRequest(String id) {
            mId = id;
        }


        public static final String REQUEST_METHOD = "GET";
        public static final int READ_TIMEOUT = 3000;
        public static final int CONNECTION_TIMEOUT = 3000;

        @Override
        protected String doInBackground(Void... voids){

            //Nécessaire car si on envoie une url sans l'id le serveur nous renvoie tout les utilisateurs
            String idFinal = mId;
            if(mId.isEmpty()){
                idFinal="vide";
            }
            String stringUrl = IPMEAN+"/api/users/"+idFinal;
            Log.d("-------------", stringUrl);

            String result;
            String inputLine;
            try {
                //Create a URL object holding our url
                URL myUrl = new URL(stringUrl);
                //Create a connection
                HttpURLConnection connection =(HttpURLConnection)
                        myUrl.openConnection();
                //Set methods and timeouts
                connection.setRequestMethod(REQUEST_METHOD);
                connection.setReadTimeout(READ_TIMEOUT);
                connection.setConnectTimeout(CONNECTION_TIMEOUT);

                //Connect to our url
                connection.connect();
                //Create a new InputStreamReader
                InputStreamReader streamReader = new
                        InputStreamReader(connection.getInputStream());
                //Create a new buffered reader and String Builder
                BufferedReader reader = new BufferedReader(streamReader);
                StringBuilder stringBuilder = new StringBuilder();
                //Check if the line we are reading is not null
                while((inputLine = reader.readLine()) != null){
                    stringBuilder.append(inputLine);
                }
                //Close our InputStream and Buffered reader
                reader.close();
                streamReader.close();
                //Set our result equal to our stringBuilder
                result = stringBuilder.toString();
            }
            catch(IOException e){
                e.printStackTrace();
                result = e.toString();
            }
            return result;
        }
        @Override
        protected void onPostExecute(String result){
            mTextInfoConnectView.setText(result);
            super.onPostExecute(result);
            parseStringToJson(result); //parse et sauvegarde localement les données
            printFromSharedPreference(); //affiche les donées de SharedPreferences
        }
    }


    /**
     * MEAN STACK PUT
     * Asynctask pour la modification d'un profil (bien vérifier l'adresse ip)
     * Void, void , string
     */
    private class JsonEditRequest extends AsyncTask<Void, Void, String> {


        private final String mId; //Doit provenir de sharedPreference
        private final String mName;
        private final String mFav;

        //Contructeur par defaut
        JsonEditRequest(String id, String name, String fav) {
            mId = id;
            mName = name;
            mFav = fav;
        }


        public static final String REQUEST_METHOD = "PUT";
        public static final int READ_TIMEOUT = 3000;
        public static final int CONNECTION_TIMEOUT = 3000;

        @Override
        protected String doInBackground(Void... voids){
            String stringUrl = IPMEAN+"/api/users/"+mId;


            String result;
            String inputLine;
            try {
                //Create a URL object holding our url
                URL myUrl = new URL(stringUrl);
                JSONObject json = new JSONObject();
                json.put("name", mName);
                json.put("fav", mFav);
                String requestBody = json.toString();
                //Create a connection
                HttpURLConnection connection =(HttpURLConnection)
                        myUrl.openConnection();
                //Set methods and timeouts
                connection.setRequestMethod(REQUEST_METHOD);
                connection.setReadTimeout(READ_TIMEOUT);
                connection.setConnectTimeout(CONNECTION_TIMEOUT);

                connection.setRequestProperty("Content-Type", "application/json");
                OutputStream outputStream = new BufferedOutputStream(connection.getOutputStream());
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "utf-8"));
                writer.write(requestBody);
                writer.flush();
                writer.close();
                outputStream.close();

                //Connect to our url
                connection.connect();
                //Create a new InputStreamReader
                InputStreamReader streamReader = new
                        InputStreamReader(connection.getInputStream());

                //Create a new buffered reader and String Builder
                BufferedReader reader = new BufferedReader(streamReader);
                StringBuilder stringBuilder = new StringBuilder();


                //Check if the line we are reading is not null
                while((inputLine = reader.readLine()) != null){
                    stringBuilder.append(inputLine);
                }
                //Close our InputStream and Buffered reader
                reader.close();
                streamReader.close();
                //Set our result equal to our stringBuilder
                result = stringBuilder.toString();
            }
            catch(IOException | JSONException e){
                e.printStackTrace();
                result = e.toString();
            }
            return result;
        }
        @Override
        protected void onPostExecute(String result){
            //mTextEditView.setText(result); //contient les données avant la modification
            super.onPostExecute(result);
            JsonConnectRequest jsonConnectRequest = new JsonConnectRequest(getIdSharedPreference()); //on met à jour apres l'update qu'on a fait
            jsonConnectRequest.execute();

        }
    }




    /**
     * TOOL : parse my String to JSON + change mTextViewInfo + sharedPreference
     * @param myJsonString contient simplement le contenu (pas de header etc)
     */
    public void parseStringToJson(String myJsonString){
        try {

            Log.d("------------------> ", myJsonString);
            JSONObject json = new JSONObject(myJsonString); // convert String to JSONObject
            Log.d("-------------", "Taille "+json.length());
            Log.d("-------------", json.getString("name"));
            Log.d("-------------", json.getString("_id"));
            Log.d("-------------", json.getString("fav"));
            Toast.makeText(getApplicationContext(), "ID : "+json.getString("_id")+", Nom :"+ json.getString("name") +", fav :"+ json.getString("fav"), Toast.LENGTH_LONG).show();

            mTextViewInfo.setText("ID : "+json.getString("_id")+", Nom :"+ json.getString("name") +", fav :"+ json.getString("fav"));


            SharedPreferences settings = getApplicationContext().getSharedPreferences(PREFS_NAME, MODE_PRIVATE); //MODE_PRIVATE
            SharedPreferences.Editor editor = settings.edit();

            //sauvegarde local
            editor.putString("save_name", json.getString("name"));
            editor.putString("save_id", json.getString("_id"));
            editor.putString("save_fav", json.getString("fav"));
            editor.commit();



        } catch (Throwable t) {
            Log.e("My App", "Could not parse malformed JSON: \"" + myJsonString + "\"");
        }
    }

    /**
     *
     * Cherche si on a sauvegardé des données + affiche
     * Utilisé au lancement de l'application
     */
    public void printFromSharedPreference(){

        SharedPreferences settings;

        settings = getApplicationContext().getSharedPreferences(PREFS_NAME, MODE_PRIVATE); //1
        String save_name = settings.getString("save_name", null);
        String save_id = settings.getString("save_id", null);
        String save_fav = settings.getString("save_fav", null);
        if(save_id!=null){
            mTextEditView.setText("id:"+save_id+" nom:"+save_name+" ville:"+save_fav);
            Toast.makeText(getApplicationContext(), "Donnée Local trouvé. Bonjour "+save_name, Toast.LENGTH_SHORT).show();
        }else{
            mTextEditView.setText("Aucune données dans SharedPreferences");

        }
    }

    /**
     * Déconnexion
     * Méthode qui vide simplement le SharedPreference
     */
    public void flushSharedPreference(){

        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = getApplicationContext().getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        editor = settings.edit();

        editor.clear();
        editor.commit();
        Toast.makeText(getApplicationContext(), "Données Locales nettoyé", Toast.LENGTH_SHORT).show();
        mTextEditView.setText("VIDE");
    }


    /**
     * ----------------------------------------------------------------------
     *          GETTER POUR SHAREDPREFERENCE
     *  ----------------------------------------------------------------------
     */

    /**
     * Permet de retourner l'id sauvegardé par sharedPreference
     * Utilisé pour la modification d'un profil
     * @return l'id sauvegardé localement
     * null si on trouve rien
     */
    public String getIdSharedPreference(){

        SharedPreferences settings;
        settings = getApplicationContext().getSharedPreferences(PREFS_NAME, MODE_PRIVATE); //1
        String save_id = settings.getString("save_id", null);

        return save_id;
    }

    /**
     * Permet de retourner le nom sauvegardé par sharedPreference
     * @return le name sauvegardé localement
     * null si on trouve rien
     */
    public String getNameSharedPreference(){

        SharedPreferences settings;
        settings = getApplicationContext().getSharedPreferences(PREFS_NAME, MODE_PRIVATE); //1
        String save_name = settings.getString("save_name", null);

        return save_name;
    }

    /**
     * Permet de retourner les fav sauvegardé par sharedPreference
     * @return les fav  sauvegardés localement (ex : Sherbrooke,Paris,Montreal)
     * null si on trouve rien
     */
    public String getFavSharedPreference(){

        SharedPreferences settings;
        settings = getApplicationContext().getSharedPreferences(PREFS_NAME, MODE_PRIVATE); //1
        String save_fav = settings.getString("save_fav", null);

        return save_fav;
    }


}

