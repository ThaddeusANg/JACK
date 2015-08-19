import java.io.*;

import java.net.*;
import java.util.ArrayList;
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
					System.out.println(PathMap.get(key).getClass());
					if(PathMap.get(key).getClass().equals(ArrayList.class)){
						ArrayList<String> paths = (ArrayList<String>) PathMap.get(key);
						paths.add(path);
						PathMap.put(key, paths);
						ArrayList<String> temp = (ArrayList<String>)PathMap.get(key);
						System.out.println("Size "+temp.size());
						for(String row:temp){
							System.out.println(key+" array in kv pair "+row);
						}
					}else{
					ArrayList<String> paths = new ArrayList<String>();
					paths.add((String) PathMap.get(key));
					paths.add(path);
					PathMap.put(key, paths);
					for(String row:(ArrayList<String>)PathMap.get(key)){
						System.out.println(key+" array in kv pair "+row);
					}
					}
				}
				PathMap.put(key, path);
				System.out.println("--end output--");
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
					if(PathMap.get(key).getClass().equals(ArrayList.class)){
						ArrayList<String> paths = (ArrayList<String>) PathMap.get(key);
						paths.add(path);
						PathMap.put(key, paths);
					}else{
					ArrayList<String> paths = new ArrayList<String>();
					paths.add((String) PathMap.get(key));
					paths.add(path);
					PathMap.put(key, paths);
					}
				}
				PathMap.put(key, path);
				System.out.println("--end output--");
			}
			path=incremPath;
		}
	}
	public String nodePath(String key){
		return (String) PathMap.get(key);
	}
}

   