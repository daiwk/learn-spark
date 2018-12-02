
export spark_submit=~/Documents/spark-2.4.0-bin-hadoop2.7/bin/spark-submit

${spark_submit} \
        --master local \
        ./pyspark_demo/demo.py
