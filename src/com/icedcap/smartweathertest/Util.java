package com.icedcap.smartweathertest;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class Util {

	/**
	 * ����ǩ��
	 * 
	 * @param baseString
	 *            ����
	 * @param keyString
	 *            ˽Կ
	 * @return ��Կ
	 * @throws GeneralSecurityException
	 * @throws UnsupportedEncodingException
	 */
	public static String computeSignature(String baseString, String keyString)
			throws GeneralSecurityException, UnsupportedEncodingException {

		SecretKey secretKey = null;

		byte[] keyBytes = keyString.getBytes();
		secretKey = new SecretKeySpec(keyBytes, "HmacSHA1");

		Mac mac = Mac.getInstance("HmacSHA1");

		mac.init(secretKey);

		byte[] text = baseString.getBytes();

		return new String(Base64.encodeBase64(mac.doFinal(text))).trim();
	}

	/**
	 * ͨ�������ŵõ�����
	 * 
	 * @param WeatherPhenomenonNumber
	 * @return
	 */
	public static String getWeatherPhenomenon(String WeatherPhenomenonNumber) {
		String weatherPhenomenon;
		if (WeatherPhenomenonNumber != null
				&& !WeatherPhenomenonNumber.equals("")) {
			System.out
					.println("Integer.parseInt(WeatherPhenomenonNumber.trim())----"
							+ Integer.parseInt(WeatherPhenomenonNumber.trim()));
			switch (Integer.parseInt(WeatherPhenomenonNumber.trim())) {
			case 0:
				weatherPhenomenon = "��";
				return weatherPhenomenon;
			case 1:
				weatherPhenomenon = "����";
				return weatherPhenomenon;
			case 2:
				weatherPhenomenon = "��";
				return weatherPhenomenon;
			case 3:
				weatherPhenomenon = "����";
				return weatherPhenomenon;
			case 4:
				weatherPhenomenon = "������";
				return weatherPhenomenon;
			case 5:
				weatherPhenomenon = "��������б���";
				return weatherPhenomenon;
			case 6:
				weatherPhenomenon = "���ѩ";
				return weatherPhenomenon;
			case 7:
				weatherPhenomenon = "С��";
				return weatherPhenomenon;
			case 8:
				weatherPhenomenon = "����";
				return weatherPhenomenon;
			case 9:
				weatherPhenomenon = "����";
				return weatherPhenomenon;
			case 10:
				weatherPhenomenon = "����";
				return weatherPhenomenon;
			case 11:
				weatherPhenomenon = "����";
				return weatherPhenomenon;
			case 12:
				weatherPhenomenon = "�ش���";
				return weatherPhenomenon;
			case 13:
				weatherPhenomenon = "��ѩ";
				return weatherPhenomenon;
			case 14:
				weatherPhenomenon = "Сѩ";
				return weatherPhenomenon;
			case 15:
				weatherPhenomenon = "��ѩ";
				return weatherPhenomenon;
			case 16:
				weatherPhenomenon = "��ѩ";
				return weatherPhenomenon;
			case 17:
				weatherPhenomenon = "��ѩ";
				return weatherPhenomenon;
			case 18:
				weatherPhenomenon = "��";
				return weatherPhenomenon;
			case 19:
				weatherPhenomenon = "����";
				return weatherPhenomenon;
			case 20:
				weatherPhenomenon = "ɳ����";
				return weatherPhenomenon;
			case 21:
				weatherPhenomenon = "С������";
				return weatherPhenomenon;
			case 22:
				weatherPhenomenon = "�е�����";
				return weatherPhenomenon;
			case 23:
				weatherPhenomenon = "�󵽱���";
				return weatherPhenomenon;
			case 24:
				weatherPhenomenon = "���굽����";
				return weatherPhenomenon;
			case 25:
				weatherPhenomenon = "���굽�ش���";
				return weatherPhenomenon;
			case 26:
				weatherPhenomenon = "С����ѩ";
				return weatherPhenomenon;
			case 27:
				weatherPhenomenon = "�е���ѩ";
				return weatherPhenomenon;
			case 28:
				weatherPhenomenon = "�󵽱�ѩ";
				return weatherPhenomenon;
			case 29:
				weatherPhenomenon = "����";
				return weatherPhenomenon;
			case 30:
				weatherPhenomenon = "��ɳ";
				return weatherPhenomenon;
			case 31:
				weatherPhenomenon = "ǿɳ����";
				return weatherPhenomenon;
			case 53:
				weatherPhenomenon = "��";
				return weatherPhenomenon;
			case 99:
				weatherPhenomenon = "��";
				return weatherPhenomenon;
			default:
				weatherPhenomenon = "date error";
				return weatherPhenomenon;
			}
		} else {
			return "";
		}
	}

	/**
	 * ͨ�������ŵõ�����
	 * 
	 * @param windDirectionNumber
	 * @return
	 */
	public static String getWindDirection(String windDirectionNumber) {
		String windDirection;
		if (windDirectionNumber != null && !windDirectionNumber.equals("")) {
			// System.out.println("Integer.parseInt(windDirectionNumber)----"+Integer.parseInt(windDirectionNumber));
			switch (Integer.parseInt(windDirectionNumber)) {
			case 0:
				windDirection = "�޳�������";
				return windDirection;
			case 1:
				windDirection = "������";
				return windDirection;
			case 2:
				windDirection = "����";
				return windDirection;
			case 3:
				windDirection = "���Ϸ�";
				return windDirection;
			case 4:
				windDirection = "�Ϸ�";
				return windDirection;
			case 5:
				windDirection = "���Ϸ�";
				return windDirection;
			case 6:
				windDirection = "����";
				return windDirection;
			case 7:
				windDirection = "������";
				return windDirection;
			case 8:
				windDirection = "����";
				return windDirection;
			case 9:
				windDirection = "��ת��";
				return windDirection;
			default:
				windDirection = "date error";
				return windDirection;
			}
		} else {
			return "";
		}
	}

}
