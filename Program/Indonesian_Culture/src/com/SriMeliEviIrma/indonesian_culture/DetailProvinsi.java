package com.SriMeliEviIrma.indonesian_culture;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.SriMeliEviIrma.indonesian_culture.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailProvinsi extends Activity {
	public ImageLoader imageLoader;
	{

		imageLoader = new ImageLoader(null);
	}
	JSONArray string_json = null;

	String id;

	private ProgressDialog pDialog;
	
	JSONParser jsonParser = new JSONParser();
	
	public static final String TAG_ID = "id";
	public static final String TAG_NAMA_PROVINSI = "nama_prov";
	public static final String TAG_GAMBAR = "gambar";
	private static String url_provinsi_detail = "http://toyota-jambi.hol.es/indonesian_culture/android/detail_listprovinsi.php";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail_provinsi);
		
		Intent i = getIntent();

		id = i.getStringExtra(TAG_ID);


		new AmbilDetailProv().execute();
	}
	
	
	class AmbilDetailProv extends AsyncTask<String, String, String> { 
		 
		@Override
		protected void onPreExecute() { 
			super.onPreExecute();
			pDialog = new ProgressDialog(DetailProvinsi.this);
			pDialog.setMessage("Mohon Tunggu ...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		protected String doInBackground(String... params) {
				try {

						List<NameValuePair> params1 = new ArrayList<NameValuePair>();
						params1.add(new BasicNameValuePair("id", id));

						JSONObject json = jsonParser.makeHttpRequest(url_provinsi_detail, "GET", params1);
						string_json = json.getJSONArray("prov");

							runOnUiThread(new Runnable() {
								public void run() {

									ImageView thumb_image = (ImageView) findViewById(R.id.imageView1);
							        TextView isi = (TextView) findViewById(R.id.content);

							try {
								// ambil objek member pertama dari JSON Array
								JSONObject ar = string_json.getJSONObject(0);
								String judul_d = ar.getString("nama_prov");
								String isi_d = ar.getString("keterangan");	
								
								isi.setText(isi_d);
								
								Window w = getWindow();
								w.setTitle(judul_d);
					        
								imageLoader.DisplayImage(ar.getString(TAG_GAMBAR),thumb_image);		        
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								//e.getMessage();
								}
						}
					});
							
					} catch (JSONException e) {
						e.printStackTrace();
				}

			return null;
		}
		
		protected void onPostExecute(String file_url) {
			pDialog.dismiss();
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detail_provinsi, menu);
		return true;
	}
	public boolean onOptionsItemSelected(MenuItem item)
    {
         
        switch (item.getItemId())
        {
        case R.id.home:
            // Single menu item is selected do something
            // Ex: launching new activity/screen or show alert message
        	finish();
			Intent i = new Intent(getApplicationContext(), ListProvinsi.class);
			startActivity(i);
            return true;
 
        default:
            return super.onOptionsItemSelected(item);
        }
    }
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		finish();
		Intent i = new Intent(getApplicationContext(), ListProvinsi.class);
		startActivity(i);
        return true;
	}
	
}
