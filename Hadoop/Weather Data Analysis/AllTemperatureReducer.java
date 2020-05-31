// cc AllTemperatureReducer Reducer for the ncdc weather dataset
// vv AllTemperatureReducer
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class AllTemperatureReducer
  extends Reducer<Text, IntWritable, Text, IntWritable> {
  
  @Override
  public void reduce(Text key, Iterable<IntWritable> values,
      Context context)
      throws IOException, InterruptedException {

    int tempValue = Integer.MIN_VALUE;

    for (IntWritable value : values) {
      tempValue = value.get();
      context.write(new Text(key), new IntWritable(tempValue));	
    }
  }
}
// ^^ AllTemperatureReducer
