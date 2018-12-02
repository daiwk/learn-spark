# learn-spark

上网随便搜一下，装scala+spark

## scala

如果是mac, 可以brew直接装个sbt，如果是别的操作系统，百度一下也能装

参考[https://www.cnblogs.com/memento/p/9153012.html](https://www.cnblogs.com/memento/p/9153012.html)把源换了（mac的话，把这个目录的repositories文件拷到~/.sbt/下就行~~，其他操作系统不知道了。。）

这里是参考的[http://spark.apache.org/docs/2.1.0/quick-start.html#self-contained-applications](http://spark.apache.org/docs/2.1.0/quick-start.html#self-contained-applications)

所以demo.sbt文件是：

```
name := "Simple Project"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies += "org.apache.spark" %% "spark-core" % "2.1.0"
```

所以我们的run_scala.sh长这样：

```
sbt package

${spark_submit} \
        --class "SimpleApp" \
        --master local \
        target/scala-2.11/simple-project_2.11-1.0.jar
```

## pyspark

不用蛋疼的sbt了，直接run_pyspark.sh就行啦

