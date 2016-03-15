package com.rutgersanonymous.rutgersanonymous.core;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by mdislam on 2/29/16.
 */
public class ParseServerResponse {

    private String json;
    private ArrayList<ArrayList> objects;

    public ParseServerResponse(String json) {
        this.json = json;
        objects = new ArrayList<>();
    }

    public ArrayList getObjects() {
        return objects;
    }

    public boolean execute(){

        boolean status;

        try {
            JSONArray jsonArray = new JSONArray(json);

            for(int i=0; i<jsonArray.length(); i++){

                JSONArray innerArray = jsonArray.getJSONArray(i);

                ArrayList<String> object = new ArrayList<>();

                for(int j=0; j<innerArray.length(); j++){
                    object.add(innerArray.get(j).toString());
                }

                objects.add(object);
            }

            status = true;

        }  catch (JSONException e){
            try {
                JSONObject jsonObject = new JSONObject(json);
                ArrayList<String> object = new ArrayList<>();

                for(int j=0; j<jsonObject.length(); j++){
                    object.add(jsonObject.getString("success"));
                    object.add(jsonObject.getString("id"));
                }

                objects.add(object);

                status = true;
            } catch (JSONException a){
                status = false;
            }
        }


        return status;
    }

}
