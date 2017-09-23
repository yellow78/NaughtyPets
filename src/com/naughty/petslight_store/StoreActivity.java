package com.naughty.petslight_store;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.json.JSONException;
import org.json.JSONObject;

import com.naughty.petslight.R;
import com.naughty.petslight.MainActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class StoreActivity extends Activity {

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
	
	ImageButton mImageButton2;
	ImageButton mImageButton3;
	ImageButton mImageButton4;
	ImageButton mImageButton5;
	ImageButton mImageButton6;
	ImageButton mImageButton7;
	ImageButton mImageButton8;
	ImageButton mImageButton9;
	ImageButton mImageButton10;
	ImageButton mImageButton11;
	ImageButton mImageButton12;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.storelayout);
		// 弄琌Τ笴栏眀腹
		init(StoreActivity.this);
		
		// 代刚
		//points = 1000;
		
		mImageButton2 = (ImageButton) findViewById(R.id.imageButton2);
		mImageButton3 = (ImageButton) findViewById(R.id.imageButton3);
		mImageButton4 = (ImageButton) findViewById(R.id.imageButton4);
		mImageButton5 = (ImageButton) findViewById(R.id.imageButton5);
		mImageButton6 = (ImageButton) findViewById(R.id.imageButton6);
		
		view = (TextView) findViewById(R.id.textpoint);
		view.setText(String.valueOf(points));
	}

	public void backbtn(View v) {
		Intent storeintent = new Intent();
		storeintent.setClass(StoreActivity.this, com.naughty.petslight.MainActivity.class);
		StoreActivity.this.startActivity(storeintent);
		StoreActivity.this.finish();
	}

	public void imgbtn01(View v) {
		setpetUI = 1;
		petUI01 = 1;
		writeSettings(MainActivity.getInstance().getApplicationContext());
		Intent storeintent = new Intent();
		storeintent.setClass(StoreActivity.this, com.naughty.petslight.MainActivity.class);
		StoreActivity.this.startActivity(storeintent);
		StoreActivity.this.finish();

	}

	public void imgbtn02(View v) {
		
		if (points < 100) {
			AlertDialog.Builder dialog = new AlertDialog.Builder(StoreActivity.this);
			dialog.setTitle("翴计ぃì");
			dialog.setMessage("胐惠翴计100叫纗翴计");
			dialog.setPositiveButton("絋﹚", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					Intent storeintent = new Intent();
					storeintent.setClass(StoreActivity.this, com.naughty.petslight_shop.ShopActivity.class);
					StoreActivity.this.startActivity(storeintent);
					StoreActivity.this.finish();
				}
			});
			dialog.show();
		} else {
			if(petUI02 == 1){
				setpetUI = 2;
				writeSettings(MainActivity.getInstance().getApplicationContext());
				Intent storeintent = new Intent();
				storeintent.setClass(StoreActivity.this, com.naughty.petslight.MainActivity.class);
				StoreActivity.this.startActivity(storeintent);
				StoreActivity.this.finish();
			}
			else{
				setpetUI = 2;
				petUI02 = 1;
				points = points - 100;
				writeSettings(MainActivity.getInstance().getApplicationContext());
				view.setText(String.valueOf(points));
				Intent storeintent = new Intent();
				storeintent.setClass(StoreActivity.this, com.naughty.petslight.MainActivity.class);
				StoreActivity.this.startActivity(storeintent);
				StoreActivity.this.finish();
			}
		}

	}

	public void imgbtn03(View v) {
		
		if (points < 200) {
			AlertDialog.Builder dialog = new AlertDialog.Builder(StoreActivity.this);
			dialog.setTitle("翴计ぃì");
			dialog.setMessage("胐惠翴计200叫纗翴计");
			dialog.setPositiveButton("絋﹚", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					Intent storeintent = new Intent();
					storeintent.setClass(StoreActivity.this, com.naughty.petslight_shop.ShopActivity.class);
					StoreActivity.this.startActivity(storeintent);
					StoreActivity.this.finish();
				}
			});
			dialog.show();
		} else {
			if(petUI03 == 1){
				setpetUI = 3;
				writeSettings(MainActivity.getInstance().getApplicationContext());
				Intent storeintent = new Intent();
				storeintent.setClass(StoreActivity.this, com.naughty.petslight.MainActivity.class);
				StoreActivity.this.startActivity(storeintent);
				StoreActivity.this.finish();
			}
			else{
				setpetUI = 3;
				petUI03 = 1;
				points = points - 200;
				writeSettings(MainActivity.getInstance().getApplicationContext());
				view.setText(String.valueOf(points));
				Intent storeintent = new Intent();
				storeintent.setClass(StoreActivity.this, com.naughty.petslight.MainActivity.class);
				StoreActivity.this.startActivity(storeintent);
				StoreActivity.this.finish();
			}
		}

	}

	public void imgbtn04(View v) {
		
		if (points < 200) {
			AlertDialog.Builder dialog = new AlertDialog.Builder(StoreActivity.this);
			dialog.setTitle("翴计ぃì");
			dialog.setMessage("胐惠翴计200叫纗翴计");
			dialog.setPositiveButton("絋﹚", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					Intent storeintent = new Intent();
					storeintent.setClass(StoreActivity.this, com.naughty.petslight_shop.ShopActivity.class);
					StoreActivity.this.startActivity(storeintent);
					StoreActivity.this.finish();
				}
			});
			dialog.show();
		} else {
			if(petUI04 == 1){
				setpetUI = 4;
				writeSettings(MainActivity.getInstance().getApplicationContext());
				Intent storeintent = new Intent();
				storeintent.setClass(StoreActivity.this, com.naughty.petslight.MainActivity.class);
				StoreActivity.this.startActivity(storeintent);
				StoreActivity.this.finish();
			}
			else{
				setpetUI = 4;
				petUI04 = 1;
				points = points - 200;
				writeSettings(MainActivity.getInstance().getApplicationContext());
				view.setText(String.valueOf(points));
				Intent storeintent = new Intent();
				storeintent.setClass(StoreActivity.this, com.naughty.petslight.MainActivity.class);
				StoreActivity.this.startActivity(storeintent);
				StoreActivity.this.finish();
			}
		}

	}

	public void imgbtn05(View v) {
		
		if (points < 400) {
			AlertDialog.Builder dialog = new AlertDialog.Builder(StoreActivity.this);
			dialog.setTitle("翴计ぃì");
			dialog.setMessage("胐惠翴计400叫纗翴计");
			dialog.setPositiveButton("絋﹚", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					Intent storeintent = new Intent();
					storeintent.setClass(StoreActivity.this, com.naughty.petslight_shop.ShopActivity.class);
					StoreActivity.this.startActivity(storeintent);
					StoreActivity.this.finish();
				}
			});
			dialog.show();
		} else {
			if(petUI05 == 1){
				setpetUI = 5;
				writeSettings(MainActivity.getInstance().getApplicationContext());
				Intent storeintent = new Intent();
				storeintent.setClass(StoreActivity.this, com.naughty.petslight.MainActivity.class);
				StoreActivity.this.startActivity(storeintent);
				StoreActivity.this.finish();
			}
			else{
				setpetUI = 5;
				petUI05 = 1;
				points = points - 400;
				writeSettings(MainActivity.getInstance().getApplicationContext());
				view.setText(String.valueOf(points));
				Intent storeintent = new Intent();
				storeintent.setClass(StoreActivity.this, com.naughty.petslight.MainActivity.class);
				StoreActivity.this.startActivity(storeintent);
				StoreActivity.this.finish();
			}
		}

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
}
