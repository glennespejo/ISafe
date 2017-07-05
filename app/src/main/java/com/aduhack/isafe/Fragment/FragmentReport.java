package com.aduhack.isafe.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aduhack.isafe.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentReport extends android.support.v4.app.Fragment  {


    public FragmentReport() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_report, container, false);
    }


}
