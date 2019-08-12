package com.rover.utilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public final class FileUtility {
	private FileUtility() {		
	}
	
	public static List<String> readFile(String fileName) throws IOException{
		//String fileNameWithPath = FileSystems.getDefault().getPath(".").toAbsolutePath() + ".\\resources\\" + fileName;
		String fileNameWithPath = FileSystems.getDefault().getPath(".").toAbsolutePath() + File.separator +  fileName;
		
		/**
		StringBuilder builder = new StringBuilder("src");
	    builder.append(File.separator);
	    builder.append("main");
	    builder.append(File.separator);
	    builder.append("resources");
	    builder.append(File.separator);
	    builder.append(fileName);
		**/
	    ///src/main/resources/plateau.txt
	    // src/main/resources/plateau.txt
	    
		return Files.readAllLines(Paths.get(fileNameWithPath));
	    
	    //return Files.readAllLines(Paths.get(builder.toString()));
	}
}
