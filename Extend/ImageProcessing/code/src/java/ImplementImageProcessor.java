import java.io.*;
import java.awt.*;
import java.awt.image.*;
import imagereader.IImageProcessor;

/*
    implement of IImageProcessor
*/
@SuppressWarnings("all")
public class ImplementImageProcessor implements IImageProcessor
{
    /*
        set coefficient of R G B
        I = 0.299 * R + 0.587 * G + 0.114 * B
    */
    private final double REDCOEFF = 0.299;
    private final double GREENCOEFF = 0.587;
    private final double BLUECOEFF = 0.114;

    /*
        choice:
        0 => get red
        1 => get green
        2 => get blue
        default => get gray
    */
    private Image changeColor(Image srcImg, int choice)
    {
        BufferedImage image = new BufferedImage(srcImg.getWidth(null), srcImg.getHeight(null), BufferedImage.TYPE_INT_RGB);
        image.getGraphics().drawImage(srcImg, 0, 0, null);
        for (int i = 0; i < image.getWidth(); ++ i)
        {
            for (int j = 0; j < image.getHeight(); ++ j)
            {
                Color color = new Color(image.getRGB(i, j));
                switch(choice)
                {
                    case 0:
                        image.setRGB(i, j, new Color(color.getRed(), 0, 0).getRGB());
                        break;

                    case 1:
                        image.setRGB(i, j, new Color(0, color.getGreen(), 0).getRGB());
                        break;

                    case 2:
                        image.setRGB(i, j, new Color(0, 0, color.getBlue()).getRGB());
                        break;
                    
                    default:
                        image.setRGB(i, j, new Color(getGray(color), getGray(color), getGray(color)).getRGB());
                        break;
                }
            }
        }
        return image;
    }

    /*
        get gray
        I = 0.299 * R + 0.587 * G + 0.114 * B
    */
    private int getGray(Color color)
    {
        return (int)(color.getRed() * REDCOEFF + color.getGreen() * GREENCOEFF + color.getBlue() * BLUECOEFF);
    }

    /*
        show red
    */
    public Image showChanelR(Image srcImg)
    {
        return changeColor(srcImg, 0);
    }

    /*
        show green
    */
    public Image showChanelG(Image srcImg)
    {
        return changeColor(srcImg, 1);
    }

    /*
        show blue
    */
    public Image showChanelB(Image srcImg)
    {
        return changeColor(srcImg, 2);
    }

    /*
        show gray
    */
    public Image showGray(Image srcImg)
    {
        return changeColor(srcImg, 3);
    }

}
