package me.mgrigorov;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.StringTokenizer;

public class MyMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    private static final IntWritable ONE = new IntWritable(1);
    private final transient Text word = new Text();

    @Override
    public void map(final LongWritable key, final Text value, final Context context) throws IOException, InterruptedException {
        final String line = value.toString();
        final StringTokenizer tokenizer = new StringTokenizer(line);

        while (tokenizer.hasMoreTokens()) {
            Integer number = Integer.valueOf(tokenizer.nextToken());

            for (int i = 1; i <= 10; i++) {
                if (number % i == 0) {
                    word.set("mod " + String.valueOf(i));

                    context.write(word, ONE);
                }
            }
        }
    }
}
