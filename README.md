# learn-spark

如果是mac+scala

先brew装个sbt，

参考[https://www.cnblogs.com/memento/p/9153012.html](https://www.cnblogs.com/memento/p/9153012.html)把源换了（把这个目录的repositories文件拷到~/.sbt/下就行~~）

sbt package 

这里是参考的[http://spark.apache.org/docs/2.1.0/quick-start.html#self-contained-applications](http://spark.apache.org/docs/2.1.0/quick-start.html#self-contained-applications)
所以demo.sbt文件是：

```
name := "Simple Project"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies += "org.apache.spark" %% "spark-core" % "2.1.0"
```
