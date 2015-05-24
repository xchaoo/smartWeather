package com.icedcap.smartweathertest;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Calendar;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.R.string;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.icedcap.smartweathertest.R;

/**
 * ��������ַ��http://blog.csdn.net/icedcap/article/details/20743627
 * @author icedcap(��ֻ�Ǹ���)
 *
 */
@SuppressLint("HandlerLeak")
public class MainActivity extends Activity implements OnClickListener {
	private static final String TAG = "SmartWeather";
	private Button button1, button2, button3;
	private TextView tv1, tv2, tv3;

	private boolean flag = false;
	private String weatherAPIAddress = "http://open.weather.com.cn/data/?";
	private static final String APPID = "fd25afc604b5bc73";//��Կ����֮��
	private static final String AVAILABLE_APPID = "fd25af";//appidǰ��λhttp����ʱ�����
	private static final String PRIVATE_Key = "1fa7df_SmartWeatherAPI_3639f0f";//��Կ����֮��
	private String areaId = "";
	private String weatherType;
	private String date;
	private String token;// ��Կ����(����)
	private String fullAddress;// http://webapi.weather.com.cn/data/?areaid=""&type=""&date=""&appid=""&key=".urlencode($key);

	private Handler handler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		handler = new MyHandler();
		init();
		// AsyncHttpRequest();
	}

	private void init() {
		button1 = (Button) findViewById(R.id.button1);
		tv1 = (TextView) findViewById(R.id.textview1);
		button1.setOnClickListener(this);
		button2 = (Button) findViewById(R.id.button2);
		tv2 = (TextView) findViewById(R.id.textview2);
		button2.setOnClickListener(this);
		button3 = (Button) findViewById(R.id.button3);
		tv3 = (TextView) findViewById(R.id.textview3);
		button3.setOnClickListener(this);
	}

	// httprequest
	class RequestObserveWeather implements Runnable {

		@Override
		public void run() {
			try {
				System.out.println("RequestObserveWeather");
				// WebUrlManager.CARSEVER_GetCarsServlet
				URL url = new URL(fullAddress);
				System.out.println("fullAddress----------->" + fullAddress);
				HttpURLConnection conn = (HttpURLConnection) url
						.openConnection();

				BufferedInputStream bis = new BufferedInputStream(
						conn.getInputStream());
				// �����ȡ

				byte[] data = new byte[1024];
				int len = 0;
				String bufferString = "";
				while ((len = bis.read(data)) != -1) {
					bufferString += new String(data, 0, len);
				}
				System.out.println(bufferString);
				String jsondata=bufferString.trim();
				System.out.println("jsondata=="+jsondata);
				
				/*HttpClient httpClient=new DefaultHttpClient();
				HttpGet httpGet=new HttpGet(fullAddress);
				HttpResponse httpResponse =httpClient.execute(httpGet);
				//if (httpResponse.getStatusLine().getStatusCode()==200) {
					HttpEntity entity=httpResponse.getEntity();
					String response=EntityUtils.toString(entity,"utf-8");
					System.out.println("response=="+ response);
				*/
				
				
				//JSONObject obj = new JSONObject(bufferString.trim()).getJSONObject("f1");
					
				  //JSONObject  forecast=new JSONObject(response);
				JSONObject forecast=new JSONObject (jsondata);
				JSONObject forecast_test=forecast.getJSONObject("c");
				String city_name=forecast_test.getString("c5"); //������������
				System.out.println("city_name"+" IS "+ city_name);	
				
				JSONObject forecast_3d=forecast.getJSONObject("f");//
				JSONArray forecast_f1_array=forecast_3d.getJSONArray("f1");//����ǰ����
				JSONObject forecast_f1fc=forecast_f1_array.getJSONObject(0);//������һ��
				
				//ArrayList<String> fcd=new ArrayList<String>();
				//fcd.add(forecast_f1fc.getString("fc"));//��һ�����
				//fcd.add(forecast_f1fc.getString("fd"));//��һ������
				String[] fcd=new String[2];
				fcd[0]=forecast_f1fc.getString("fc");
				fcd[1]=forecast_f1fc.getString("fd");
				System.out.println("��һ�����"+" IS "+ fcd[0]);	
				System.out.println("��һ������"+" IS "+ fcd[1]);	
				
//=====================================================================
				/*
				String[] dayWeatherPhenomenonNumber = new String[forecast.length()];
				String[] nightWeatherPhenomenonNumber = new String[forecast.length()];
				String[] maxTemperature = new String[forecast.length()];
				String[] minTemperature = new String[forecast.length()];
				String[] dayWindDirectionNumber = new String[forecast.length()];
				String[] nightWindDirectionNumber = new String[forecast.length()];
				String[] dayWindForce = new String[forecast.length()];
				String[] nightWindForce = new String[forecast.length()];
				String[] dayWindDirection = new String[forecast.length()];
				String[] nightWindDirection = new String[forecast.length()];
				String[] dayWeatherPhenomenon = new String[forecast.length()];
				String[] nightWeatherPhenomenon = new String[forecast.length()];

				String forecast3dMsg[] = new String[forecast.length()];
				
				for (int i = 0; i < forecast.length(); i++) {
					JSONObject forecastObj = forecast.getJSONObject(i);
					
					dayWeatherPhenomenonNumber[i] = forecastObj.getString("fa");// ��������������
					nightWeatherPhenomenonNumber[i] = forecastObj.getString("fb");// ҹ������������
					System.out.println("day������----��"+ dayWeatherPhenomenonNumber[i]);
					dayWeatherPhenomenon[i] = Util.getWeatherPhenomenon(dayWeatherPhenomenonNumber[i]);
					System.out.println("night������----��"+ nightWeatherPhenomenonNumber[i]);
					nightWeatherPhenomenon[i] = Util.getWeatherPhenomenon(nightWeatherPhenomenonNumber[i]);// ������������
					maxTemperature[i] = forecastObj.getString("fc");
					minTemperature[i] = forecastObj.getString("fd");// ����
					dayWindDirectionNumber[i] = forecastObj.getString("fe");
					nightWindDirectionNumber[i] = forecastObj.getString("ff");// ������
					dayWindDirection[i] = Util.getWindDirection(dayWindDirectionNumber[i]);
					nightWindDirection[i] = Util.getWindDirection(nightWindDirectionNumber[i]);// �������
					dayWindForce[i] = forecastObj.getString("fg");
					nightWindForce[i] = forecastObj.getString("fh");// ����

					forecast3dMsg[i] = "\n��" + (i + 1) + "�죺  " + "���죺"
								+ dayWeatherPhenomenon[i] + " "
								+ dayWindDirection[i] + dayWindForce[i] + "��\n"
								+ " 		ҹ��" + nightWeatherPhenomenon[i] + " "
								+ nightWindDirection[i] + nightWindForce[i]
								+ "��  ���£�" + minTemperature[i] + "~"
								+ maxTemperature[i] + "��";
					System.out.println(forecast3dMsg);

				}
				*/
				
//=====================================================================
				/*String currentTemperature = obj.getString("l1");
				String currentHumidity = obj.getString("l2");
				String currentWindForce = obj.getString("l3");
				String currentWindDirectionNumber = obj.getString("l4");
				System.out.println("currentWindDirectionNumber===>"
						+ currentWindDirectionNumber);
				String currentWindDirection = Util
						.getWindDirection(currentWindDirectionNumber);
				String releaseTime = obj.getString("l7");

				String weatherMsg = "��ǰ�¶ȣ� " + minTemperature[1] + ".c" + "\n"
						+ "��ǰʪ�ȣ� " + currentHumidity + "%\n" + "��ǰ������"
						+ currentWindForce + "��\n" + "��ǰ���� "
						+ currentWindDirection + "\n" + "����ʱ�䣺 " + releaseTime;
				System.out.println(weatherMsg);
				*/				
				
				
				Message msg = handler.obtainMessage();
				msg.what = 1;
				msg.obj =city_name+"�����¶�Ϊ"+fcd[0]+",�����¶�Ϊ"+fcd[1];
				handler.sendMessage(msg);
				
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// httprequest
	class RequestForecast3dWeather implements Runnable {
		@Override
		public void run() {
			try {
				System.out.println("RequestForecast3dWeather");
				System.out.println("fullAddress----------->" + fullAddress);
				URL url = new URL(fullAddress);
				HttpURLConnection conn = (HttpURLConnection) url
						.openConnection();

				BufferedInputStream bis = new BufferedInputStream(
						conn.getInputStream());
				// �����ȡ
				byte[] data = new byte[1024];
				int len = 0;
				String bufferString = "";
				while ((len = bis.read(data)) != -1) {
					bufferString += new String(data, 0, len);
				}
				JSONObject area = new JSONObject(bufferString.trim())
						.getJSONObject("c");
				System.out.println("area_json=============>" + area);
				JSONObject forecast3d = new JSONObject(bufferString.trim())
						.getJSONObject("f");
				System.out
						.println("forecast3d_json=============>" + forecast3d);

				String areaId = area.getString("c1");
				String cityName = area.getString("c3");
				String contry = area.getString("c9");
				System.out.println("����ID�� " + areaId + "  ���У� " + cityName
						+ "  ���ң� " + contry);

				String releaseTime = forecast3d.getString("f0");
				System.out.println("����ʱ�䣺 " + releaseTime);

				String forcastMsgHead = "����ID�� " + areaId + "  ���У� " + cityName
						+ "  ���ң� " + contry + "\n" + "����ʱ�䣺 " + releaseTime;

				JSONArray forecast = forecast3d.getJSONArray("f1");
				String[] dayWeatherPhenomenonNumber = new String[forecast
						.length()];
				String[] nightWeatherPhenomenonNumber = new String[forecast
						.length()];
				String[] maxTemperature = new String[forecast.length()];
				String[] minTemperature = new String[forecast.length()];
				String[] dayWindDirectionNumber = new String[forecast.length()];
				String[] nightWindDirectionNumber = new String[forecast
						.length()];
				String[] dayWindForce = new String[forecast.length()];
				String[] nightWindForce = new String[forecast.length()];
				String[] dayWindDirection = new String[forecast.length()];
				String[] nightWindDirection = new String[forecast.length()];
				String[] dayWeatherPhenomenon = new String[forecast.length()];
				String[] nightWeatherPhenomenon = new String[forecast.length()];

				String forecast3dMsg[] = new String[forecast.length()];

				for (int i = 0; i < forecast.length(); i++) {
					JSONObject forecastObj = (JSONObject) forecast.opt(i);
					dayWeatherPhenomenonNumber[i] = forecastObj.getString("fa");// ��������������
					nightWeatherPhenomenonNumber[i] = forecastObj
							.getString("fb");// ҹ������������
					System.out.println("day������----��"+ dayWeatherPhenomenonNumber[i]);
					dayWeatherPhenomenon[i] = Util
							.getWeatherPhenomenon(dayWeatherPhenomenonNumber[i]);
					System.out.println("night������----��"+ nightWeatherPhenomenonNumber[i]);
					nightWeatherPhenomenon[i] = Util
							.getWeatherPhenomenon(nightWeatherPhenomenonNumber[i]);// ������������
					maxTemperature[i] = forecastObj.getString("fc");
					minTemperature[i] = forecastObj.getString("fd");// ����
					dayWindDirectionNumber[i] = forecastObj.getString("fe");
					nightWindDirectionNumber[i] = forecastObj.getString("ff");// ������
					dayWindDirection[i] = Util
							.getWindDirection(dayWindDirectionNumber[i]);
					nightWindDirection[i] = Util
							.getWindDirection(nightWindDirectionNumber[i]);// �������
					dayWindForce[i] = forecastObj.getString("fg");
					nightWindForce[i] = forecastObj.getString("fh");// ����

					forecast3dMsg[i] = "\n��" + (i + 1) + "�죺  " + "���죺"
							+ dayWeatherPhenomenon[i] + " "
							+ dayWindDirection[i] + dayWindForce[i] + "��\n"
							+ " 		ҹ��" + nightWeatherPhenomenon[i] + " "
							+ nightWindDirection[i] + nightWindForce[i]
							+ "��  ���£�" + minTemperature[i] + "~"
							+ maxTemperature[i] + "��";
					System.out.println(forecast3dMsg);

				}

				Message msg = handler.obtainMessage();
				msg.what = 2;
				String forecastMsg;
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < forecast.length(); i++) {
					sb.append(forecast3dMsg[i]);
				}
				forecastMsg = sb.toString();

				msg.obj = forcastMsgHead + forecastMsg;
				handler.sendMessage(msg);

			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// httprequest
	class RequestWeatherIndex implements Runnable {

		@Override 
		public void run() {
			// TODO Auto-generated method stub
			try {
				System.out.println("RequestWeatherIndex");
				System.out.println("fullAddress----------->" + fullAddress);
				URL url = new URL(fullAddress);
				HttpURLConnection conn = (HttpURLConnection) url
						.openConnection();

				BufferedInputStream bis = new BufferedInputStream(
						conn.getInputStream());
				// �����ȡ
				byte[] data = new byte[1024];
				int len = 0;
				String bufferString = "";
				while ((len = bis.read(data)) != -1) {
					bufferString += new String(data, 0, len);
				}
				JSONArray indexArray = new JSONObject(bufferString.trim())
						.getJSONArray("i");
				System.out.println("indexArray=============>" + indexArray);

				String[] indexLevel = new String[indexArray.length()];
				String[] indexContent = new String[indexArray.length()];
				String[] indexMsg = new String[indexArray.length()];
				for (int i = 0; i < indexArray.length(); i++) {
					JSONObject indexObj = (JSONObject) indexArray.opt(i);

					indexLevel[i] = indexObj.getString("i4");
					indexContent[i] = indexObj.getString("i5");

					indexMsg[i] = "ָ������: " + indexLevel[i] + "\nָ������: "
							+ indexContent[i];
					System.out.println(indexMsg[i]);
				}

				Message msg = handler.obtainMessage();
				msg.what = 3;
				msg.obj = indexMsg[0];
				handler.sendMessage(msg);

			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * ȡ�������ַ
	 * 
	 * @param weatherAPIAddress
	 * @param areaId
	 * @param weatherType
	 * @param date
	 * @return fullAddress
	 * @throws UnsupportedEncodingException
	 * @throws GeneralSecurityException
	 */
	private String getFullAddress(String areaId, String weatherType, String date)
			throws UnsupportedEncodingException, GeneralSecurityException {

		String baseString = weatherAPIAddress + "areaid=" + areaId + "&type="
				+ weatherType + "&date=" + date + "&appid=" + APPID;
		String keyString = PRIVATE_Key;
		String token = Util.computeSignature(baseString, keyString);
		String fullAddress = weatherAPIAddress + "areaid=" + areaId + "&type="
				+ weatherType + "&date=" + date + "&appid=" + AVAILABLE_APPID
				+ "&key=" + token;
		return fullAddress;
	}

	/**
	 * ������ʱ��
	 * 
	 * @return String
	 */
	private String getDate() {
		Calendar c = Calendar.getInstance();

		String year = String.valueOf(c.get(Calendar.YEAR));
		String month = String.valueOf(c.get(Calendar.MONTH) + 1);
		String day = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
		String hour = String.valueOf(c.get(Calendar.HOUR_OF_DAY));
		String mins = String.valueOf(c.get(Calendar.MINUTE));
		// String secs = String.valueOf(c.get(Calendar.SECOND));

		StringBuffer sbBuffer = new StringBuffer();
		sbBuffer.append(year + month + day + hour + mins);

		return sbBuffer.toString();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {

		// ����http get
		switch (v.getId()) {
		case R.id.button1:
			try {
				fullAddress = this.getFullAddress("101010100", "forecast_f",
						getDate());
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (GeneralSecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			new Thread(new RequestObserveWeather()).start();
			break;
		case R.id.button2:
			try {
				fullAddress = this.getFullAddress("101010100", "index",
						getDate());
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (GeneralSecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			new Thread(new RequestWeatherIndex()).start();
			break;
		case R.id.button3:
			try {
				fullAddress = this.getFullAddress("101010100", "forecast3d",
						getDate());
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (GeneralSecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			new Thread(new RequestForecast3dWeather()).start();
			break;
		}

	}

	class MyHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				tv1.setText((String) msg.obj);
				break;
			case 2:
				tv3.setText((String) msg.obj);
				break;
			case 3:
				tv2.setText((String) msg.obj);
				break;
			default:
				return;
			}

		}
	}
}
