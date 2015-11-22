package me.mgrigorov;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;

public class ConfigTool extends Configured implements Tool {
    private Class<?> app;
    private Class<? extends Mapper> mapper;
    private Class<? extends Reducer> reducer;

    private ConfigTool() {
    }

    public ConfigTool(Class<?> app, Class<? extends Mapper> mapper, Class<? extends Reducer> reducer) {
        this.app = app;
        this.mapper = mapper;
        this.reducer = reducer;
    }

    @Override
    public int run(final String[] args) throws Exception {
        final Configuration conf = this.getConf();
        final Job job = Job.getInstance(conf, "Simple Count");
        job.setJarByClass(app);

        job.setMapperClass(mapper);
        job.setReducerClass(reducer);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);

        FileSystem fs = FileSystem.get(conf);

        Path outputPath = new Path(args[1]);

        if (fs.exists(outputPath)) {
            fs.delete(outputPath, true);
        }

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, outputPath);

        return job.waitForCompletion(true) ? 0 : 1;
    }
}
