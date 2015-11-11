package com.nguyen.showinstalledapp;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<ApplicationInfo> {
    private Context context;
    private int resource;
    private ArrayList<ApplicationInfo> listData;
    private PackageManager pm;
    private TextView appName, appPackage;
    private ImageView imageIcon;

    public ListAdapter(Context context_, int resource_, ArrayList<ApplicationInfo> listData_) {
        super(context_,resource_,listData_);
        this.context = context_;
        this.resource = resource_;
        this.listData = listData_;
        pm = context.getPackageManager();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ( LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_row_list_view,null);

        appName = (TextView) view.findViewById(R.id.appName);
        appPackage = (TextView) view.findViewById(R.id.appPackage);
        imageIcon = (ImageView) view.findViewById(R.id.imageIcon);

        ApplicationInfo tmp = listData.get(position);
        appName.setText(tmp.loadLabel(pm));
        appName.setText(tmp.packageName);
        imageIcon.setImageDrawable(tmp.loadLogo(pm));

        return view;
    }
}
