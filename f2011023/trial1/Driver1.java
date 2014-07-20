package trial1;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.hadoop.mapred.TextOutputFormat;

public class Driver1
{
	static int c = 0;
	Driver1(String pathOfInput) throws IOException
	{
    JobConf conf = new JobConf(trial1.Driver1.class);
    conf.setJobName("lengthLCS");
    ///////////////////////////////////////////////
    conf.addResource(new Path("path-to-your-core-site.xml file"));
    conf.addResource(new Path("path-to-your-hdfs-site.xml file"));
     //////////////////////////////////////////////////////////////////
    conf.setOutputKeyClass(IntWritable.class);
    conf.setOutputValueClass(Text.class);

    conf.setMapperClass(Mapper1.class);
    //conf.setCombinerClass(Reduce1.class);
    conf.setReducerClass(Reducer1.class);

    conf.setInputFormat(TextInputFormat.class);
    conf.setOutputFormat(TextOutputFormat.class);

      java.nio.file.Path dir= Paths.get(pathOfInput);
	  //File pairFile = new File(dir.getName()+"/pairs.txt");
    File pairFile = new File("/home/mansi/files.txt");
	  PrintWriter fw=new PrintWriter(pairFile); 
	  //System.out.println("here");
	  try (DirectoryStream<java.nio.file.Path> stream = Files.newDirectoryStream((java.nio.file.Path) dir)) 
	  {		  
		  int len = 0;
		  ArrayList<java.nio.file.Path> store = new ArrayList<java.nio.file.Path>();
		  for(java.nio.file.Path file: stream) 
		  { 
			  store.add(file.toAbsolutePath()); 
			  len++;
		  }
		  //System.out.println("************"+len+"**********");
		  for(int i=0;i<len;i++) 
		  {
			  
			  for(int j=i+1;j<len;j++)
				  fw.println(store.get(i).toString()+" "+store.get(j).toString()); 
		  }
		  stream.close();
		  
	} catch (IOException | DirectoryIteratorException x) { System.err.println(x);} 
	  fw.close();
	  
	  FileInputFormat.setInputPaths(conf, pairFile.getAbsolutePath());
	  Path reducePath=new Path("FirstReduce");
	  FileOutputFormat.setOutputPath(conf, reducePath );
    //System.out.println("hi");
	  JobClient.runJob(conf);
	  //driver2(reduce1.getAbsolutePath(), args[1]);
  }

}
