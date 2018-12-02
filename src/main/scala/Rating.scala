//import spark.implicits._

import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, SparkSession}

import org.apache.spark.ml.evaluation.RegressionEvaluator
import org.apache.spark.ml.recommendation.ALS

case class ARating(userId: Int, movieId: Int, rating: Float, timestamp: Long) 

object Rating {
def parseRating(str: String): ARating = {
    val fields = str.split("::")
    assert(fields.size == 4)
    ARating(fields(0).toInt, fields(1).toInt, fields(2).toFloat, fields(3).toLong)
}


//    def parseRating(str: String) {
//        val fields = str.split("::")
//        assert(fields.size == 4)
//        return (fields(0).toInt, fields(1).toInt, fields(2).toFloat, fields(3).toLong)
//    }

    def main(args: Array[String]) {
        //val ratings = spark.read.textFile("./sample_movielens_ratings.txt")
        val logFile = "./sample_movielens_ratings.txt" // Should be some file on your system
        val conf = new SparkConf().setAppName("Simple Application")
        val sc = new SparkContext(conf)
        val sqlContext= new org.apache.spark.sql.SQLContext(sc)

        import sqlContext.implicits._
        val ratings = sc.textFile(logFile, 2)
            .map(parseRating)
            .toDF()
        val Array(training, test) = ratings.randomSplit(Array(0.8, 0.2))

        // Build the recommendation model using ALS on the training data
        val als = new ALS()
            .setMaxIter(5)
            .setRegParam(0.01)
            .setUserCol("userId")
            .setItemCol("movieId")
            .setRatingCol("rating")
        val model = als.fit(training)

        // Evaluate the model by computing the RMSE on the test data
        val predictions = model.transform(test)

        val evaluator = new RegressionEvaluator()
            .setMetricName("rmse")
            .setLabelCol("rating")
            .setPredictionCol("prediction")
        val rmse = evaluator.evaluate(predictions)
        println(s"Root-mean-square error = $rmse")
    }
}
