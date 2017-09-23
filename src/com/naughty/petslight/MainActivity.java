package com.naughty.petslight;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Hashtable;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.naughty.petslight.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tapjoy.TJActionRequest;
import com.tapjoy.TJConnectListener;
import com.tapjoy.TJEarnedCurrencyListener;
import com.tapjoy.TJError;
import com.tapjoy.TJGetCurrencyBalanceListener;
import com.tapjoy.TJPlacement;
import com.tapjoy.TJPlacementListener;
import com.tapjoy.TJSpendCurrencyListener;
import com.tapjoy.TJVideoListener;
import com.tapjoy.Tapjoy;
import com.tapjoy.TapjoyConnectFlag;
import com.tapjoy.TapjoyLog;

public class MainActivity extends Activity implements TJGetCurrencyBalanceListener, TJPlacementListener{

	private static MainActivity mInstance;

	public static MainActivity getInstance() {
		return mInstance;
	}

	private TJPlacement directPlayPlacement;
	private TJPlacement offerwallPlacement;
	
	private final String GPKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAxCVkCDfhowriR3LR4mDaznTp9sBtCRGLjRMJhVjx0ItSXXR5Kij1b++h6cx+/Mzq4jYPzdxM+Mk89L6/de7RZ6N+Gb7BRVnX8vMy+bWgfYOAimShKZWZriui31eaL3oUcgFF200F+mL6AokYuxRy6VejGyHJn3g3x4wb4QprYR5EISEZFKmXQ+rTLCwKKZbmSbqLuxxSUC5zwHmUO+ikuen2EzUg824k98lZgWu8YPaaGFA+xWr/Zmr0AXMLTYDWwWlJyKJjDJyVOWwOylemHc1ZDY/pa+MRM6S2u0DlE5cs/iIUZbfoLd4Q971+R+qRhOJVXYQnKfSiAgGj/tz3/wIDAQAB";
	private final String TapjoyId = "7beb403f-3ea0-4577-be17-d07c0a696d42";
	private final String TapjoyAPIKey = "e-tAPz6gRXe-F9B8CmltQgECskI6QhxOX2YBnCcrJXmh_V2TivHndeim27bz";
	private final String NTGServerId = "";
	private final String NTGGameId = "160100002";
	private final String NTGAPIKey = "2cc6856111f44f3c";
	private final String TAG = "petslight";
	public static String roleid = "";
	public static int points = 0;
	public static int petUI01 = 1;
	public static int petUI02 = 0;
	public static int petUI03 = 0;
	public static int petUI04 = 0;
	public static int petUI05 = 0;
	public static int setpetUI = 1;

	private static JSONObject mSettings;

	LinearLayout layoutAd;
	LinearLayout layoutpet;

	Camera mCamera = null;

	boolean light = true;

	public int push = 1;

	ImageButton mImageButton1;
	TextView view;

	@Override
	protected void onResume() {

		super.onResume();

		mCamera = Camera.open();

	}

	public void on() {

		Parameters mParameters = mCamera.getParameters();

		mParameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);

		mCamera.setParameters(mParameters);

		mCamera.startPreview();

