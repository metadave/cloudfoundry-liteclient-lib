/*
 * Copyright 2009-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.cloudfoundry.client.lib.util;

//import org.cloudfoundry.client.lib.domain.CloudResource;
//import org.codehaus.jackson.map.ObjectMapper;
//import org.codehaus.jackson.type.TypeReference;
//import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Some JSON helper utilities used by the Cloud Foundry Java client.
 *
 * @author Thomas Risberg
 *
 */
public class JsonUtil {

	protected static final Logger logger = Logger.getAnonymousLogger();

//	private final static ObjectMapper mapper = new ObjectMapper();

//	public static final MediaType JSON_MEDIA_TYPE = new MediaType(
//			MediaType.APPLICATION_JSON.getType(),
//			MediaType.APPLICATION_JSON.getSubtype(),
//			Charset.forName("UTF-8"));

	public static Map<String, Object> convertJsonToMap(String json) throws JSONException {
		Map<String, Object> retMap = new HashMap<String, Object>();
		if (json != null) {
//			try {
//				retMap = mapper.readValue(json, new TypeReference<Map<String, Object>>() {});
//			} catch (IOException e) {
//				logger.warn("Error while reading Java Map from JSON response: " + json, e);
//			}
			retMap = convertJsonToMap(new JSONObject(json));
		}
		return retMap;
	}
	
	public static Map<String, Object> convertJsonToMap(JSONObject json) throws JSONException {
		Map<String, Object> retMap = new HashMap<String, Object>();
		JSONObject jo = new JSONObject(json.toString());
		for (String key : JsonUtil.keys(jo)) {
			if (jo.get(key.toString()) instanceof JSONObject) {
				retMap.put(key.toString(), convertJsonToMap(jo.getJSONObject(key.toString())));
			}
			else {
				retMap.put(key.toString(), jo.getString(key.toString()));
			}
		}
		return retMap;
	}
	
//	public static List<String> convertJsonToList(String json) {
//		List<String> retList = new ArrayList<String>();
//		if (json != null) {
//			try {
//				retList = mapper.readValue(json, new TypeReference<List<String>>() {});
//			} catch (IOException e) {
//				logger.warn("Error while reading Java List from JSON response: " + json, e);
//			}
//		}
//		return retList;
//	}

//	public static List<CloudResource> convertJsonToCloudResourceList(String json) {
//		List<CloudResource> retList = new ArrayList<CloudResource>();
//		if (json != null) {
//			try {
//				retList = mapper.readValue(json, new TypeReference<List<CloudResource>>() {});
//			} catch (IOException e) {
//				logger.warn("Error while reading Java List from JSON response: " + json, e);
//			}
//		}
//		return retList;
//	}

//	public static String convertToJson(Object value) {
//		if (mapper.canSerialize(value.getClass())) {
//			try {
//				return mapper.writeValueAsString(value);
//			} catch (IOException e) {
//				logger.warn("Error while serializing " + value + " to JSON", e);
//				return null;
//			}
//		}
//		else {
//			throw new IllegalArgumentException("Value of type " + value.getClass().getName() +
//					" can not be serialized to JSON.");
//		}
//	}
	
	public static JSONObject convertMapToJson(Map<String, Object> map) throws JSONException {
		JSONObject res = new JSONObject();
		for (String key : map.keySet()) {
			res.put(key, map.get(key));
		}
		return res;
	}
	
	public static List<String> keys(JSONObject j) {
		List<String> list = new ArrayList<String>();
		@SuppressWarnings("unchecked")
		Iterator<Object> i = j.keys();
		while (i.hasNext()) {
			list.add(i.next().toString());
		}
		return list;
	}

}
