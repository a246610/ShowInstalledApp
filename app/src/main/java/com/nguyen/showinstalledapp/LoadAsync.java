package com.nguyen.showinstalledapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class LoadAsync extends AsyncTask <Void,Void, ListAdapter>{

    private Context context;
    private ListView listView;
    private ProgressDialog dialog;
    private PackageManager pm;
    private List<ApplicationInfo> listInfo;
    private List<ApplicationInfo> listData;

    public LoadAsync(Context context_, ListView listView_){
        this.context = context_;
        this.listView = listView_;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        dialog = new ProgressDialog(context);
        dialog.setTitle("Waiting");
        dialog.setMessage("On Progressing ... ");
        dialog.show();

        pm = context.getPackageManager();
    }

    @Override
    protected  ListAdapter doInBackground(Void... params) {
        listInfo = pm.getInstalledApplications(PackageManager.GET_META_DATA);
        listData = new ArrayList<ApplicationInfo>();

        for (ApplicationInfo app: listInfo) {
            if (pm.getLaunchIntentForPackage(app.packageName) != null)
                listData.add(app);
        }

        ListAdapter adapter = new ListAdapter(context, R.layout.custom_row_list_view, listData);
        return adapter;
    }

    @Override
    protected void onPostExecute(ListAdapter res) {
        super.onPostExecute(res);

        listView.setAdapter(res);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = pm.getLaunchIntentForPackage(listData.get(position).packageName);
                context.startActivity(i);


            }
        });

        dialog.dismiss();
    }
}
