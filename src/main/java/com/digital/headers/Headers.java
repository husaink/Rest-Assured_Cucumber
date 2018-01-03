package com.digital.headers;

import java.util.HashMap;
import java.util.Map;

public class Headers {
	
	public static Map<String,String> setHeaders()
	{
		Map<String,String> headers = new HashMap<String, String>();
		
		headers.put("app_key", "72d4f9f2fa04b2b1cf80b29d320e6726");
		headers.put("app_id", "0b714692");
		
		return headers;
	}

}
