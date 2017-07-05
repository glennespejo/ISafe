package com.aduhack.isafe.Activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.aduhack.isafe.Helper.FileReportTask;
import com.aduhack.isafe.Model.ReportModel;
import com.aduhack.isafe.R;

import java.util.concurrent.ExecutionException;

public class FileReportActivity extends ActionBarActivity {
    Spinner spinner_incident;
    EditText editText_details;
    TextView textView_location;
    Button button_setLocation, button_cancel, button_save;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filereport);

        spinner_incident = (Spinner)findViewById(R.id.spinner_incident);
        editText_details = (EditText)findViewById(R.id.editText_details);
        textView_location = (TextView)findViewById(R.id.textView_location);
        button_setLocation = (Button)findViewById(R.id.button_setLocation);
        button_cancel = (Button)findViewById(R.id.button_cancel);
        button_save = (Button)findViewById(R.id.button_save);

        button_setLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent setLocationIntent = new Intent(getApplicationContext(),SetLocationActivity.class);
                startActivityForResult(setLocationIntent, 1);
            }
        });
        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReportModel report = new ReportModel();
                report.setLocation(textView_location.getText().toString());
                report.setIncidentType(spinner_incident.getSelectedItem().toString());
                report.setDetail(editText_details.getText().toString());
                String a= "";
                try {
                    a= new FileReportTask(report).execute().get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                if(a.equals("Okay")) finish();

            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

            if (resultCode == RESULT_OK) {
                textView_location.setText(data.getStringExtra("location"));
            }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_file_report, menu);
        return true;
    }

    @Override
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
    }
}
