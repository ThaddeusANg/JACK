import java.io.*;

import java.net.*;
import java.util.HashMap;
import java.util.Set;

import org.json.*;


public class JACK{
	HashMap PathMap = new HashMap();
	public JACK(){

	}
	
	public JSONObject APICall(String url) throws IOException{
		JSONObject jsonOutput;
		String output="";
        URL httpReq = new URL(url);
        URLConnection con = httpReq.openConnection();
        BufferedReader in = new BufferedReader(
                                new InputStreamReader(
                                con.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null)
        	{output+=inputLine;}
        in.close();
        jsonOutput = new JSONObject(output);
        return jsonOutput;
	}
	
	public void pathHash(JSONObject content, String path){
		Set<String> keys =content.keySet(); 
		System.out.println(content.keySet());
		for(String key:keys){
			System.out.println(content.get(key).getClass());
			System.out.println(content.get(key));
			path+="."+key;
			if(content.get(key).getClass().equals(JSONArray.class))
			{
				pathHash(content.getJSONArray(key), path);
			}else if(content.get(key).getClass().equals(JSONObject.class)){
				pathHash(content.getJSONObject(key),path);
			}else{
				System.out.println("Node Value of "+key+": "+content.get(key));
				System.out.println(path);
				PathMap.put(key, path);
			}
		}
	}
	
	public void pathHash(JSONArray content, String path){
		for(int key=0;key<content.length();key++){
			path+="["+key+"]";
			System.out.println(content.get(key).getClass());
			System.out.println(content.get(key));
			if(content.get(key).getClass().equals(JSONArray.class))
			{
				pathHash(content.getJSONArray(key), path);
			}else if(content.get(key).getClass().equals(JSONObject.class)){
				pathHash(content.getJSONObject(key), path);
			}else{
				System.out.println("Node Value of "+key+": "+content.get(key));
				System.out.println(path);
				PathMap.put(key, path);
			}
		}
	}
	public String nodePath(String key){
		return (String) PathMap.get(key);
	}
}

   