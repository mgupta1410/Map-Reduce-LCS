package trial1;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class Mapper2 extends MapReduceBase implements Mapper<LongWritable, Text, IntWritable, Text>  
{
	IntWritable one = new IntWritable(1);
    Text word = new Text();
    String s1, s2;
	public void map(LongWritable key, Text value, OutputCollector<IntWritable,Text> output, Reporter reporter) throws IOException 
	{		
			String store[] = value.toString().split("\t");
		    String[] line = store[1].split(",");
	    	int lcs = trial1.lcs.SeqLCS(line[0],line[1]);
	    	StringBuffer sbr = trial1.lcs.sub;
	    
	        //int lcs = trial1.lcs.SeqLCS(line[0],line[1]);
	   	    word.set(sbr.toString()); //will get sorted by lcs.*/
	    	output.collect(new IntWritable(1),word);
	}
}

