package commons;

import java.io.InputStream;

import org.json.JSONObject;
import org.json.JSONTokener;

public class EndUserJson {
	InputStream datais;
	JSONObject jsonObject;

	public void getEndUserData() throws Exception {
		try {
			String dataFileName = "Data/DataEndUser.json";
			datais = getClass().getClassLoader().getResourceAsStream(dataFileName);
			JSONTokener tokener = new JSONTokener(datais);
			jsonObject = new JSONObject(tokener);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (datais != null) {
				datais.close();
			}
		}
	}

}
