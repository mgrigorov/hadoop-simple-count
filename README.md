Requires:
* installed JDK 7 (config: JAVA_HOME, etc.)
* installed Hadoop 2.6.2 (config: HADOOP_HOME, <hadoop folder>/etc/hadoop/hadoop-env.sh)
* installed Maven 3.0.5 (config: M2_HOME, etc.)

Run:
* mvn clean compile exec:java

OR
* mvn clean install
* hadoop jar ./target/simple-count-1.0-SNAPSHOT.jar input/ output/

Input / Output folders:
* input folder contains file with 300000 random int values
* output folder will be created in process of MapReduce work
* output folder will be deleted if exists