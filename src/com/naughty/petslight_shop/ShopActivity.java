package com.naughty.petslight_shop;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.json.JSONException;
import org.json.JSONObject;

import com.naughty.petslight.R;
import com.naughty.petslight.MainActivity;
import com.naughty.petslight_store.StoreActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class ShopActivity extends Activity {

	private final String GPKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtwUDNLSGtmjZiV5Y2hgPCMIHTTMXIejeATMcV30tKREvonX1jFNUr1U0BuJNAyTLiHCOaJPB0f98w00Rzyi3p+I0+Glqae3o7PNyODFS24ycwBp5c7zBkImY7TIdGyY/iSGo21Jf6VezgKFK08pWv7AR2eJOpxyl0hkMs1l2V+Y/Is7gnZlY84WnMYn4eIxbzTpqkVq+m4U2E6yEdOLdcuhcHsEsD2UENUpHvKtzY7TvS1MkDiui6VD0xIcQziAJdO/7T+Hhzhd4LEMluq59F28cmMHiBw1xwAUG+nB3NB2ZSuSbgiiFq4DBsEZkTyAlIS0KG8L1/GmSNww33cuiRwIDAQAB";
	private final String TapjoyId = "c022786c-30a1-491f-8f74-c34da25b24d8";
	private final String TapjoyAPIKey = "wCJ4bDChSR-PdMNNolsk2AECfqeylPMkXzMVi21SlvRCOIAYhLgD5VYbPKn5";

	private final String NTGServerId = "";
	private final String NTGGameId = "151100005";
	private final String NTGAPIKey = "bafaca547f1a4f65";
	private final String TAG = "petslight";

	private static JSONObject mSettings;

	public static String roleid = "";
	public static int points = 0;
	public static int petUI01 = 1;
	public static int petUI02 = 0;
	public static int petUI03 = 0;
	public static int petUI04 = 0;
	public static int petUI05 = 0;
	public static int setpetUI = 1;

	TextView view;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shoplayout);

		init(ShopActivity.this);

		view = (TextView) findViewById(R.id.textshoppoint);
		view.setText(String.valueOf(points));
		
	}

	private static void readSettings(Context context) {
		File saveFile = new File(context.getFilesDir().getPath() + "/petslight.dat");
		if (!saveFile.exists()) {
			try {
				mSettings = new JSONObject();
				mSettings.put("points", 0);
				mSettings.put("roleid", "");
				mSettings.put("petUI01", 0);
				mSettings.put("petUI02", 0);
				mSettings.put("petUI03", 0);
				mSettings.put("petUI04", 0);
				mSettings.put("petUI05", 0);
				mSettings.put("setpetUI", 1);
				saveFile.createNewFile();
				FileOutputStream stream = new FileOutputStream(saveFile);
				stream.write(mSettings.toString().getBytes("UTF-8"));
				stream.flush();
				stream.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				FileInputStream stream = new FileInputStream(saveFile);
				byte[] buffer = new byte[stream.available()];
				stream.read(buffer);
				stream.close();

				mSettings = new JSONObject(new String(buffer, "UTF-8"));

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void writeSettings(Context context) {
		try {
			mSettings.put("points", points);
			mSettings.put("roleid", roleid);
			mSettings.put("petUI01", petUI01);
			mSettings.put("petUI02", petUI02);
			mSettings.put("petUI03", petUI03);
			mSettings.put("petUI04", petUI04);
			mSettings.put("petUI05", petUI05);
			mSettings.put("setpetUI", setpetUI);
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		File saveFile = new File(context.getFilesDir().getPath() + "/petslight.dat");
		FileOutputStream stream;
		try {
			stream = new FileOutputStream(saveFile);
			stream.write(mSettings.toString().getBytes("UTF-8"));
			stream.flush();
			stream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void init(Activity act) {
		readSettings(act.getApplicationContext());
		try {
			points = mSettings.getInt("points");
			roleid = mSettings.getString("roleid");
			petUI01 = mSettings.getInt("petUI01");
			petUI02 = mSettings.getInt("petUI02");
			petUI03 = mSettings.getInt("petUI03");
			petUI04 = mSettings.getInt("petUI04");
			petUI05 = mSettings.getInt("petUI05");
			setpetUI = mSettings.getInt("setpetUI");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void shopbackbtn(View v) {
		Intent storeintent = new Intent();
		storeintent.setClass(ShopActivity.this, com.naughty.petslight.MainActivity.class);
		ShopActivity.this.startActivity(storeintent);
		ShopActivity.this.finish();
	}

	public void shop30(View v) {
		
	}

	public void shop60(View v) {
		
	}

	public void shop150(View v) {
		
	}

	public void shop300(View v) {
		
	}

}
