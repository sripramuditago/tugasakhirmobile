package com.SriMeliEviIrma.indonesian_culture;

import java.util.ArrayList;
import java.util.HashMap;

import com.SriMeliEviIrma.indonesian_culture.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressLint("InflateParams")
public class LazyAdapter extends BaseAdapter {
    
    private Activity activity;
    private ArrayList<HashMap<String, String>> data;
    private static LayoutInflater inflater=null;
    public ImageLoader imageLoader; 
    
    public LazyAdapter(Activity a, ArrayList<HashMap<String, String>> d) {
        activity = a;
        data = d ;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageLoader=new ImageLoader(activity.getApplicationContext());
    }
    

    public int getCount() {
		return data.size();
	}

	public Object getItem(int position) {
		return position;
	}

	public long getItemId(int position) {
		return position;
	}
    
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.list_row, null);

        TextView idprov = (TextView) vi.findViewById(R.id.kode);
        TextView nmprov =(TextView)vi.findViewById(R.id.nm_provinsi);
        ImageView image_prov =(ImageView)vi.findViewById(R.id.gambar);
        
        //imageLoader.DisplayImage(data[position], image_prov);
        
        HashMap<String, String> daftar_provinsi = new HashMap<String, String>();
        daftar_provinsi = data.get(position);
        
        idprov.setText(daftar_provinsi.get(ListProvinsi.TAG_ID));
        nmprov.setText(daftar_provinsi.get(ListProvinsi.TAG_NAMA_PROVINSI));
        imageLoader.DisplayImage(daftar_provinsi.get(ListProvinsi.TAG_GAMBAR), image_prov);
        
        return vi;
    }
    
    
}