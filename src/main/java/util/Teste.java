package util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Teste {

	
	public void funcina() {
		
		
		 Path source = Paths.get(this.getClass().getResource("/").getPath());
	        Path newFolder = Paths.get(source.toAbsolutePath() + "/newFolder/");
	        try {
				Files.createDirectories(newFolder);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	public void funcinaok() { 
		
		String caminho = ClassLoader.getSystemResource("src/main/webapp/resources/repositorioArquivo/tmp").getPath();
		System.out.println(caminho);
		
	}
	public void getpath() {
		
	}
}
