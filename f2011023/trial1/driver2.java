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

public class driver2 
{
	driver2() throws IOException
	{
    JobConf conf = new JobConf(trial1.Driver1.class);
    conf.setJobName("lengthLCS");
    ///////////////////////////////////////////////
    conf.addResource(new Path("path-to-your-core-site.xml file"));
    conf.addResource(new Path("path-to-your-hdfs-site.xml file"));
     //////////////////////////////////////////////////////////////////
    conf.setOutputKeyClass(IntWritable.class);
    conf.setOutputValueClass(Text.class);

    conf.setMapperClass(Mapper2.class);
    //conf.setCombinerClass(Reduce1.class);
    conf.setReducerClass(Reducer2.class);

    conf.setInputFormat(TextInputFormat.class);
    conf.setOutputFormat(TextOutputFormat.class);
    File file = new File("FirstReduce/part-00000");
	  FileInputFormat.setInputPaths(conf, file.getAbsolutePath());
	  Path reduceTwo=new Path("Out");
	  
    FileOutputFormat.setOutputPath(conf, reduceTwo);
	  JobClient.runJob(conf);
  }

}
