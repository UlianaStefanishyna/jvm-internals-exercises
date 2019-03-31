# jvm-internals-exercises
Here are some different tasks related to JVM internals topic

For benchmarking:

*1*. 
```asp
   mvn archetype:generate
              -DinteractiveMode=false
              -DarchetypeGroupId=org.openjdk.jmh
              -DarchetypeArtifactId=jmh-java-benchmark-archetype
              -DgroupId=com.jenkov
              -DartifactId=first-benchmark
              -Dversion=1.0
```

*2*. 
```asp
   cd /benchmark
```

*3*. 
```asp
   mvn clean install
```

*4*. 
```asp
   java -jar target/benchmarks.jar
```
