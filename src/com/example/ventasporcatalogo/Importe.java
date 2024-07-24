package com.example.ventasporcatalogo;

import com.example.ventasporcatalogo.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class Importe extends Activity {
	TextView ta, tb, tc, td;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_importe);
		
		ta = (TextView) findViewById(R.id.textViewB);
		tb = (TextView) findViewById(R.id.textViewD);
		tc = (TextView) findViewById(R.id.textViewF);
		td = (TextView) findViewById(R.id.textViewH);
		
		Bundle b = getIntent().getExtras();
		
		ta.setText(b.getString("nombre"));
		tb.setText(b.getString("genero"));
		tc.setText("$ " + b.getString("precio"));
		td.setText("$ " + b.getString("total"));
	}
	
	  public void retornar(View v)
	  {
		  finish();
	  }
}
