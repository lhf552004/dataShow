package org.openstreetmap.josm.plugin.datashow;



import org.openstreetmap.josm.Main;
import org.openstreetmap.josm.gui.MainMenu;
import org.openstreetmap.josm.plugins.Plugin;
import org.openstreetmap.josm.plugins.PluginInformation;


public class DataShowPlugin extends Plugin {


	public DataShowPlugin(PluginInformation info) {
		super(info);
		// TODO Auto-generated constructor stub
		MainMenu.add(Main.main.menu.moreToolsMenu, new DatashowAction(Main.map));
	}

}
