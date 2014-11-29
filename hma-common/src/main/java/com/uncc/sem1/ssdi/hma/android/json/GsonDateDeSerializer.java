package com.uncc.sem1.ssdi.hma.android.json;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class GsonDateDeSerializer implements JsonDeserializer<Date> {
	private List<SimpleDateFormat> formats = new ArrayList<SimpleDateFormat>();

	public GsonDateDeSerializer() {
		formats.add(new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz"));
		formats.add(new SimpleDateFormat("yyyy-MM-dd"));
		formats.add(new SimpleDateFormat("HH:mm:ss"));
	}

	@Override
	public Date deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext ctx) throws JsonParseException {
		try {
			String j = json.getAsJsonPrimitive().getAsString();
			return parseDate(j);
		} catch (ParseException e) {
			throw new JsonParseException(e.getMessage(), e);
		}
	}

	private Date parseDate(String dateString) throws ParseException {
		Date parsedDate = null;
		if (dateString != null && dateString.trim().length() > 0) {
			for (SimpleDateFormat format : formats) {
				try {
					parsedDate = format.parse(dateString);
					if (parsedDate != null){
						return parsedDate;
					}
				} catch (ParseException pe) {
					System.out.println(pe.getErrorOffset() + "\nNow tring different format");
				}
			}
			return parsedDate;
		} else {
			return null;
		}
	}

}
