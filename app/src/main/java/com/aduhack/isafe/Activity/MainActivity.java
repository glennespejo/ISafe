package com.aduhack.isafe.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.aduhack.isafe.Common.CircleImageView;
import com.aduhack.isafe.Fragment.FragmentReport;
import com.aduhack.isafe.Fragment.FragmentSearch;
import com.aduhack.isafe.R;


public class MainActivity extends ActionBarActivity {
    private Button BTsearch;
    private ImageButton BTfileReport;
    private EditText SearchET;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setTitle("iSafe");
        setSupportActionBar(toolbar);



        BTsearch = (Button)findViewById(R.id.searchButton);
        BTfileReport = (ImageButton)findViewById(R.id.fileReportButton);
        SearchET = (EditText)findViewById(R.id.search_text);

        BTsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(SearchET.getText().toString().length()> 0){
                Bundle bundle = new Bundle();
                bundle.putString("searchText", SearchET.getText().toString());

                FragmentSearch fs = new FragmentSearch();
                fs.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.container, fs).addToBackStack("safe").commit();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Search Invalid", Toast.LENGTH_SHORT).show();
                }
            }
        });

        BTfileReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), FileReportActivity.class);
                startActivity(i);
            }
        });
    }


   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }*/

   /* @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    @Override
    public void onBackPressed() {
        if( getSupportFragmentManager().getBackStackEntryCount()>0) getSupportFragmentManager().popBackStack();
        else
            super.onBackPressed();
    }
}
