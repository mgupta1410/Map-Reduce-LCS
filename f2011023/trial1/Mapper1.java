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

public class Mapper1 extends MapReduceBase implements Mapper<LongWritable, Text, IntWritable, Text>  
{
	IntWritable one = new IntWritable(1);
    Text word = new Text();
    String s1, s2;
	public void map(LongWritable key, Text value, OutputCollector<IntWritable,Text> output, Reporter reporter) throws IOException 
	{
		    String[] line = value.toString().split(" ");
	        
	    	int lcs = trial1.lcs.SeqLCS(line[0],line[1]);
	   	    word.set(line[0]+","+line[1]); //will get sorted by lcs.
	    	output.collect(new IntWritable(-1*lcs),word);
	}
}

class lcs
{	
	 public static StringBuffer sub= new StringBuffer("");
	 public static int SeqLCS(String path1, String path2)throws IOException
	 {
		 	java.nio.file.Path p1= Paths.get(path1);
	   	    java.nio.file.Path p2= Paths.get(path2);
		    StringBuffer file1,file2;
		    file1= readFile(p1.toString(), Charset.defaultCharset());
		    file2 = readFile(p2.toString(), Charset.defaultCharset());
		    
		    for(int i=0;i<file1.length();i++)
		    {
		    	if(file1.charAt(i) == '\n') file1.setCharAt(i, '#');
		    }
		    
		    for(int i=0;i<file2.length();i++)
		    {
		    	if(file2.charAt(i) == '\n') file2.setCharAt(i, '#');
		    }
		    
	        String[] line1 = file1.toString().split("#");
	        String[] line2 = file2.toString().split("#");
	        
	        int i;
	        //for(i=0;i<line1.length;i++) {System.out.println(line1[i]);}
	        int M = line1.length;
	        int N = line2.length;
	        int[][] opt = new int[M+1][N+1];
		       
	        // compute length of LCS and all subproblems via dynamic programming
	        for ( i = M-1; i >= 0; i--) 
	        {
	            for (int j = N-1; j >= 0; j--) 
	            {
	                if (line1[i].equals(line2[j]))
	                {
	                    opt[i][j] = opt[i+1][j+1] + 1;
	                    //System.out.println(line1[i]);
	                     
	                }
	                else 
	                    opt[i][j] = Math.max(opt[i+1][j], opt[i][j+1]);
	            }
	        }
	        i = 0;
	        int j=0;
	        sub = new StringBuffer("");
	        //subseq.delete(0,sub.length());
	        while(i<M && j<N)
	        {
	        	if(line1[i].compareTo(line2[j])==0)
	        	{
	        		sub.append(line1[i]);
	        		sub.append("#");
	        		i++;
	        		j++;
	        	}
	        	else if(opt[i+1][j] >= opt[i][j+1]) i++;
	        	else j++;
	        }
	        return opt[0][0];
	    }
	 
		static StringBuffer readFile(String path, Charset encoding) throws IOException 
			{
			  byte[] encoded = Files.readAllBytes(Paths.get(path));
			  StringBuffer sb = new StringBuffer(encoding.decode(ByteBuffer.wrap(encoded)).toString());
			  return sb;
			}
}
