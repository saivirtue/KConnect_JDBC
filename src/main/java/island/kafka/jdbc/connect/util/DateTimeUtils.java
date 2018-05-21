/*
 *  Copyright 2016 Confluent Inc.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package island.kafka.jdbc.connect.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class DateTimeUtils
{

	// public static final TimeZone UTC = TimeZone.getTimeZone("UTC");
	public static final TimeZone LOCAL_TIMEZONE = TimeZone.getDefault();

	public static final ThreadLocal<Calendar> LOCALE_CALENDAR = new ThreadLocal<Calendar>()
	{
		@Override
		protected Calendar initialValue()
		{
			// return new GregorianCalendar(TimeZone.getTimeZone("UTC"));
			return new GregorianCalendar(TimeZone.getDefault());
		}
	};

	private static final ThreadLocal<SimpleDateFormat> LOCALE_DATE_FORMAT = new ThreadLocal<SimpleDateFormat>()
	{
		protected SimpleDateFormat initialValue()
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			// sdf.setTimeZone(UTC);
			sdf.setTimeZone(LOCAL_TIMEZONE);
			return sdf;
		}
	};

	private static final ThreadLocal<SimpleDateFormat> LOCALE_TIME_FORMAT = new ThreadLocal<SimpleDateFormat>()
	{
		protected SimpleDateFormat initialValue()
		{
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
			// sdf.setTimeZone(UTC);
			sdf.setTimeZone(LOCAL_TIMEZONE);
			return sdf;
		}
	};

	private static final ThreadLocal<SimpleDateFormat> LOCALE_TIMESTAMP_FORMAT = new ThreadLocal<SimpleDateFormat>()
	{
		protected SimpleDateFormat initialValue()
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			// sdf.setTimeZone(UTC);
			sdf.setTimeZone(LOCAL_TIMEZONE);
			return sdf;
		}
	};

	public static String formatUtcDate(Date date)
	{
		return LOCALE_DATE_FORMAT.get().format(date);
	}

	public static String formatUtcTime(Date date)
	{
		return LOCALE_TIME_FORMAT.get().format(date);
	}

	public static String formatUtcTimestamp(Date date)
	{
		return LOCALE_TIMESTAMP_FORMAT.get().format(date);
	}

}
