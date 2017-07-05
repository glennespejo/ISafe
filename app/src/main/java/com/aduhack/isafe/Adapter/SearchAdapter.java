package com.aduhack.isafe.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.aduhack.isafe.Common.CircleImageView;
import com.aduhack.isafe.Model.SearchRowViewModel;
import com.aduhack.isafe.R;

import java.util.ArrayList;

/**
 * Created by Jee.Gloria on 3/21/2015.
 */
public class SearchAdapter  extends BaseAdapter {
    private Context _context;
    ArrayList<SearchRowViewModel> _items;
    private ViewHolder _holder;
    private boolean hasPassed = false;

    public SearchAdapter(Context context, ArrayList<SearchRowViewModel> items) {
        _context = context;
        _items = items;
    }

    @Override
    public int getCount() {
        return _items.size();
    }

    @Override
    public Object getItem(int position) {
        return _items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if(view == null) {
            view = View.inflate(_context, R.layout.search_row, null);
            _holder = new ViewHolder();
            _holder.initialize(view);
            view.setTag(_holder);
        }
        else {
            _holder = (ViewHolder) view.getTag();
        }

        _holder.location.setText(_items.get(position).getLocation());
        _holder.details.setText(_items.get(position).getDetails());
        _holder.incident_type.setText(_items.get(position).getIncidentType().substring(0,1));
        _holder.incidenttv.setText(_items.get(position).getIncidentType());



        return view;
    }

    private static class ViewHolder  {
        public TextView location;
        public TextView details, incidenttv;
        public Button incident_type;



        public void initialize(View view)   {

            location = (TextView)view.findViewById(R.id.location);
            details = (TextView)view.findViewById(R.id.details);
            incident_type = (Button)view.findViewById(R.id.incident_type);
            incidenttv = (TextView)view.findViewById(R.id.incident_typeTV);

        }
    }

}
