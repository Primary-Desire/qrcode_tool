package listener;

import util.QRCodeUtils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ButtonListener implements ActionListener {

    public static final String BUTTON = "Scanning";

    @Override
    public void actionPerformed(ActionEvent e) {
        if (BUTTON.equals(e.getActionCommand())) {
            File screenShot = QRCodeUtils.captureScreen();
            try {
                String content = QRCodeUtils.decoding(screenShot);
                QRCodeUtils.openBrowse(content);
            } finally {
                screenShot.delete();
            }
        }
    }
}
