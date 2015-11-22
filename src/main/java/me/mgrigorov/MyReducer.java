package me.mgrigorov;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class MyReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
    private static final Logger logger = LoggerFactory.getLogger(MyReducer.class);

    @Override
    public void reduce(final Text key, final Iterable<IntWritable> values, final Context context) throws IOException, InterruptedException {
        int sum = 0;

        for (final IntWritable val : values) {
            sum += val.get();
        }

        logger.info("Reduce: key={}, value={}", key, sum);
        context.write(key, new IntWritable(sum));
    }
}
