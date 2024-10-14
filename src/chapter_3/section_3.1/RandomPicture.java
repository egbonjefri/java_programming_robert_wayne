
import lib.Picture;

import java.awt.Color;

public class RandomPicture {
    



    public static void main(String[] args){
        int red = (int) Math.floor(Math.random()*256);
        int green = (int) Math.floor(Math.random()*256);
        int blue = (int) Math.floor(Math.random()*256);
        Color color = new Color(red, green, blue);
        int width = 256;
        int height = 256;
        Picture picture1 = new Picture(width, height);
        for (int col = 0; col < width; col++) {
            for (int row = 0; row < height; row++) {
                picture1.set(col, row, color);
            }
        }
        picture1.show();
    }
}
