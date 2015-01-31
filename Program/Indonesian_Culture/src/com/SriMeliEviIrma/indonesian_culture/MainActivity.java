package com.SriMeliEviIrma.indonesian_culture;

import com.SriMeliEviIrma.indonesian_culture.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	Button btn_masuk;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
		
		btn_masuk = (Button) findViewById(R.id.btn_masuk);
		
		//jika button masuk dilklik
		btn_masuk.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "SELAMAT DATANG DI INDONESIAN CULTURE!", Toast.LENGTH_LONG).show();
				
				Intent i = new Intent(getApplicationContext(), ListProvinsi.class);
				startActivity(i);
			}
		});
		
        
		
	}

}
