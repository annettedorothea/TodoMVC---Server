	/********************************************************************************
	 * generated by de.acegen 1.5.4
	 ********************************************************************************/
	
	
	
	
	package de.acegen;
	
	import java.util.concurrent.ConcurrentHashMap;
	import java.util.concurrent.ConcurrentMap;
	
	import java.time.LocalDateTime;
	
	public class SquishyDataProvider {
		
		private static ConcurrentMap<String, LocalDateTime> systemTimeMap = new ConcurrentHashMap<>();

		private static ConcurrentMap<String, ConcurrentMap<String, String>> valueMap = new ConcurrentHashMap<>();
	
		public static void putSystemTime(String uuid, LocalDateTime systemTime) {
			if (uuid != null && systemTime != null) {
				systemTimeMap.put(uuid, systemTime);
			}
		}
		
		public static void put(String uuid, String key, String value) {
			if (uuid != null && value != null) {
				ConcurrentMap<String, String> mapForUuid = valueMap.get(uuid);
				if (mapForUuid == null) {
					mapForUuid = new ConcurrentHashMap<String, String>();
					valueMap.put(uuid, mapForUuid);
				}
				mapForUuid.put(key, value);
			}
		}
		
		public static LocalDateTime consumeSystemTime(String uuid) {
			if (uuid != null) {
				LocalDateTime value = systemTimeMap.get(uuid);
				if (value != null) {
					systemTimeMap.remove(uuid);
				}
				return value;
			} else {
				return null;
			}
		}
	
		public static String consumeValue(String uuid, String key) {
			if (uuid != null && key != null) {
				ConcurrentMap<String, String> mapForUuid = valueMap.get(uuid);
				if (mapForUuid == null) {
					return null;
				}
				String value = mapForUuid.get(key);
				mapForUuid.remove(key);
				if (mapForUuid.size() == 0) {
					valueMap.remove(uuid);
				}
				return value;
			} else {
				return null;
			}
		}
		
	}

	
	
	/******* S.D.G. *******/
	
	
	
