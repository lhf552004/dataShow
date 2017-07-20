package testCanvas;

import java.util.HashMap;
import java.util.Map;

public class SimulateData {
	private static Map<String,String> map = new HashMap<String,String>();
	
	public static Map<String,String> setMapping(){
		map.clear();
		map.put("1", "green");
		map.put("2","yellow");
		map.put("3","red");
		
		return map;
	}
	
	public static String getColor(int key) {
		
		String theKey = Integer.toString(key);
		return map.get(theKey);
	}
	
	public static int[][] simulateData(){
		int [][]a={
	    		{1,3,2},
	    		{2,3,1},
	    		{3,1,1}
	    	};
		return a;
	}
}
