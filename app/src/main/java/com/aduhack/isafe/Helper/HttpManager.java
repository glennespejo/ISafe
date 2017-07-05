package com.aduhack.isafe.Helper;

import android.support.annotation.NonNull;
import android.util.Log;


import com.aduhack.isafe.Model.ReportModel;
import com.aduhack.isafe.Model.SearchRowViewModel;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.Array;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by jaggEd2 on 12/6/14.
 */
public class HttpManager {


    /*methods
    *  - getRestaurants
    *  -getmenu
    *  -getimages
    *
    *  post
    *  -send ratings
    *  -registeruser
    *  -setreservation
    *  -setorder
    * */
    private static String url = "";


    private static String Domain = "http://192.168.1.17:8080/isafe/api/v1.0/events";

    private static String url_search = Domain + "/search/";


    public HttpHelper _httpHelper;
   // private DataHelper _database;

    public HttpManager(){
        _httpHelper = new HttpHelper();
      //  _database = _data;
    }



    public ArrayList<SearchRowViewModel> Search(String searchText){

        String result="";
        try{
            result = EntityUtils.toString(_httpHelper.getResponse(url_search + searchText.replace(" ","%20")));
        }catch(IOException e){
            e.printStackTrace();
        }
        if(result != null){

            try {
                JSONObject jObject = new JSONObject(result);
                JSONArray results = jObject.getJSONArray("data");

                ArrayList<SearchRowViewModel> searchResult = new ArrayList<SearchRowViewModel>();
                SearchRowViewModel resultone;
                for (int i = 0; i < results.length(); i++) {
                    JSONObject a = results.getJSONObject(i);
                    resultone = new SearchRowViewModel();
                    resultone.setIncidentType(a.getString("incident"));
                    resultone.setDetails(a.getString("detail"));
                    resultone.setLocation(a.getString("location"));
                    searchResult.add(resultone);
                }

                return searchResult;
            }catch(JSONException e){
                e.printStackTrace();
            }
        }
        return null;
    }


    public void FileReport(ReportModel report){
        String result = "";
        List<NameValuePair> value = new ArrayList<NameValuePair>(3);

        value.add(new BasicNameValuePair("detail", report.getDetail()));
        value.add(new BasicNameValuePair("incident", report.getIncidentType()));
        value.add(new BasicNameValuePair("location", report.getLocation()));
        try{
            result = EntityUtils.toString(_httpHelper.postResponse(Domain, value));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
