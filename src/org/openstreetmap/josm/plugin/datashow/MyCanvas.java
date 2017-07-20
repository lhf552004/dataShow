package org.openstreetmap.josm.plugin.datashow;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

public class MyCanvas extends JComponent {
	
	public void paint(Graphics g) {
		int[][] arr = SimulateData.simulateData();
		int offsetX = 5;
		int offsetY = 5;
		int widthX = 10;
		int heightY= 6;
		for(int i = 0; i<3;i++) {
			for(int j =0; j<3;j++) {
				int theKey = arr[i][j];
				String theColor = SimulateData.getColor(theKey);
				g.fillRect(offsetX,offsetY,widthX,heightY);
				if(theColor == "green") {
					g.setColor(Color.green);
					
				}else if(theColor == "yellow") {
					g.setColor(Color.yellow);					
				}else if(theColor == "red") {
					g.setColor(Color.red);
				}
				
				offsetX += (widthX +2);
				if(offsetX > (widthX+2)*3) {
					offsetX = 5;
					offsetY += (heightY+2);
				}
				
			}
		}
	}
}
