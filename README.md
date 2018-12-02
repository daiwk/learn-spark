# learn-spark

上网随便搜一下，装scala+spark

## scala

如果是mac, 可以brew直接装个sbt，如果是别的操作系统，百度一下也能装

参考[https://www.cnblogs.com/shortboy/p/6069517.html](https://www.cnblogs.com/shortboy/p/6069517.html)，注意，里面的```#这个ivy```这种字眼要干掉。。不然sbt会以为这也是网址的一部分。。我已经放到当前目录啦，就是[https://github.com/daiwk/learn-spark/blob/master/repositories](https://github.com/daiwk/learn-spark/blob/master/repositories)

mac的话，把这个repositories文件拷到~/.sbt/下就行~~，其他操作系统不知道了。。

好像没啥用..好像还是去https://repo1.maven.org/maven2/org/xxxx这种地方下截。。

然后执行下

sbt run  -Dsbt.override.build.repos=true

接下来我们的demo是参考的[http://spark.apache.org/docs/2.2.0/quick-start.html#self-contained-applications](http://spark.apache.org/docs/2.2.0/quick-start.html#self-contained-applications)

所以demo.sbt文件是：

```
name := "Simple Project"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
    "org.apache.spark" %% "spark-core" % "2.2.0",
    "org.apache.spark" %% "spark-mllib" % "2.2.0",
    "org.apache.spark" %% "spark-sql" % "2.2.0"
                    )
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


## mllib

### als

启动脚本run_scala_als.sh

demo代码在./src/main/scala/Rating.scala中，参考的是[https://zhuanlan.zhihu.com/p/24220475](https://zhuanlan.zhihu.com/p/24220475)并针对spark 2.2.0做了改动。。

数据集用的[https://grouplens.org/datasets/movielens/](https://grouplens.org/datasets/movielens/)的小数据集，把./ml-latest-small/ratings.csv.origin的第一行删了（每列的名字），得到./ml-latest-small/ratings.csv

有时会出现rmse是Nan的，参考[https://stackoverflow.com/questions/43544815/why-spark-ml-als-algorithm-print-rmse-nan](https://stackoverflow.com/questions/43544815/why-spark-ml-als-algorithm-print-rmse-nan)

因此，参考其中的答案，对spark2.2.0，设置了```model.setColdStartStrategy("drop")```

最终输出形如：

```
Root-mean-square error = 1.0810692891196552

...

+------+-------+------+---------+----------+
|userId|movieId|rating|timestamp|prediction|
+------+-------+------+---------+----------+
|     1|   1298|   5.0|964984086|  5.672396|
|     1|   1220|   5.0|964981909| 4.5910745|
|     1|   2987|   5.0|964982495| 3.9871237|
|     1|    367|   4.0|964981710| 3.5012622|
|     1|   2628|   4.0|964980523|  4.485484|
|     1|   2105|   4.0|964981725|  4.109007|
|     1|   3702|   5.0|964983737| 4.1622286|
|     1|   1030|   3.0|964982903| 3.8955429|
|     1|    157|   5.0|964984100|  3.409433|
|     1|   3793|   5.0|964981855| 4.7080493|
|     1|   1089|   5.0|964982951|  4.688549|
|     1|    919|   5.0|964982475|  4.257528|
|     1|   1270|   5.0|964983705| 4.8917155|
|     1|   1198|   5.0|964981827| 4.8256407|
|     1|    923|   5.0|964981529|  3.980864|
|     1|   2366|   4.0|964982462| 3.2791367|
|     1|   2580|   5.0|964983000|  4.452132|
|     1|   2018|   5.0|964980523| 4.3208723|
|     1|   2387|   5.0|964983123| 3.6585732|
|     1|    804|   4.0|964980499|  3.497317|
+------+-------+------+---------+----------+
only showing top 20 rows


```
