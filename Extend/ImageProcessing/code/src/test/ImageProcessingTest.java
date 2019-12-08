import java.io.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import static org.junit.Assert.*;
import org.junit.Test;

import imagereader.IImageIO;
import imagereader.IImageProcessor;

/*
    implement of ImageProcessingTest
*/
@SuppressWarnings("all")
public class ImageProcessingTest 
{
    // src image (1.bmp) path
    private String loadPath1 = "bmptest/1.bmp";

    // image save path
    private String savePath1 = "res/1_save";

    // standard red image
    private String standardPath1 = "bmptest/goal/1_red_goal.bmp";

    // standard green image
    private String standardPath2 = "bmptest/goal/1_green_goal.bmp";

    // standard blue image
    private String standardPath3 = "bmptest/goal/1_blue_goal.bmp";

    // standard gray image
    private String standardPath4 = "bmptest/goal/1_gray_goal.bmp";

    // src image (2.bmp) path
    private String loadPath2 = "bmptest/2.bmp";

    // image save path
    private String savePath2 = "res/2_save";

    // standard red image
    private String standardPath5 = "bmptest/goal/2_red_goal.bmp";

    // standard green image
    private String standardPath6 = "bmptest/goal/2_green_goal.bmp";

    // standard blue image
    private String standardPath7 = "bmptest/goal/2_blue_goal.bmp";

    // standard gray image
    private String standardPath8 = "bmptest/goal/2_gray_goal.bmp";

    @Test
    // test the implement of ImplementImageIO
    public void TestIO()
    {
        // check 1.bmp
        checkIO(loadPath1, savePath1);
        // check 2.bmp
        checkIO(loadPath2, savePath2);
    }

    @Test
    // test the implement of IImageProcessor
    public void TestChanel()
    {
        // check 1.bmp
        checkChanel(loadPath1, standardPath1, 0);
        checkChanel(loadPath1, standardPath2, 1);
        checkChanel(loadPath1, standardPath3, 2);
        checkChanel(loadPath1, standardPath4, 3);
        // check 2.bmp
        checkChanel(loadPath2, standardPath5, 0);
        checkChanel(loadPath2, standardPath6, 1);
        checkChanel(loadPath2, standardPath7, 2);
        checkChanel(loadPath2, standardPath8, 3);
    }

    // check IO
    private void checkIO(String path1, String path2)
    {
        try
        {
            // get src image
            ImplementImageIO imageioer = new ImplementImageIO();
            Image srcImg = imageioer.myRead(path1);
            Image saveImg = imageioer.myWrite(srcImg, path2);

            // get standard image
            FileInputStream inputStream = new FileInputStream(path1);
            BufferedImage standardImg = ImageIO.read(inputStream);

            BufferedImage testImg = new BufferedImage(srcImg.getWidth(null), srcImg.getHeight(null), BufferedImage.TYPE_INT_RGB);
            testImg.getGraphics().drawImage(srcImg, 0, 0, null);
            BufferedImage testImg2 = new BufferedImage(saveImg.getWidth(null), saveImg.getHeight(null), BufferedImage.TYPE_INT_RGB);
            testImg2.getGraphics().drawImage(saveImg, 0, 0, null);
            checkImage(testImg, standardImg);
            checkImage(testImg2, testImg);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    // check chanel
    private void checkChanel(String path1, String path2, int type)
    {
        try
        {
            // get src image
            IImageIO imageioer = new ImplementImageIO();
            IImageProcessor processor = new ImplementImageProcessor();
            Image srcImg = imageioer.myRead(path1);
            // get standard image
            FileInputStream inputStream = new FileInputStream(path2);
            BufferedImage standardImg = ImageIO.read(inputStream);
            Image tempImg = null;
            switch(type)
            {
                case 0:
                    tempImg = processor.showChanelR(srcImg);
                    break;
                
                case 1:
                    tempImg = processor.showChanelG(srcImg);
                    break;

                case 2:
                    tempImg = processor.showChanelB(srcImg);
                    break;
                
                default:
                    tempImg = processor.showGray(srcImg);
                    break;
            }
            BufferedImage testImg = new BufferedImage(tempImg.getWidth(null), tempImg.getHeight(null), BufferedImage.TYPE_INT_RGB);
            testImg.getGraphics().drawImage(tempImg, 0, 0, null);
            checkImage(testImg, standardImg);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    // check image
    private void checkImage(BufferedImage testImg, BufferedImage standardImg)
    {
        // check the image size
        assertEquals(testImg.getHeight(), standardImg.getHeight());
        assertEquals(testImg.getWidth(), standardImg.getWidth());
        // check the image RGB
        for (int i = 0; i < testImg.getWidth(); ++ i)
        {
            for (int j = 0; j < testImg.getHeight(); ++ j)
            {
                assertEquals(testImg.getRGB(i, j), standardImg.getRGB(i, j));
            }
        }
    }
}
