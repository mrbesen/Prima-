package prima.plus.main;

import java.io.*;

public class filereader {

	public String[] read(String filepath, int lines) throws IOException
	{
		FileReader f = new FileReader(filepath);
		BufferedReader b = new BufferedReader(f);
		
		String[] content = new String[lines];

	    for(int i = 0; i<lines;i++)
	    {
	       	content[i] = b.readLine().toString();
	    }
	    f.close();
		return content;
	}
	
	public void write_conf(String dir)
	{
		dir = dir + "conf.txt";
	}
}
