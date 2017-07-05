package com.aduhack.isafe.Helper;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.aduhack.isafe.Model.SearchRowViewModel;

import java.util.ArrayList;

/**
 * Created by Jee.Gloria on 3/21/2015.
 */
public class SearchTask extends AsyncTask<Void, Void, ArrayList<SearchRowViewModel>>
    {

        HttpManager hm;
       // DataHelper dh;
        String searchText;
        public SearchTask(String _search) {
//            dh = new DataHelper(context);
//            dh.open();
            searchText = _search;
            hm = new HttpManager();
//            rm = model;
        }

        @Override
        protected ArrayList<SearchRowViewModel> doInBackground(Void... params) {
            ArrayList<SearchRowViewModel> items = hm.Search(searchText);

            return items;
        }

        @Override
        protected void onPostExecute(ArrayList<SearchRowViewModel> result) {
            super.onPostExecute(result);
        }



}
