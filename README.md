# learn-spark

上网随便搜一下，装scala+spark

## scala

如果是mac, 可以brew直接装个sbt，如果是别的操作系统，百度一下也能装

参考[https://www.cnblogs.com/shortboy/p/6069517.html](https://www.cnblogs.com/shortboy/p/6069517.html)，注意，里面的```#这个ivy```这种字眼要干掉。。不然sbt会以为这也是网址的一部分。。我已经放到当前目录啦，就是[https://github.com/daiwk/learn-spark/blob/master/repositories](https://github.com/daiwk/learn-spark/blob/master/repositories)

mac的话，把这个repositories文件拷到~/.sbt/下就行~~，其他操作系统不知道了。。

好像没啥用..好像还是去https://repo1.maven.org/maven2/org/xxxx这种地方下截。。

然后执行下

sbt run  -Dsbt.override.build.repos=true

接下来我们的demo是参考的[http://spark.apache.org/docs/2.1.0/quick-start.html#self-contained-applications](http://spark.apache.org/docs/2.1.0/quick-start.html#self-contained-applications)

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

