package com.example.ventasporcatalogo;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	TextView e1, e2, e3, e4;
	RadioButton r1,r2;
	CheckBox cb1;
	TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		e1 = (EditText) findViewById(R.id.editText1);
		e2 = (EditText) findViewById(R.id.editText2);
		e3 = (EditText) findViewById(R.id.editText3);
		e4 = (EditText) findViewById(R.id.editText4);
		cb1 = (CheckBox) findViewById(R.id.checkBox1);
		r1 = (RadioButton) findViewById(R.id.radio0);
		r2 = (RadioButton) findViewById(R.id.radio1);
	}
	
	 public void altas(View v)
	 {
		 Administrador a = new Administrador(this, "productos", null, 1);
		 SQLiteDatabase db = a.getWritableDatabase();
		 
		 String codigo = e1.getText().toString();
		 String nombre = e2.getText().toString();
		 String genero = e3.getText().toString();
		 String precio = e4.getText().toString();
		 
		 if(codigo.equals(""))
			{
				Toast t1=Toast.makeText(this, "Debe ingresar un código", Toast.LENGTH_LONG);
				t1.setGravity(Gravity.CENTER, 0, 0);
				t1.show();
			}else{
		 
		 ContentValues registro = new ContentValues();
		 
		 registro.put("codigo", codigo);
		 registro.put("nombre", nombre);
		 registro.put("genero", genero);
		 registro.put("precio", precio);
		 
		 db.insert("productos", null, registro);
		 
		 Toast.makeText(this, "El producto se grabó correctamente", Toast.LENGTH_LONG).show();
		 
		 //Limpiar campos
		 e1.setText("");
		 e2.setText("");
		 e3.setText("");
		 e4.setText("");
		 
		 db.close();
			}
	 }
	 
		public void consultas(View v)
		{
			Administrador a = new Administrador(this, "productos", null, 1);
			SQLiteDatabase db = a.getWritableDatabase();
			 
			String cod = e1.getText().toString();
			
			 if(cod.equals(""))
				{
					Toast t1=Toast.makeText(this, "Debe ingresar un código", Toast.LENGTH_LONG);
					t1.setGravity(Gravity.CENTER, 0, 0);
					t1.show();
				}else{
			 
			String sql = "Select nombre, genero, precio from productos where codigo="+cod+"";
			Cursor fila = db.rawQuery(sql,null);
			
	    	if(fila.moveToFirst())
			{
				e2.setText(fila.getString(0));
				e3.setText(fila.getString(1));
				e4.setText(fila.getString(2));
				
			}
			else
			{
				Toast t1 = Toast.makeText(this, "No se encontró el Codigo "+ cod,Toast.LENGTH_LONG);
				
				// Para centrar el Toast en el Activity
				t1.setGravity(Gravity.CENTER, 0, 0);
				
				t1.show();
				e1.setText("");
			}
			db.close();   
			}
		}
		
		public void importe (View v){
			 String nombre = e2.getText().toString();
			 String genero  = e3.getText().toString();
			 String precio = e4.getText().toString();
			 
			 if(precio.equals("")){
				 Toast t1=Toast.makeText(this, "Debe ingresar un producto", Toast.LENGTH_LONG);
					t1.setGravity(Gravity.CENTER, 0, 0);
					t1.show();
			 }else{
			 
			 float pp= Float.parseFloat(precio);
			 
		
			 
			 
			 String total = precio;
			 if(r2.isChecked())
			 { 
				 pp = pp * 1.20f;
			     total = String.valueOf(pp);
			 } else {
				 if (r1.isChecked()){
					 pp = pp * 0.90f;
					 total = String.valueOf(pp);
				 }
			 }
			
			 if(cb1.isChecked()){
				 pp = pp + 100;
				 total = String.valueOf(pp);
			 }
			 
			 Intent i = new Intent (this,Importe.class);
			 i.putExtra("nombre", nombre);
			 i.putExtra("genero", genero);
			 i.putExtra("precio", precio);
			 i.putExtra("total", total);
			 
			 startActivity(i);
			 }
		}
		
		public void salir(View v)	
		 {
			 Toast t1=Toast.makeText(this, "--- Fin del Programa ---", Toast.LENGTH_LONG);
			 
			 TextView vista = (TextView) t1.getView().findViewById(android.R.id.message); 
			 vista.setTextColor(Color.GREEN);  

			 t1.setGravity(Gravity.CENTER, 0, 0); t1.show();
			 finish();
			 }

}
