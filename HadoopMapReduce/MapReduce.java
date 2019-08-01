import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
//import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.io.Writable;

public class CaptainAmerica {
	
	private static final long DAY_MILIS = 1000 * 60 * 60 * 24;

	public static void main(String[] args)  throws Exception {
		 Configuration conf = new Configuration();
		    Job job = Job.getInstance(conf, "Serverlogs"); // job name
		    job.setJarByClass(CaptainAmerica.class); //Our jar is ampped to our main class
		    job.setMapperClass(MapperTask.class); // setting the approporate mapper class
		    job.setCombinerClass(IntSumReducer.class);// The combiner class has been removed and is insited to be remove when we use custom classes. This in tutn was causing exceptions.
		    job.setReducerClass(IntSumReducer.class); // Setting reducer class
		    job.setOutputKeyClass(Text.class); //setting output type of key in map
		    job.setOutputValueClass(LongWritable.class); //setting output type of value in map
		    FileInputFormat.addInputPath(job, new Path(args[0])); //the job will take the first args which we pass in our command line as Input path
		    FileOutputFormat.setOutputPath(job, new Path(args[1])); //the job will take the second args which we pass in our command line as Output path
		    System.exit(job.waitForCompletion(true) ? 0 : 1); //system will exit once the job is completed

	}
	
	
	
	
	
	public static class MapperTask extends Mapper<Object, Text, Text, LongWritable>{
		
		  private LongWritable one = new LongWritable(1L);
		
		  private Text UrlDay = new Text();
		  SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss Z", Locale.getDefault());
		
		
		public void map(Object key, Text value, Context context) throws IOException, InterruptedException{
			
			StringTokenizer matcher = new StringTokenizer(value.toString());
			
			try {
			 String ip = matcher.nextToken();
			    matcher.nextToken(); //discard
			    matcher.nextToken();
				long timestamp = dateFormat.parse(matcher.nextToken("]").substring(2)).getTime();
				matcher.nextToken("\"");
			    String request = matcher.nextToken("\"");
			    String[] requestParts = request.split(" ");
			    String httpMethod = requestParts[0];
			    String url = requestParts[1];
			    matcher.nextToken(" ");
			    int httpStatusCode = Integer.parseInt(matcher.nextToken());
			    int responseSize = Integer.parseInt(matcher.nextToken());
			    matcher.nextToken("\"");
			    String referrer = matcher.nextToken("\"");
			    matcher.nextToken("\"");
			    String userAgent = matcher.nextToken("\"");
			    
			 
			    long timehrs = getDay(timestamp);
			    
			    UrlDay.set(url+"_"+ Long.toString(timehrs));
			   
			    
			    context.write(UrlDay,one);
			
		}
			
			catch(ArrayIndexOutOfBoundsException e)
			{
				return;
			}
			catch(NumberFormatException e) 
		    { 
		        return; 
		    } 
		    catch(NullPointerException e) 
		    {
		    	System.out.println("This is thrown");
		        return;
		    }
			
			catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		  private long getDay(long timeStamp) {
		      return (timeStamp / DAY_MILIS) * DAY_MILIS; 
		    }
	}
	
	
	public static class IntSumReducer extends Reducer<Text,LongWritable,Text,LongWritable> {
		
		LongWritable valuelong = new LongWritable();
		public void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {
			long sum = 0L; //sum up the values
		      for(LongWritable value: values) {
		        sum+= value.get();
		      }
		      
		      
		    
		      valuelong.set(sum);
		      context.write( key, valuelong);
		      
		}
	}
	
	
	
	

}

