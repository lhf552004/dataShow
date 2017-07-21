package org.openstreetmap.josm.plugins.datashow;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import org.openstreetmap.josm.gui.Notification;

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
	 public static void showNotification(String message, String type) {
	        Notification note = new Notification(message);

	        if ("info".equals(type))
	            note.setIcon(JOptionPane.INFORMATION_MESSAGE);
	        else if ("warning".equals(type))
	            note.setIcon(JOptionPane.WARNING_MESSAGE);
	        else if ("error".equals(type))
	            note.setIcon(JOptionPane.ERROR_MESSAGE);
	        else
	            note.setIcon(JOptionPane.PLAIN_MESSAGE);

	        note.setDuration(Notification.TIME_SHORT);
	        note.show();
	    }
}
