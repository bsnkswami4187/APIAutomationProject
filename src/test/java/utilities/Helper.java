package utilities;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Helper 
{
	public static String getCurrentDate()
	{
		String date = null;
		try 
		{	
			   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-YYYY_HH-mm-ss");  
			   LocalDateTime now = LocalDateTime.now();  
			   date = dtf.format(now);  		   			  
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		 return date;
	}
	public static void deleteFolder(String folderPath) 
	{
		File folder = new File(folderPath);
		if (folder.exists() && folder.isDirectory()) 
		{
			for (File file : folder.listFiles()) 
			{
				if (file.isDirectory()) 
				{
					deleteFolder(file.getAbsolutePath());
				} 
				else 
				{
					file.delete();
				}
			}
			folder.delete();
			System.out.println("Folder deleted successfully: " + folderPath);
		}		
		if(!folder.exists() || folder.listFiles().length == 0) 
		{
			folder.mkdir();
			System.out.println("Folder does not exist or is not a empty: " + folderPath+ ", created a new folder.");
		}
	}
//	public static void main(String[] args) 
//	{
//		deleteFolder("./logs");
//	}
}