		light = true;

	}

	public void off() {

		Parameters mParameters = mCamera.getParameters();

		mParameters.setFlashMode(Parameters.FLASH_MODE_OFF);

		mCamera.setParameters(mParameters);

		// mCamera.stopPreview();

		light = false;

	}

	protected void onPause() {

		super.onPause();

		off(); 

		mCamera.release(); // Release Camera

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

	private boolean postRequired() {
		return Looper.myLooper() != this.getMainLooper();
	}

	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mInstance = this;
		
		AdView mAdView = (AdView) findViewById(R.id.layoutadmob);
        AdRequest adRequest = new AdRequest.Builder().build();
        //AdRequest adRequest = new AdRequest.Builder().addTestDevice("7BB034A969F50CED1ACA447D65980A7A").build();
        mAdView.loadAd(adRequest);

		init(MainActivity.this);

		//測試
		//points = 1000;

		mImageButton1 = (ImageButton) findViewById(R.id.imageButton1);
		layoutpet = (LinearLayout) this.findViewById(R.id.petlayout);

		switch (setpetUI) {
		case 1:
			mImageButton1.setImageResource(R.drawable.dagaon);
			layoutpet.setBackgroundResource(R.drawable.daga2);
			break;

		case 2:
			mImageButton1.setImageResource(R.drawable.dagbon);
			layoutpet.setBackgroundResource(R.drawable.dagb2);
			break;

		case 3:
			mImageButton1.setImageResource(R.drawable.dagcon);
			layoutpet.setBackgroundResource(R.drawable.dagc2);
			break;

		case 4:
			mImageButton1.setImageResource(R.drawable.cataon);
			layoutpet.setBackgroundResource(R.drawable.cata2);
			break;

		case 5:
			mImageButton1.setImageResource(R.drawable.catbon);
			layoutpet.setBackgroundResource(R.drawable.catb2);
			break;
		}

		view = (TextView) findViewById(R.id.usepoint);
		view.setText(String.valueOf(points));

		connectToTapjoy();
		Tapjoycheckpoint();
	}
	
	private void Tapjoycheckpoint(){
		Tapjoy.getCurrencyBalance(new TJGetCurrencyBalanceListener(){
			@Override
			public void onGetCurrencyBalanceResponse(String currencyName, int balance) {
				points = balance;
				writeSettings(MainActivity.getInstance().getApplicationContext());
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						if (view != null) {
							view.setText(String.valueOf(points));
						}
					}
				});
				Log.i(TAG, "getCurrencyBalance returned " + currencyName + ":" + balance);
			}
			@Override
			public void onGetCurrencyBalanceResponseFailure(String error) {
				Log.i(TAG, "getCurrencyBalance error: " + error);
			}
		});
	}
	
	/**
	 * Wrapper method to call {@link Tapjoy.#spendCurrency(int, TJSpendCurrencyListener}
	 */
	private void callSpendCurrency(int amount) {
		// Spend virtual currency
		Tapjoy.spendCurrency(amount, new TJSpendCurrencyListener() {
			@Override
			public void onSpendCurrencyResponse(String currencyName, int balance) {
				Log.i(TAG, "getCurrencyBalance returned " + currencyName + ":" + balance);
			}

			@Override
			public void onSpendCurrencyResponseFailure(String error) {
				Log.i(TAG, "error : " + error );
			}
		});
	}
	
	/**
	 * Attempts to connect to Tapjoy
	 */
	private void connectToTapjoy() {
		// OPTIONAL: For custom startup flags.
		Hashtable<String, Object> connectFlags = new Hashtable<String, Object>();
		connectFlags.put(TapjoyConnectFlag.ENABLE_LOGGING, "true");

		// If you are not using Tapjoy Managed currency, you would set your own
		// user ID here.
		// connectFlags.put(TapjoyConnectFlag.USER_ID, "A_UNIQUE_USER_ID");

		// Connect with the Tapjoy server. Call this when the application first
		// starts.
		// REPLACE THE SDK KEY WITH YOUR TAPJOY SDK Key.
		String tapjoySDKKey = "5rrtMm1tRw244oijbqOXDgEC7uclnvZrPpBIXTPZpeOyxWsro8VXt9_phXFp";

		// NOTE: This is the only step required if you're an advertiser.
		Tapjoy.connect(getApplicationContext(), tapjoySDKKey, connectFlags, new TJConnectListener() {
			@Override
			public void onConnectSuccess() {
				MainActivity.this.onConnectSuccess();
			}

			@Override
			public void onConnectFailure() {
				MainActivity.this.onConnectFail();
			}
		});
	}
	
	/**
	 * Handles a successful connect to Tapjoy. Pre-loads direct play placement
	 * and sets up Tapjoy listeners
	 */
	public void onConnectSuccess() {

		Log.e(TAG, "Tapjoy connect call Success");
	}

	/**
	 * Handles a failed connect to Tapjoy
	 */
	public void onConnectFail() {
		Log.e(TAG, "Tapjoy connect call failed");
	}

	/**
	 * Notify Tapjoy the start of this activity for session tracking
	 */
	@Override
	protected void onStart() {
		super.onStart();
		Tapjoy.onActivityStart(this);
	}

	/**
	 * Notify Tapjoy the end of this activity for session tracking
	 */
	@Override
	protected void onStop() {
		super.onStop();
		Tapjoy.onActivityStop(this);
		Tapjoycheckpoint();
	}
	
	private void callShowOffers() {
		// Construct TJPlacement to show Offers web view from where users can
		// download the latest offers for virtual currency.
		offerwallPlacement = new TJPlacement(this, "InsufficientCurrency", new TJPlacementListener() {
			@Override
			public void onRequestSuccess(TJPlacement placement) {
				
			}

			@Override
			public void onRequestFailure(TJPlacement placement, TJError error) {
				
			}

			@Override
			public void onContentReady(TJPlacement placement) {
				TapjoyLog.i(TAG, "onContentReady for placement " + placement.getName());

				placement.showContent();
			}

			@Override
			public void onContentShow(TJPlacement placement) {
				TapjoyLog.i(TAG, "onContentShow for placement " + placement.getName());
			}

			@Override
			public void onContentDismiss(TJPlacement placement) {
				TapjoyLog.i(TAG, "onContentDismiss for placement " + placement.getName());
			}

			@Override
			public void onPurchaseRequest(TJPlacement placement, TJActionRequest request, String productId) {
				TapjoyLog.i(TAG, "onPurchaseRequest " + placement.getName() + "productId : " + productId);
			}

			@Override
			public void onRewardRequest(TJPlacement placement, TJActionRequest request, String itemId, int quantity) {
				TapjoyLog.i(TAG, "onRewardRequest " + placement.getName() + "itemId : " + itemId + " quantity : " + quantity);
			}
		});
		offerwallPlacement.requestContent();
	}
	
