import listener.ButtonListener;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        JFrame window = new JFrame("Scan QR Code");
        window.setSize(200, 100);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        window.add(panel);

        placeComponents(panel);

        window.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JButton scanQRCode = new JButton(ButtonListener.BUTTON);
        scanQRCode.setBounds(50, 20, 100, 40);
        scanQRCode.addActionListener(new ButtonListener());
        panel.add(scanQRCode);

    }

}
