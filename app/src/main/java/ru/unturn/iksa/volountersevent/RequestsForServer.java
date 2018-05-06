package ru.unturn.iksa.volountersevent;

import android.os.AsyncTask;
import android.os.StrictMode;
import android.text.Html;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * Created by Iksa on 11.03.2018.
 */

public class RequestsForServer {

    private static final String BASE_URL = "http://unturn.ru/samsung/";

    public void registerOrganisation(){
        getRequest("?registerOrganisation");
    }
    public void registerClient(){
        getRequest("?registerClient");
    }
    public void addEvent(){
        getRequest("?addEvent");
    }

    public static Event[] getEvents(String city){
        String[] requestWithEvents = getRequest("?getEvents&city=" + city).split("/");
        /* event[0] = ID of Event
           event[1] = Name of Event
           event[2] = Starter Of Event
           event[3] = Location
           event[4] = date Of Event
        */
        ArrayList<Event> events = new ArrayList<Event>();
        for(String stringEvent : requestWithEvents){
            String[] event = stringEvent.split("%");
            Event classOfEvent = new Event(Integer.parseInt(event[0]), event[1], event[2], event[3], event[4]);
            events.add(classOfEvent);
        }
        Event[] justTest = new Event[events.size()];
        for (int i = 0; i < events.size(); i++) {
            justTest[i] = events.get(i);
        }
        return justTest;
    }

    private static String getRequest(String params) {
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            URL url = new URL(BASE_URL + params);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(5000);
            urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Mobile Safari/537.36");
            urlConnection.connect();
            return readStream(urlConnection.getInputStream());
        }catch (IOException ex){
            ex.printStackTrace();
            return null;
        }
    }

    private static String readStream(InputStream is) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            Log.e("TEST", "IOException", e);
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                Log.e("TEST", "IOException", e);
            }
        }
        return sb.toString();
    }
}
