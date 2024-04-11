package com.library.core.util;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class DateTimeUitl {
	private static final String zoneIdVN = "Asia/Ho_Chi_Minh";


	public static LocalDateTime getLocalDateTimeNowZoneVN() {
		return LocalDateTime.now(ZoneId.of(zoneIdVN));
	}
}
