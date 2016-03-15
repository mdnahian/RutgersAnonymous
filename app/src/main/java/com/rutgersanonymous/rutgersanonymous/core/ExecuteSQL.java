package com.rutgersanonymous.rutgersanonymous.core;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by mdislam on 3/14/16.
 */
public class ExecuteSQL extends AsyncTask<String, Void, String> {

    public ExecuteSQLResponse delegate = null;

    @Override
    protected String doInBackground(String... params) {
        String response = getResponseFromSQL(params[0], params[1], params[2]);

        if(response == null){
            Log.d("Crash", "Response was null");
        }

        return getResponseFromSQL(params[0], params[1], params[2]);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        delegate.processFinish(s);
    }

    public String getResponseFromSQL(String sql, String requestURL, String apiKey){

        StringBuilder tempBuffer = new StringBuilder();

        try {

            URL url = new URL(requestURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            OutputStream os = connection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

            String data = Uri.encode("api_key", "UTF-8")+"="+Uri.encode(apiKey, "UTF-8")+"&"+Uri.encode("sql", "UTF-8")+"="+Uri.encode(sql, "UTF-8");

            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
            os.close();

            InputStream is = connection.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);

            int charRead;
            char[] inputBuffer = new char[500];

            while (true) {
                charRead = isr.read(inputBuffer);
                if (charRead <= 0) {
                    break;
                }

                tempBuffer.append(String.copyValueOf(inputBuffer, 0, charRead));
            }

            return tempBuffer.toString();

        } catch (IOException e ){
            Log.d("LoginPrompt", "Error 201: Could not read data.");
        } catch (SecurityException a){
            Log.d("LoginPrompt", "Error 203: Do not have the permissions to connect to URL.");

        }

        return null;

    }

}
