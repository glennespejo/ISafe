package com.aduhack.isafe.Helper;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;

public class HttpHelper {

    public boolean wifiConnected = false;
    public boolean mobileConnected = false;
    public boolean isConnected = false;

    public void updateConnectedFlags(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeInfo = connMgr.getActiveNetworkInfo();
        if (activeInfo != null && activeInfo.isConnected()) {
            this.wifiConnected = activeInfo.getType() == ConnectivityManager.TYPE_WIFI;
            this.mobileConnected = activeInfo.getType() == ConnectivityManager.TYPE_MOBILE;
        } else {
            this.wifiConnected = false;
            this.mobileConnected = false;
        }
    }

    public boolean isWifiConnected() {
        return wifiConnected;
    }

    public boolean isMobileConnected() {
        return mobileConnected;
    }

    public boolean isConnected() {
        return mobileConnected || wifiConnected;
    }


    // GET With Header
    public String getResponseWithHeader(String fulldomain, String token) {
        HttpResponse httpResponse;
        HttpEntity httpEntity = null;
        String jsonResult = "";
        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet get = new HttpGet(fulldomain);
            get.setHeader("Content-Type", "application/json");
            get.setHeader("Authorization", "Bearer " + token);
            httpResponse = httpClient.execute(get);
            httpEntity = httpResponse.getEntity();
            jsonResult = EntityUtils.toString(httpEntity);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonResult;
    }

    // GET
    public HttpEntity getResponse(String fulldomain) {
        HttpResponse httpResponse;
        HttpEntity httpEntity = null;
        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet get = new HttpGet(fulldomain);
            httpResponse = httpClient.execute(get);
            httpEntity = httpResponse.getEntity();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return httpEntity;
    }

    // POST
    public HttpEntity postResponse(String fulldomain, List<NameValuePair> values) {
        HttpResponse httpResponse;
        HttpEntity httpEntity = null;
        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(fulldomain);
            httpPost.setEntity(new UrlEncodedFormEntity(values));
            httpResponse = httpClient.execute(httpPost);
            Log.v("Response",httpResponse.toString());
            httpEntity = httpResponse.getEntity();
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return httpEntity;
    }
}