//	public void buttonBUY(View v) {
//		Intent storeintent = new Intent();
//		storeintent.setClass(MainActivity.this, com.naughty.petslight_shop.ShopActivity.class);
//		MainActivity.this.startActivity(storeintent);
//		MainActivity.this.finish();
//	}

	public void buttonFree(View v) {
		callShowOffers();
	}

	public void buttonStore(View v) {
		Intent storeintent = new Intent();
		storeintent.setClass(MainActivity.this, com.naughty.petslight_store.StoreActivity.class);
		MainActivity.this.startActivity(storeintent);
		MainActivity.this.finish(); 
	}

	public void btnlight(View v) {

		switch (push) {
		case 1:
			switch (setpetUI) {
			case 1:
				mImageButton1.setImageResource(R.drawable.dagaon);
				layoutpet.setBackgroundResource(R.drawable.daga2);
				break;

			case 2:
				mImageButton1.setImageResource(R.drawable.dagbon);
				layoutpet.setBackgroundResource(R.drawable.dagb2);
				break;

			case 3:
				mImageButton1.setImageResource(R.drawable.dagcon);
				layoutpet.setBackgroundResource(R.drawable.dagc2);
				break;

			case 4:
				mImageButton1.setImageResource(R.drawable.cataon);
				layoutpet.setBackgroundResource(R.drawable.cata2);
				break;

			case 5:
				mImageButton1.setImageResource(R.drawable.catbon);
				layoutpet.setBackgroundResource(R.drawable.catb2);
				break;
			}
			on();
			push = push + 1;
			break;
		case 2:
			switch (setpetUI) {
			case 1:
				mImageButton1.setImageResource(R.drawable.dagaoff);
				layoutpet.setBackgroundResource(R.drawable.daga1);
				break;

			case 2:
				mImageButton1.setImageResource(R.drawable.dagboff);
				layoutpet.setBackgroundResource(R.drawable.dagb1);
				break;

			case 3:
				mImageButton1.setImageResource(R.drawable.dagcoff);
				layoutpet.setBackgroundResource(R.drawable.dagc1);
				break;

			case 4:
				mImageButton1.setImageResource(R.drawable.cataoff);
				layoutpet.setBackgroundResource(R.drawable.cata1);
				break;

			case 5:
				mImageButton1.setImageResource(R.drawable.catboff);
				layoutpet.setBackgroundResource(R.drawable.catb1);
				break;

			}
			off();
			push = 1;
			break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onContentDismiss(TJPlacement arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onContentReady(TJPlacement arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onContentShow(TJPlacement arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPurchaseRequest(TJPlacement arg0, TJActionRequest arg1, String arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onRequestFailure(TJPlacement arg0, TJError arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onRequestSuccess(TJPlacement arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onRewardRequest(TJPlacement arg0, TJActionRequest arg1, String arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onGetCurrencyBalanceResponse(String arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onGetCurrencyBalanceResponseFailure(String arg0) {
		// TODO Auto-generated method stub
		
	}
}