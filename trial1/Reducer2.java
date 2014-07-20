package trial1;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

public class Reducer2 extends MapReduceBase implements Reducer<IntWritable, Text, IntWritable, Text> 
{		
	 public static String[] calculateLCS(String [] array1,String[] array2)
	 {		
		 	StringBuffer subsequence = new StringBuffer();
		 	int i;
	        int M = array1.length;
	        int N = array2.length;
	        int[][] opt = new int[M+1][N+1];
		       
	        
	        // compute length of LCS and all subproblems via dynamic programming
	        for ( i = M-1; i >= 0; i--) 
	        {
	            for (int j = N-1; j >= 0; j--) 
	            {
	                if (array1[i].equals(array2[j]))
	                {
	                    opt[i][j] = opt[i+1][j+1] + 1;
	                    //System.out.println(array1[i]);
	                     
	                }
	                else 
	                    opt[i][j] = Math.max(opt[i+1][j], opt[i][j+1]);
	            }
	        }
	        i = 0;
	        int j=0;
	        while(i<M && j<N)
	        {
	        	if(array1[i].compareTo(array2[j])==0)
	        	{
	        		subsequence.append(array1[i]);
	        		subsequence.append("#");
	        		i++; j++;
	        	}
	        	else if(opt[i+1][j] >= opt[i][j+1]) i++;
	        	else j++;
	        }
	        return subsequence.toString().split("#");
	 }
	 
     public void reduce(IntWritable key, Iterator<Text> values, OutputCollector<IntWritable, Text> output, Reporter reporter) throws IOException 
     {
      ArrayList<String>Memo = new ArrayList<String>();
      while(values.hasNext())
      {
    	Memo.add(values.next().toString());
      }
      StringBuffer sb = new StringBuffer();
      
      String[] finalAns = Memo.get(0).split("#");
      
      //System.out.println("MemoSize"+Memo.size());
 	  for(int i=1;i<Memo.size();i++)
      {
 		 String[] array2 = Memo.get(i).split("#");
 		 finalAns = calculateLCS(finalAns,array2);
 		 
      }
 	  Text text = new Text();
 	  //System.out.println(finalAns.length);
 	  for(int i=0;i<finalAns.length;i++)
 	  {
 		  sb.append(finalAns[i]);
 		  sb.append("\n");
 	  }
 	  text.set(sb.toString());
 	  output.collect(new IntWritable(0),text);
     }
}

