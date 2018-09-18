import java.util.*;
import java.io.*;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonHandler {

	public static Collection<Object> data() {
		ArrayList<Object> data = new ArrayList<Object>();
		JSONParser parser = new JSONParser();
		JSONObject rawJSON;

		try {

			rawJSON = (JSONObject) parser.parse(new FileReader("C:\\searchData.json"));
			Object[] keys = rawJSON.keySet().toArray();
			for (Object key : keys) {
				JSONObject json = (JSONObject) rawJSON.get(key);
				data.add(new Object[] { json });
			}
		}

		catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return data;
	}
}
