package org.zerock.mallapi.common.utils;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.springframework.format.Formatter;

public class LocalDateFormatter implements Formatter<LocalDate> {

	/***
	 * @기능 LocalDate 를 yyyy-MM-dd 문자열로 변환
	 * @param object
	 * @param locale
	 * @return String
	 */
	@Override
	public String print(LocalDate object, Locale locale) {
		return DateTimeFormatter.ofPattern("yyyy-MM-dd").format(object);
	}

	/***
	 * @기능 yyyy-MM-dd 문자열을 LocalDate 로 parse
	 * @param text
	 * @param locale
	 * @return LocalDate
	 */
	@Override
	public LocalDate parse(String text, Locale locale) throws ParseException {
		return LocalDate.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
	

}
