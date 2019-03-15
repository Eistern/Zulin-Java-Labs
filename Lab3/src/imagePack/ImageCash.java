package imagePack;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ImageCash {
    private Map<Integer, ImageIcon> bufferedImages;
    private Map<Integer, String> imageIDs;
    private ImageIcon defaultImage;
    private static ImageCash ourInstance;

    public static ImageCash getInstance() throws IOException {
        ImageCash localInstance = ourInstance;
        if (ourInstance == null)
            synchronized (ImageCash.class) {
                if (ourInstance == null) {
                    localInstance = new ImageCash();
                    ourInstance = localInstance;
                }
            }
        return localInstance;
    }

    private ImageCash() throws IOException {
        defaultImage = new ImageIcon(ImageIO.read(ImageCash.class.getResourceAsStream("default.png")));
        bufferedImages = new HashMap<>();
        imageIDs = new HashMap<>();
        Properties idProperties = new Properties();
        idProperties.load(ImageCash.class.getResourceAsStream("id.properties"));
        idProperties.forEach((id, fileName) ->
            imageIDs.put(Integer.parseInt(id.toString()), fileName.toString()));
    }

    public ImageIcon getImage(int imgID) throws IOException {
        if (bufferedImages.containsKey(imgID))
            return bufferedImages.get(imgID);
        if (!imageIDs.containsKey(imgID))
            return defaultImage;

        bufferedImages.put(imgID, new ImageIcon(ImageIO.read(ImageCash.class.getResourceAsStream(imageIDs.get(imgID)))));
        return bufferedImages.get(imgID);
    }
}
