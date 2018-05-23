package ru.unturn.iksa.volountersevent;

import android.os.StrictMode;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Iksa on 11.03.2018.
 */

public class RequestsForServer {

    private static final String BASE_URL = "https://samsung.eatric.ru/";

    public static boolean registerCompany(String firstName, String secondName, String email, String hashPassword, String phoneNumber, String INN){
        return getRequest("?registerOrganisation&mail=" + email + "&firstName=" + firstName + "&secondName=" + secondName + "&password=" + hashPassword + "&phoneNumber=" + phoneNumber + "&INN=" + INN).trim().equals("1");
    }
    public static boolean registerClient(String firstName, String secondName, String email, String hashPassword, String phoneNumber){
        return getRequest("?registerClient&mail=" + email + "&firstName=" + firstName + "&secondName=" + secondName + "&password=" + hashPassword + "&phoneNumber=" + phoneNumber).trim().equals("1");
    }
    public static boolean checkClient(String mail, String password){
        boolean result = getRequest("?checkClient&mail=" + mail + "&password=" + password).trim().equals("1");
        return result;
    }
    public static boolean addEvent(){
        return getRequest("?addEvent").equals("1");
    }

    public static boolean enterToEvent(String emailClient, int idOfEvent){
        return getRequest("?enterToEvent&mail=" + emailClient + "&idOfEvent=" + idOfEvent).trim().equals("1");
    }

    public static News[] getNews(){
        String requestWithNews = getRequest("?getNews");
        ArrayList<News> allNewsList = new ArrayList<News>();
        try {
            JSONObject jsonResponse = new JSONObject(requestWithNews);
            JSONArray jsonData = jsonResponse.getJSONArray("news");
            for (int i = 0; i < jsonData.length(); i++) {
                JSONObject jsonObject = jsonData.getJSONObject(i);
                allNewsList.add(new News(jsonObject.getInt("id"), jsonObject.getString("header"), jsonObject.getString("shortVersion"),
                        jsonObject.getString("fullNews"), jsonObject.getString("linkImage"), jsonObject.getString("date")));
            }
            News[] allNewsArray = new News[allNewsList.size()];
            for (int i = 0; i < allNewsList.size(); i++) {
                allNewsArray[i] = allNewsList.get(i);
            }
            return allNewsArray;
        }catch (JSONException ex){

        }
        return new News[0];
    }

    public static Event[] getEvents(String city, String mail){
        String[] requestWithEvents = getRequest("?getEvents&city=" + city + "&mail=" + mail).trim().split("/");
        /* event[0] = ID of Event
           event[1] = Name of Event
           event[2] = Starter Of Event
           event[3] = Location
           event[4] = Description
           event[5] = date Of Event
        */
        ArrayList<Event> events = new ArrayList<Event>();
        for(String stringEvent : requestWithEvents){
            String[] event = stringEvent.split("%");
            Event classOfEvent = new Event(Integer.parseInt(event[0]), event[1], event[2], event[3], event[5], event[4]);
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
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(5000);
            urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Mobile Safari/537.36");
            urlConnection.connect();
            String result = readStream(urlConnection.getInputStream());
            Log.d("ResultRequest", result);
            return result;
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


