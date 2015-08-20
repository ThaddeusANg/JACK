import java.io.*;

import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import org.json.*;


public class JACK{
	HashMap<String, ArrayList<String>> PathMap = new HashMap<String, ArrayList<String>>();
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

		for(String key:keys){

			path+="."+key;
			if(content.get(key).getClass().equals(JSONArray.class))
			{
				pathHash(content.getJSONArray(key), path);
			}else if(content.get(key).getClass().equals(JSONObject.class)){
				pathHash(content.getJSONObject(key),path);
			}else{
				System.out.println("--output below--");
				System.out.println("Node Value of "+key+": "+content.get(key));
				System.out.println(path);
				
				if(PathMap.containsKey(key)){
					PathMap.get(key).add(path);
					for(String row:PathMap.get(key)){
						System.out.println(row);
					}
				}else{
				ArrayList<String> push = new ArrayList<String>();
				push.add(path);
				PathMap.put(key, push);
				System.out.println("--end output--");
				}
			}
		}
	}
	
	public void pathHash(JSONArray content, String path){
		String incremPath = path;
		for(int key=0;key<content.length();key++){
			path+="["+key+"]";
			if(content.get(key).getClass().equals(JSONArray.class))
			{
				pathHash(content.getJSONArray(key), path);
			}else if(content.get(key).getClass().equals(JSONObject.class)){
				pathHash(content.getJSONObject(key), path);
			}else{
				System.out.println("--output below--");
				System.out.println("Node Value of "+key+": "+content.get(key));
				System.out.println(path);
				
				if(PathMap.containsKey(key)){
					PathMap.get(key).add(path);
				}else{
				ArrayList<String> push = new ArrayList<String>();
				push.add(path);
				PathMap.put(key+"", push);
				}
				System.out.println("--end output--");
			}
			path=incremPath;
		}
	}
	public ArrayList<String> nodePath(String key){
		return (ArrayList<String>) PathMap.get(key);
	}
}

   