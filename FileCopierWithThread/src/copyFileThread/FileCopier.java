package copyFileThread;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

public class FileCopier implements Runnable
{
	File sourceFolder;
	List<File> filesName;
	File destinationFolder;


	public FileCopier( List<File> files,File sourceFolder, File destinationFolder) {
		super();
		this.sourceFolder = sourceFolder;
		this.filesName = files;
		this.destinationFolder = destinationFolder;
	}
	
	
	public void run() 
	{
		try {
			for(int i = 0; i<filesName.size(); i++)
			{
				File fileName =  filesName.get(i); // taking out the filename one by one from list.
//				File source = new File(sourceFolder);
//				File destination = new File(destinationFolder);
				System.out.println(fileName + "  "+Thread.currentThread().getName());
				System.out.println(destinationFolder.toPath() +"\\"+ fileName.getName());

			Files.copy( fileName.toPath(), Path.of( destinationFolder.toPath()+ "\\" + fileName.getName()), StandardCopyOption.REPLACE_EXISTING);
			
			}
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
 
}
