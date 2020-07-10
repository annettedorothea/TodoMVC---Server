/* 
 * Copyright (c) 2020, Annette Pohl, Koblenz, Germany
 *
 * Permission to use, copy, modify, and/or distribute this software for any
 * purpose with or without fee is hereby granted, provided that the above
 * copyright notice and this permission notice appear in all copies.

 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
 * WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR
 * ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 * WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
 * ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF
 * OR IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 *
 * generated with de.acegen 0.9.6
 *
 */




	package de.acegen;
	
	import java.util.concurrent.ConcurrentHashMap;
	import java.util.concurrent.ConcurrentMap;
	
	import java.time.LocalDateTime;
	
	public class NotReplayableDataProvider {
		
		private static ConcurrentMap<String, LocalDateTime> systemTimeMap = new ConcurrentHashMap<>();

		private static ConcurrentMap<String, ConcurrentMap<String, Object>> valueMap = new ConcurrentHashMap<>();
	
		public static LocalDateTime consumeSystemTime(String uuid) {
			LocalDateTime value = systemTimeMap.get(uuid);
			if (value != null) {
				systemTimeMap.remove(uuid);
			}
			return value;
		}
	
		public static void putSystemTime(String uuid, LocalDateTime systemTime) {
			systemTimeMap.put(uuid, systemTime);
		}
		
		public static void put(String uuid, String key, Object value) {
			ConcurrentMap<String, Object> mapForUuid = valueMap.get(uuid);
			if (mapForUuid == null) {
				mapForUuid = new ConcurrentHashMap<String, Object>();
				valueMap.put(uuid, mapForUuid);
			}
			mapForUuid.put(key, value);
		}
		
		public static Object consumeValue(String uuid, String key) {
			ConcurrentMap<String, Object> mapForUuid = valueMap.get(uuid);
			if (mapForUuid == null) {
				return null;
			}
			Object value = mapForUuid.get(key);
			mapForUuid.remove(key);
			if (mapForUuid.size() == 0) {
				valueMap.remove(uuid);
			}
			return value;
		}
		
	}					
	


/******* S.D.G. *******/



