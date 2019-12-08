import java.io.*;
import java.awt.*;
import java.awt.image.*;
import java.util.logging.*;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;

import imagereader.IImageIO;

/*
    implement of ImplementImageIO
*/
@SuppressWarnings("all")
public class ImplementImageIO implements IImageIO 
{
    // set length of bitmap header and message
    private final int LENGTH = 54;
    // set logger
    private Logger log = Logger.getLogger("logger");

    /*
        get message from bitmap message
    */
    private int getMessage(byte[] message, int start, int length)
    {
        int result = 0;
        for (int i = 0; i < length; ++ i)
        {
            result = result | ((message[start + i] & 0xff) << 8 * i);
        }
        return result;
    }

    /*
        implement of myRead
    */
    @Override
    public Image myRead(String filePath) throws IOException
    {
        Image resImg = null;
        try
        {
            File file = new File(filePath);
            FileImageInputStream istream = new FileImageInputStream(file);
            byte message[] = new byte[LENGTH];
            istream.read(message, 0, LENGTH);
            // get width, height and size from bitmap
            int width = getMessage(message, 18, 4);
            int height = getMessage(message, 22, 4);
            int size = getMessage(message, 34, 4);
            //int emptyNum = size / height - 3 * width;
            int emptyNum = (size / 3) / height - width;
            byte store[] = new byte[size];
            istream.read(store, 0, size);
            int temp[] = new int[height * width];
            int index = 0;
            // from bottom to top
            for (int i = height - 1; i >= 0; -- i)
            {
                // from left to right
                for (int j = 0; j < width; ++ j)
                {
                    temp[i * width + j] = (int)((store[index] & 0xff) | ((store[index + 1] & 0xff) << 8) | ((store[index + 2] & 0xff) << 16) | (0xff << 24));
                    index += 3;
                }
                // ignore the empty pixel
                index += emptyNum * 3;
            }
            resImg = Toolkit.getDefaultToolkit().createImage((ImageProducer) new MemoryImageSource(width, height, temp, 0, width));
            istream.close();
        } 
        catch (IOException exception) {
            log.setLevel(Level.WARNING);
            log.info("[Error!]");
        }
        return resImg;
    }

    /*
        implement of myWrite
    */
    @Override
    public Image myWrite(Image image, String filePath) throws IOException
    {
        try
        {
            File file = new File(filePath + ".bmp");
            // create bufferImg
            BufferedImage bufferImg = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
            // put image into bufferImg
            bufferImg.getGraphics().drawImage(image, 0, 0, null);
            // write bufferImg into file
            ImageIO.write(bufferImg, "bmp", file);
        } 
        catch (Exception exception) {
            log.setLevel(Level.WARNING);
            log.info("[Error!]");
        }
        return image;
    }
}
