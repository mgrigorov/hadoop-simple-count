package me.mgrigorov;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.util.ToolRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(final String[] args) throws Exception {
        logger.info("Executing new count");
        final int returnCode = ToolRunner.run(new Configuration(), new ConfigTool(App.class, MyMapper.class, MyReducer.class), args);

        logger.info("Application shutdown: return_code={}", returnCode);
        System.exit(returnCode);
    }
}