package trial1;

import java.io.IOException;
import java.util.Iterator;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

public class Reducer1 extends MapReduceBase implements Reducer<IntWritable, Text, IntWritable, Text> 
{
     public void reduce(IntWritable key, Iterator<Text> values, OutputCollector<IntWritable, Text> output, Reporter reporter) throws IOException 
     {
      while(values.hasNext())
      {
    	  int k = finalDriver.inputk;
    	  if(Driver1.c<k)
    	  {
    		  IntWritable intw = new IntWritable(key.get()*(-1));
    		  output.collect(intw, values.next());
    		  Driver1.c++;	
    	  }
    	  else values.next();
      }
     
     }
}

