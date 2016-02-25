package com.idcs.jsonparsing;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hp on 2/7/2016.
 */
public class ProcessJSON extends AsyncTask<String, Void, String> {

    Context context;

    ProcessJSON(Context context){
        this.context = context;
    }

    protected String doInBackground (String... strings){
        String stream = null;
        String urlString = strings[0];

        HTTPDataHandler hh = new HTTPDataHandler();
        stream = hh.GetHTTPData(urlString);

        // Return the data from specified url
        return stream;
    }

    protected void onPostExecute(String stream){
//        MainActivity mainActivity = new MainActivity();
        TextView tv = (TextView) ((Activity)context).findViewById(R.id.tv);
        String data = "";
        //tv.setText(stream);

            /*
                Important in JSON DATA
                -------------------------
                * Square bracket ([) represents a JSON array
                * Curly bracket ({) represents a JSON object
                * JSON object contains key/value pairs
                * Each key is a String and value may be different data types
             */

        //..........Process JSON DATA................
        if(stream !=null){
            Log.d("", "Inside onPost");
            try{
                // Get the full HTTP Data as JSONObject
                JSONObject emp = new JSONObject(stream);

                // Get the JSONOArray "contacts"...........................
                JSONArray jsonArray = emp.optJSONArray("contacts");
                tv.setText("We are processing the JSON data....\n\n");
                for(int i=0; i < jsonArray.length(); i++){
                    // Get the JSONObject ...........................
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    String id = jsonObject.optString("id").toString();
                    String name = jsonObject.optString("name").toString();
                    String email = jsonObject.optString("email").toString();

                    data += "Node"+i+" : \n id= "+ id +" \n Name= "+ name +" \n Email= "+ email +" \n \n";

                }

                tv.setText(data);



//                    // Get the full HTTP Data as JSONObject
//                    JSONObject reader= new JSONObject(stream);
//
//                    // Get the JSONObject "coord"...........................
//                    JSONObject coord = reader.getJSONObject("coord");
//                    // Get the value of key "lon" under JSONObject "coord"
//                    String lon = coord.getString("lon");
//                    // Get the value of key "lat" under JSONObject "coord"
//                    String lat = coord.getString("lat");
//
//                    tv.setText("We are processing the JSON data....\n\n");
//                    tv.setText(tv.getText()+ "\tcoord...\n");
//                    tv.setText(tv.getText()+ "\t\tlon..."+ lon + "\n");
//                    tv.setText(tv.getText()+ "\t\tlat..."+ lat + "\n\n");
//
//                    // Get the JSONObject "sys".........................
//                    JSONObject sys = reader.getJSONObject("sys");
//                    // Get the value of key "message" under JSONObject "sys"
//                    String message = sys.getString("message");
//                    // Get the value of key "country" under JSONObject "sys"
//                    String country = sys.getString("country");
//                    // Get the value of key "sunrise" under JSONObject "sys"
//                    String sunrise = sys.getString("sunrise");
//                    // Get the value of key "sunset" under JSONObject "sys"
//                    String sunset = sys.getString("sunset");
//
//                    tv.setText(tv.getText()+ "\tsys...\n");
//                    tv.setText(tv.getText()+ "\t\tmessage..."+ message + "\n");
//                    tv.setText(tv.getText()+ "\t\tcountry..."+ country + "\n");
//                    tv.setText(tv.getText()+ "\t\tsunrise..."+ sunrise + "\n");
//                    tv.setText(tv.getText()+ "\t\tsunset..."+ sunset + "\n\n");
//
//                    // Get the JSONArray weather
//                    JSONArray weatherArray = reader.getJSONArray("weather");
//                    // Get the weather array first JSONObject
//                    JSONObject weather_object_0 = weatherArray.getJSONObject(0);
//                    String weather_0_id = weather_object_0.getString("id");
//                    String weather_0_main = weather_object_0.getString("main");
//                    String weather_0_description = weather_object_0.getString("description");
//                    String weather_0_icon = weather_object_0.getString("icon");
//
//                    tv.setText(tv.getText()+ "\tweather array...\n");
//                    tv.setText(tv.getText()+ "\t\tindex 0...\n");
//                    tv.setText(tv.getText()+ "\t\t\tid..."+ weather_0_id + "\n");
//                    tv.setText(tv.getText()+ "\t\t\tmain..."+ weather_0_main + "\n");
//                    tv.setText(tv.getText()+ "\t\t\tdescription..."+ weather_0_description + "\n");
//                    tv.setText(tv.getText()+ "\t\t\ticon..."+ weather_0_icon + "\n\n");

                // process other data as this way..............

            }catch(JSONException e){
                e.printStackTrace();
            }

        } // if statement end
    } // onPostExecute() end
}
