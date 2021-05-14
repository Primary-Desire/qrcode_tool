package util;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class QRCodeUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(QRCodeUtils.class);

    public static File captureScreen() {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        String suffix = "png";
        String name = CommonUtils.randomString(8);
        String fileName = String.format("%s.%s", name, suffix);
        File file = new File(fileName);
        try {
            Rectangle rectangle = new Rectangle(screen);
            BufferedImage screenShot = (new Robot()).createScreenCapture(rectangle);
            ImageIO.write(screenShot, suffix, file);
        } catch (Exception e) {
            LOGGER.error("捕获异常: {}", e.getMessage());
        }
        return file;
    }

    public static String decoding(File file) {
        String content = "";
        try {
            BufferedImage image = ImageIO.read(file);
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            Binarizer binarizer = new HybridBinarizer(source);
            BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
            Map<DecodeHintType, Object> hintTypeObjectMap = new HashMap<>();
            hintTypeObjectMap.put(DecodeHintType.CHARACTER_SET, "UTF-8");
            Result result = new MultiFormatReader().decode(binaryBitmap, hintTypeObjectMap);
            content = result.getText();
        } catch (Exception e) {
            LOGGER.error("捕获异常: {}", e.getMessage());
        }
        return content;
    }

    public static void openBrowse(String url) {
        Desktop desktop = Desktop.getDesktop();
        try {
            if (Desktop.isDesktopSupported() && desktop.isSupported(Desktop.Action.BROWSE)) {
                URI uri = new URI(url);
                desktop.browse(uri);
            }
        } catch (Exception e) {
            LOGGER.error("捕获异常: {}", e.getMessage());
        }
    }
}
