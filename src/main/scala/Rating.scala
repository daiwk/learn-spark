import org.apache.spark.sql.{DataFrame, SparkSession}

import org.apache.spark.ml.evaluation.RegressionEvaluator
import org.apache.spark.ml.recommendation.ALS

case class ARating(userId: Int, movieId: Int, rating: Float, timestamp: Long) 

object Rating {
    def parseRating(str: String): ARating = {
        val fields = str.split(",")
        assert(fields.size == 4)
        ARating(fields(0).toInt, fields(1).toInt, fields(2).toFloat, fields(3).toLong)
    }

    def main(args: Array[String]) {
        val spark = SparkSession.builder.appName("Simple Application").getOrCreate()
        import spark.implicits._
        val ratings = spark.read.textFile("./ml-latest-small/ratings.csv")
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
            .setColdStartStrategy("drop")

        val model = als.fit(training)

        // Evaluate the model by computing the RMSE on the test data
        val predictions = model.transform(test)

        val evaluator = new RegressionEvaluator()
            .setMetricName("rmse")
            .setLabelCol("rating")
            .setPredictionCol("prediction")
        val rmse = evaluator.evaluate(predictions)
        println(s"Root-mean-square error = $rmse")
        predictions.sort("userId").show(20)
    }
}
