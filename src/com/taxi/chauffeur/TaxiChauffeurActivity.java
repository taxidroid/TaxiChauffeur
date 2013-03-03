package com.taxi.chauffeur;


import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class TaxiChauffeurActivity extends Activity {
	private LocationManager objgps;
    private LocationListener objlistener;
	private TextView mTxtViewlong;
	private TextView mTxtViewlat;
	JSONParser jsonParser = new JSONParser();

	// single product url
	private static final String url_coordonnees_inserer = "http://taxidroid.hebergratuit.com/taxidroid/inserer_coord_chauffeur.php";
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_CONTACT = "contact";

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //---utilisation  de la class LocationManager pour le gps---
        objgps = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        //*************ecouteur ou listener*********************
        objlistener = new Myobjlistener();
 
        objgps.requestLocationUpdates(
            LocationManager.NETWORK_PROVIDER,120000,0,objlistener);
    }
    private class Myobjlistener implements LocationListener
    {
 
 
 
    	public void onProviderDisabled(String provider) {
            // TODO Auto-generated method stub
        }
 
 
        public void onProviderEnabled(String provider) {
            // TODO Auto-generated method stub
        }
 
 
        public void onStatusChanged(String provider, int status,
            Bundle extras) {
            // TODO Auto-generated method stub
        }
 
 
          public void onLocationChanged(Location location) {
        	  	     String lat=String.valueOf(location.getLatitude());
                	 String longi=String.valueOf(location.getLongitude());
                	 Log.d("lat", lat);
						Log.d("lon", longi);
                	 int success;
						try {
							// Building Parameters
							List<NameValuePair> params= new ArrayList<NameValuePair>();
							params.add(new BasicNameValuePair("lat", lat));
							params.add(new BasicNameValuePair("longi", longi));
							params.add(new BasicNameValuePair("id","1"));
							// getting product details by making HTTP request
							JSONObject json = jsonParser.makeHttpRequest(
									url_coordonnees_inserer, "GET", params);

							// check your log for json response
							Log.d("Delete Product", json.toString());
							
							// json success tag
							success = json.getInt(TAG_SUCCESS);
							if (success == 1) {
								JSONArray productObj = json
										.getJSONArray(TAG_CONTACT);
							// product = productObj.getJSONObject(0);
							//	String latitude = product.getString(TAG_LATITUDE);
							//	String longitude = product.getString(TAG_LONGITUDE);
								//EditText txtName = (EditText) findViewById(R.id.editText1);
								//txtName.setText(latitude);
								
							}
						} catch (JSONException e) {
							e.printStackTrace();
						}
     
          }
 
          }
}