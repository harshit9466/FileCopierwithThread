package copyFileThread;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

public class FileCopierSynchronized implements Runnable
{
	File sourceFolder;
	List<File> filesName;
	File destinationFolder;
	Object lock;


	public FileCopierSynchronized( List<File> files,File sourceFolder, File destinationFolder, Object lock) {
		super();
		this.sourceFolder = sourceFolder;
		this.filesName = files;
		this.destinationFolder = destinationFolder;
		this.lock = lock;
	}
	
	public void copyFile(File fileName) throws InterruptedException
	{
		synchronized (lock) 
		{
		try {
				System.out.println(fileName + "  "+Thread.currentThread().getName());
				System.out.println(destinationFolder.toPath() +"\\"+ fileName.getName());
				Thread.sleep(1000);

			Files.copy( fileName.toPath(), Path.of( destinationFolder.toPath()+ "\\" + fileName.getName()), StandardCopyOption.REPLACE_EXISTING);
			
		} catch (IOException e) {e.printStackTrace();}
		
		lock.notify();
		lock.wait();
		
		}
	}
	
	
	public void run()
	{
		for(int i = 0; i<filesName.size(); i++)
		{
		  try {
			copyFile(filesName.get(i));
		} catch (InterruptedException e) {e.printStackTrace();}
		
		}
	}
/*
	
	public void run() 
	{
		try {
			for(int i = 0; i<filesName.size(); i++)
			{
				File fileName =  filesName.get(i);
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
 */
}
