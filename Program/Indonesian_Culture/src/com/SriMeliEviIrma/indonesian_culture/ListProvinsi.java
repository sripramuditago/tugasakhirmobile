package com.SriMeliEviIrma.indonesian_culture;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.SriMeliEviIrma.indonesian_culture.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class ListProvinsi extends Activity {
	private ProgressDialog pDialog;
	
	JSONParser jParser = new JSONParser();
	
	ArrayList<HashMap<String, String>> DaftarProvinsi = new ArrayList<HashMap<String, String>>();
	
	private static String url_provinsi = "http://toyota-jambi.hol.es/indonesian_culture/android/listprovinsi.php";
	
	public static final String TAG_ID = "id";
	public static final String TAG_NAMA_PROVINSI = "nama_prov";
	public static final String TAG_GAMBAR = "gambar";
	
	JSONArray string_json = null;
	
	ListView list;
	LazyAdapter adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_provinsi);
		
		DaftarProvinsi = new ArrayList<HashMap<String, String>>();
		new AmbilData().execute();
		list = (ListView) findViewById(R.id.list);
		
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap<String, String> map = DaftarProvinsi.get(position);
							
                // Starting new intent
                Intent in = new Intent(getApplicationContext(), DetailProvinsi.class);

                in.putExtra(TAG_ID, map.get(TAG_ID));
                in.putExtra(TAG_GAMBAR, map.get(TAG_GAMBAR));
                startActivity(in);
                finish();
			}
		});
		
	}
	
	public void SetListViewAdapter(ArrayList<HashMap<String, String>> prov)
	{
		adapter = new LazyAdapter(this, prov);
		list.setAdapter(adapter);
	}
	
	//class ambil data
	class AmbilData extends AsyncTask<String, String, String> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(ListProvinsi.this);
			pDialog.setMessage("Mohon tunggu...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}
		
		protected String doInBackground(String... args) {
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			JSONObject json = jParser.makeHttpRequest(url_provinsi, "GET", params);
			
			try {
				
				string_json = json.getJSONArray("prov");
				
				for(int i=0;i < string_json.length(); i++) {
					JSONObject c = string_json.getJSONObject(i);
					
					String id_data_prov = c.getString(TAG_ID);
					String nama_data_prov = c.getString(TAG_NAMA_PROVINSI);
					String link_image = c.getString(TAG_GAMBAR);
					
					HashMap<String, String> map = new HashMap<String, String>();
					
					map.put(TAG_ID, id_data_prov);
					map.put(TAG_NAMA_PROVINSI, nama_data_prov);
					map.put(TAG_GAMBAR, link_image);
					
					DaftarProvinsi.add(map);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			return null;
		}
		
		protected void onPostExecute(String file_url) {
			pDialog.dismiss();
			
			runOnUiThread(new Runnable() {
				public void run() {
					SetListViewAdapter(DaftarProvinsi);
				}
			});
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_provinsi, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId())
        {
        case R.id.exit :
        	keluar();
        	return true;
 
        default:
            return super.onOptionsItemSelected(item);
        }
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		keluar();
        return true;
	}
	
	public void keluar(){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Apakah Anda Ingin" + " keluar?")
		 	.setCancelable(false)
		 	.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
		 		public void onClick(DialogInterface dialog, int id) {
		 			finish();
		 		}
		 	})
		 	.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
		 		public void onClick(DialogInterface dialog, int id) {
		 			dialog.cancel();
		 		}
		 	}).show();
	}
	

}
