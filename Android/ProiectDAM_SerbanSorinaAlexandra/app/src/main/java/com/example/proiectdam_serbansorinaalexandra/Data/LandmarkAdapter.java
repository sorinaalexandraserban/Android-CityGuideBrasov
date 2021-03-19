package com.example.proiectdam_serbansorinaalexandra.Data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.proiectdam_serbansorinaalexandra.R;

import java.util.List;

public class LandmarkAdapter extends BaseAdapter {

    Context context;
    int resource;
    List<Landmark> lista;

    public LandmarkAdapter(Context context, int resource, List<Landmark> lista) {
        this.context = context;
        this.resource = resource;
        this.lista = lista;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewAdapter adapter;

        if(convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resource, parent, false);
            adapter = new ViewAdapter();
            adapter.adapterName = convertView.findViewById(R.id.tv_adapter_name);
            adapter.adapterLocation = convertView.findViewById(R.id.tv_adapter_location);
            adapter.adapterType = convertView.findViewById(R.id.tv_adapter_type);
            convertView.setTag(adapter);
        }

        Landmark landmark = lista.get(position);
        adapter = (ViewAdapter)convertView.getTag();
        adapter.adapterName.setText(landmark.getName());
        adapter.adapterLocation.setText(landmark.getLocation());
        adapter.adapterType.setText(landmark.getType());

        return convertView;
    }

    static class ViewAdapter {
        TextView adapterName;
        TextView adapterLocation;
        TextView adapterType;
    }

    public void updateListLandmarks(List<Landmark> newLandmarks) {
        lista = newLandmarks;
        notifyDataSetChanged();
    }
}
