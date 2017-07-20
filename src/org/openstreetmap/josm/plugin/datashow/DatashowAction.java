package org.openstreetmap.josm.plugin.datashow;

import static org.openstreetmap.josm.tools.I18n.tr;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import org.xml.sax.SAXException;

import org.openstreetmap.josm.Main;
import org.openstreetmap.josm.actions.mapmode.MapMode;
import org.openstreetmap.josm.gui.MapFrame;
import org.openstreetmap.josm.gui.PleaseWaitRunnable;
import org.openstreetmap.josm.gui.progress.ProgressMonitor;
import org.openstreetmap.josm.gui.util.GuiHelper;
import org.openstreetmap.josm.tools.ImageProvider;
import org.openstreetmap.josm.tools.Shortcut;



public class DatashowAction extends MapMode implements MouseListener{
	private static final long serialVersionUID = 1L;

    protected boolean cancel;
    

    DatashowAction(MapFrame mapFrame) {
        super(tr("Datashow info"), "info-sml", tr("Datashow info."),
                Shortcut.registerShortcut("tools:Datashow", tr("Tool: {0}", tr("Datashow info")), KeyEvent.VK_X, Shortcut.ALT_CTRL),
                mapFrame, getCursor());
    }
    @Override
    public void enterMode() {
        if (!isEnabled()) {
            return;
        }
        super.enterMode();
        Main.map.mapView.setCursor(getCursor());
        Main.map.mapView.addMouseListener(this);
    }

    @Override
    public void exitMode() {
        super.exitMode();
        Main.map.mapView.removeMouseListener(this);
    }

    private static Cursor getCursor() {
        return ImageProvider.getCursor("crosshair", "info-sml");
    }
    public void cancel() {
        cancel = true;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (!Main.map.mapView.isActiveLayerDrawable()) {
            return;
        }
        requestFocusInMapView();
        updateKeyModifiers(e);
        if (e.getButton() == MouseEvent.BUTTON1) {
        	showData(e.getPoint());
        }
    }

    @Override
    protected void updateKeyModifiers(MouseEvent e) {
        ctrl = (e.getModifiers() & ActionEvent.CTRL_MASK) != 0;
        alt = (e.getModifiers() & (ActionEvent.ALT_MASK | InputEvent.ALT_GRAPH_MASK)) != 0;
        shift = (e.getModifiers() & ActionEvent.SHIFT_MASK) != 0;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }
    
    protected void showData(Point clickPoint) {
        cancel = false;

        try {
            PleaseWaitRunnable infoTask = new PleaseWaitRunnable(tr("Connecting server")) {
                @Override
                protected void realRun() throws SAXException {
                	showData(progressMonitor.createSubTaskMonitor(ProgressMonitor.ALL_TICKS, true));
                }

                @Override
                protected void finish() {
                }

                @Override
                protected void afterFinish() {
                    if (SimulateData.simulateData().length > 0) {
                        // Show result
                    	MyCanvas canvas = new MyCanvas();
                        JScrollPane scrollPane = new JScrollPane(canvas);
                        Object[] objects = {scrollPane};
                        final ImageIcon icon = new ImageIcon(getClass().getResource("/images/dialogs/info-sml.png"));
                        JOptionPane.showMessageDialog(
                                null, objects, tr("Datashow"), JOptionPane.PLAIN_MESSAGE, icon);
                    }
                }

                @Override
                protected void cancel() {
                	DatashowAction.this.cancel();
                }
            };
            new Thread(infoTask).start();
        } catch (Exception e) {
            Main.error(e);
        }
    }

    private void showData(ProgressMonitor progressMonitor) {

        progressMonitor.beginTask(null, 3);
        try {
        	SimulateData.setMapping();

        } finally {
            progressMonitor.finishTask();
        }
        progressMonitor.invalidate();
        if (SimulateData.simulateData().length == 0) {
            GuiHelper.runInEDTAndWait(
                    () -> SimulateData.showNotification(tr("Data not available."), "warning"));
            return;
        }
    }
}
