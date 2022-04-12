package util;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Method;

public class Resize {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			BufferedImage buff = ImageIO.read(new File("C:\\Users\\Paulo\\Documents\\GetDoc\\festa.jpg"));
			File f = new File("");
			
		
			Dimension newMaxSize = new Dimension(1000, 1500);
			BufferedImage resizedImg = Scalr.resize(buff, Method.QUALITY,
			                                        newMaxSize.width, newMaxSize.height);
		
			 ImageIO.write(resizedImg, "jpg", new File("C:\\Users\\Paulo\\Documents\\GetDoc\\festaok.jpg"));
		
		
		
		
		
		
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
