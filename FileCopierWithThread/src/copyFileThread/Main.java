package copyFileThread;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) 
	{
		// saving source and destination folder
		File sourceFolder = new File("C:\\Users\\zimot\\OneDrive\\Desktop\\copyFile Source");
		File destinationFolder = new File("C:\\Users\\zimot\\OneDrive\\Desktop\\copyFile Destination");
		
		//getting the files name from source with listFiles() method and saving in files arraylist;
		List<File> filesName = new ArrayList<>(); 
		File fileArray[] = sourceFolder.listFiles();
		for(File file : fileArray)
		{
			if(file.isFile())
			{
				filesName.add(file);
			}
		}

		//dividing the number files in the folder into as many threads are there here are 2;
		int filesPerThread = filesName.size()/2;

		List<File> filesName1 = filesName.subList(0, filesPerThread);
		List<File> filesName2 = filesName.subList(filesPerThread, filesName.size());

		/*int startIndex = 0;
		int endIndex = filesName.size();
		*/
/*
        // this class copies file with all threads simultaneously.
		FileCopier fileobj1 = new FileCopier(filesName1, sourceFolder, destinationFolder);
		FileCopier fileobj2 = new FileCopier(filesName2, sourceFolder, destinationFolder);
*/
		//this class copies file with different threads but only one thread can copy at a time.
		Object lock = new Object();
		FileCopierSynchronized fileobj1 = new FileCopierSynchronized(filesName1, sourceFolder, destinationFolder,lock);
		FileCopierSynchronized fileobj2 = new FileCopierSynchronized(filesName2, sourceFolder, destinationFolder,lock);
		
		Thread fileCopier1 = new Thread(fileobj1);
		Thread fileCopier2 = new Thread(fileobj2);
		
		fileCopier1.start();
		fileCopier2.start();
		
	}

}
