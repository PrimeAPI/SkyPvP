package de.primeapi.primeplugins.skypvp.util;

import org.json.JSONObject;

import java.util.Base64;

/**
 * @author Lukas S. PrimeAPI
 * created on 20.08.2022
 * crated for SkyPvP
 */
public class SkyUtils {

	public static boolean isSkullTexture(String input) {
		try {
			String base10 = new String(Base64.getDecoder().decode(input));
			new JSONObject(base10);
			return true;
		}catch (Exception ex){
			return false;
		}
	}

}
