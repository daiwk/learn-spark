import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.SQLContext
import org.apache.spark.sql.types._
import org.apache.spark.sql.Row
import org.apache.spark.rdd.RDD


object Sparksql {
    def main(args: Array[String]) {
        val spark = SparkSession.builder.appName("Simple Application").getOrCreate()
        val sqlContext = spark.sqlContext
    }
}
