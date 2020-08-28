import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class deleteFiles {

	
	// This class will delete the files from ePlus and ePlusAd folders in the same directory
	
	public static void deleteAll(String mainName) 
    { 
		mainName = mainName.replace(".idf", "");
		// Find Current Directory
		File jarDir = new File(ClassLoader.getSystemClassLoader().getResource(".").getPath());
		String pathTemp = jarDir.getAbsolutePath().replace("%20", " ");
		Path path = Paths.get(pathTemp, "ePlus\\" + mainName);
		Path pathAd = Paths.get(pathTemp, "ePlusAd\\" + mainName);
    	
    	// File to Delete
    	String[] fileTypes = {"audit", "bnd", "csv", "dxf", "edd", "eio", "end", "epmdet", "epmidf", "err", "eso", "mdd", "mtd", "rdd", "rvaudit", "shd", "mtr", "dbg"};
    	ArrayList<String> filesToDelete = new ArrayList<String>();
    	
    	filesToDelete.add(pathTemp  +"\\eplusout.err");
    	filesToDelete.add(pathTemp  +"\\eplusout.end");
    	filesToDelete.add(pathTemp  + "\\" + mainName +".epJSON");
    	filesToDelete.add(pathTemp  + "\\sqlite.err");
    	
    	for(int i = 0; i < fileTypes.length; i++) {
    		filesToDelete.add(path + "." + fileTypes[i]);
    		filesToDelete.add(pathAd + "." + fileTypes[i]);
    		filesToDelete.add(pathTemp + "\\eplusout." + fileTypes[i]);
    		
    	}
    	
    	filesToDelete.add(pathTemp  +"\\ePlus\\eplusout.err");
    	filesToDelete.add(pathTemp  +"\\ePlus\\eplusout.end");
    	filesToDelete.add(path +"Sqlite.err");
    	filesToDelete.add(path +"Table.htm");
    	filesToDelete.add(pathTemp + "\\ePlus\\"+ "audit.out");
    	filesToDelete.add(pathTemp + "\\ePlus\\"+ "out.idf");
    	filesToDelete.add(pathTemp + "\\ePlus\\"+ "readvars.audit");
    	filesToDelete.add(pathTemp + "\\ePlus\\"+ "simulation.log");
    	filesToDelete.add(pathTemp + "\\ePlus\\"+ "socket.cfg");
    	filesToDelete.add(pathTemp + "\\ePlus\\"+ "utilSocket.log");

    	filesToDelete.add(pathTemp  +"\\ePlusAd\\eplusout.err");
    	filesToDelete.add(pathTemp  +"\\ePlusAd\\eplusout.end");
    	filesToDelete.add(pathAd +"Sqlite.err");
    	filesToDelete.add(pathAd +"Table.htm");
    	filesToDelete.add(pathTemp + "\\ePlusAd\\"+ "audit.out");
    	filesToDelete.add(pathTemp + "\\ePlusAd\\"+ "out.idf");
    	filesToDelete.add(pathTemp + "\\ePlusAd\\"+ "readvars.audit");
    	filesToDelete.add(pathTemp + "\\ePlusAd\\"+ "simulation.log");
    	filesToDelete.add(pathTemp + "\\ePlusAd\\"+ "socket.cfg");
    	filesToDelete.add(pathTemp + "\\ePlusAd\\"+ "utilSocket.log");

    	// Delete files
    	for( int j = 0; j < filesToDelete.size(); j++) {
    		try
            { 
                Files.delete(Paths.get(filesToDelete.get(j))); 
                System.out.println("Deletion successful.");
            } 
            catch(NoSuchFileException e) 
            { 
                System.out.println("No such file/directory exists"); 
            } 
            catch(DirectoryNotEmptyException e) 
            { 
                System.out.println("Directory is not empty."); 
            } 
            catch(IOException e) 
            { 
                System.out.println("Invalid permissions."); 
            } 
              
             
    	}
    
    } 

}
