package com.aduhack.isafe.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.aduhack.isafe.Activity.DetailsActivity;
import com.aduhack.isafe.Adapter.SearchAdapter;
import com.aduhack.isafe.Helper.SearchTask;
import com.aduhack.isafe.Model.SearchRowViewModel;
import com.aduhack.isafe.R;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentSearch extends android.support.v4.app.Fragment  {
    private ListView searchList;
    private String searchText;

    public FragmentSearch() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_search, container, false);
        // Inflate the layout for this fragment
        Bundle bundle = getArguments();
        ArrayList<SearchRowViewModel> items = null;
        if(bundle != null) {
            searchText = getArguments().getString("searchText");
            try {
                items =  new SearchTask(searchText).execute().get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        //TODO: search text api
        SearchAdapter sa = new SearchAdapter(getActivity().getApplicationContext(), items);

        searchList = (ListView)v.findViewById(R.id.searchList);
        searchList.setAdapter(sa);
        sa.notifyDataSetChanged();

        searchList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView location = (TextView)view.findViewById(R.id.location);
                TextView details = (TextView)view.findViewById(R.id.details);
                TextView incidenttv = (TextView)view.findViewById(R.id.incident_typeTV);

                Intent i = new Intent(getActivity(), DetailsActivity.class);
                i.putExtra("incidentType", incidenttv.getText());
                i.putExtra("details", details.getText());
                i.putExtra("location", location.getText());
                startActivity(i);
            }
        });

        return v;
    }


}
