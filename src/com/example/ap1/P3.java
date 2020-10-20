package com.example.ap1;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class P3 extends Activity {
	
	private DatePicker dp;
	private Spinner sp1;
	private Spinner sp2;
	private Button bConsultar;
	
	private int fecha[] = new int [3];
	private String pe3[];
	private String estados[];
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_p3);
		
		/*el objeto Bundle pe2 sirve como receptor del Intent
		 * del Activity anterior, el metodo getIntent()
		 * optiene al Intent, el metodo getExtras()
		 * identifica los datos enviados*/
		
		Bundle pe2 = getIntent().getExtras();
		/*pe3 es un Array de tipo String, que 
		 * es igual resultado obtenido por el 
		 * metodo getStringArray(), el cual 
		 * recibe como parametro un String, el
		 * cual coresponde a una llave o identificador 
		 * de un Intent lanzado en un Activity 
		 * anterior*/

	    pe3 = pe2.getStringArray("datosP2");
		
		bConsultar = (Button)findViewById(R.id.buttonConsultar);
		sp1 = (Spinner)findViewById(R.id.spinnerSex);
		sp2 = (Spinner)findViewById(R.id.spinnerEstado);
		dp = (DatePicker)findViewById(R.id.datePickerFecha);
		
		/*este es un Arreglo estatico; podria ser llenado de forma
		 * dinamica y luego ser cargado dentro de un ArrayAdapter
		 * de tipo String para luego ser visualizado dentro de un 
		 * Spinner o un ListView*/
		estados = new String[] {"Aguscalientes",
				"Baja California Sur","Baja California",
				"Campeche","Chiapas","Chihuahua","Coahuila",
				"Colima","Distrito Federal","Durango",
				"Guadalajara","Guerrero","Hidalgo",
				"Jalisco","México","Michoacan","Morelos",
				"Nayarid","Nuevo Leon","Oaxaca","Puebla",
				"Queretaro","Quintana Roo","San Luis Potosi",
				"Sinaloa","Sonora","Tabasco","Tamaulipas",
				"Tlaxcala","Veracruz","Yucatán","Zacatecas",
				"Nacido en el Extranjero"};
		
		/*Un ArrayAdapter de tipo String, sirve para cargar
		 * un arreglo de tipo String a un Spinner, en este caso,
		 * no obstante es posible cargarlo dentro de un 
		 * ListView, este ArrayAdapter recibe como parametros 
		 * de entrada a la propia clase, a un objeto preconstruido
		 * o un spinner propio personalizado, y a un arreglo de 
		 * tipo String; cargar elementos a un Spinner de este
		 * modo es muy util cuando los datos dentro del Spinner
		 * se desea que se agreguen de forma dinamica*/
		ArrayAdapter <String> adaptador = new ArrayAdapter<String>
		(this,android.R.layout.simple_spinner_item,estados);
		adaptador.setDropDownViewResource
		(android.R.layout.simple_spinner_dropdown_item);
		
		/*se carga el adaptador dentro del Spinner.*/
		sp2.setAdapter(adaptador);
		
		/*el metodo setOnItemSelectedListener, sirve para
		 * llamar a un Elemento dentro de un Spinner*/
		sp2.setOnItemSelectedListener
		(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected
			(AdapterView<?> parent, 
					android.view.View vis,int pos,long id){
				
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				/*Aqui se escribe el codigo que se desea ejecutar
				 * cuando se elije una opcion en el objeto
				 * Spinner, esto es util cuando se desea usar
				 * al objeto Spinner como un menu de opciones*/				
			}
		});
		
		bConsultar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				/*se optiene la ultima posicion visible en 
				 * el Spinner, gracias al metodo
				 *  getLastVisiblePosition()*/
				int position = sp1.getLastVisiblePosition();
				/*se optiene el valor en el objeto Spinner
				 * mediant su posicion con el metodo
				 * getItemAtPosition(), el cual recibe un
				 * valor entero*/
				String sex = sp1.getItemAtPosition(position).toString().toUpperCase();
				
				int position2 = sp2.getLastVisiblePosition();                     
				String est = sp2.getItemAtPosition(position2).toString().toUpperCase();
				
				fecha[0]=dp.getDayOfMonth();//se obtiene el dia
				/*se obtiene el mes y se le suma 1 debido a
				 * que el rango obtenido es de 0 a 11*/
				fecha[1]=dp.getMonth()+1;
				fecha[2]=dp.getYear();//se obtiene el año
				
				Curp miCurp = new Curp();
				miCurp.setNombre(pe3[0]);
				miCurp.setPaterno(pe3[1]);
				miCurp.setMaterno(pe3[2]);
				miCurp.setSexo(sex);
				miCurp.setEstado(est);
				
				miCurp.setDD(fecha[0]);
				miCurp.setMM(fecha[1]);
				miCurp.setYYYY(fecha[2]);

				dialogo(miCurp.generaCurp(miCurp.generaClave(position2)));
			}
		});
	}
	
	/*se define un metodo propio el 
	 * cual recibe un String como parametro*/
	public void dialogo(String mensage){
		/*se crea un nuevo objeto de tipo
		 *AlertDialog.Builder el cual recibe
		 *como parametro la propia clase */
		AlertDialog.Builder rc = new AlertDialog.Builder(P3.this);
		rc.setTitle("C.U.R.P.");
		rc.setMessage(mensage);
		rc.setPositiveButton("Aceptar", null);
		rc.show();		
	}

}
