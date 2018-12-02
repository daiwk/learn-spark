
export spark_submit=~/Documents/spark-2.4.0-bin-hadoop2.7/bin/spark-submit

sbt package

#class=SimpleApp
class=Rating

${spark_submit} \
        --class $class \
        --master local \
        target/scala-2.11/simple-project_2.11-1.0.jar
