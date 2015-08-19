import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import org.json.JSONObject;


public class JACKTest {

	public static void main(String[] args) throws IOException {
		JACK apiCall = new JACK();
		JSONObject result = apiCall.APICall(
				"https://maps.googleapis.com/maps/api/geocode/json?address=1600+Amphitheatre+Parkway,+Mountain+View,+CA&key=AIzaSyBuQKvsdnCxXXpvgAKtMQhyAckl4A3FQA4"
	    		   );
		apiCall.pathHash(result,"Node Path: ");
	}

}