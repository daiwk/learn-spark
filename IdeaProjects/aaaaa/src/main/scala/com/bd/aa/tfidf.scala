import org.apache.spark.ml.feature.{HashingTF, IDF, Tokenizer}
import org.apache.spark.sql.SparkSession

/**
  * TF-IDF
  */
object extract {
  val spark=SparkSession.builder().master("local").appName("TF-IDF").getOrCreate()
  import spark.implicits._
  def main(args: Array[String]): Unit = {
    val soureceData= spark.createDataFrame(Seq(
      (0,"soyo spark like spark hadoop spark and spark like spark"),
      (1,"i wish i can like java i"),
      (2,"but i dont know how to soyo"),
      (3,"spark is good spark tool")
    )).toDF("label","sentence")
    //进行分词
    val tokenizer=new Tokenizer().setInputCol("sentence").setOutputCol("words")
    val wordsData=tokenizer.transform(soureceData)
    wordsData.show(false)  //表示不省略,打印字符串的所有单词
    val hashTF=new HashingTF().setInputCol("words").setOutputCol("rawsFeatures").setNumFeatures(1000)
    //生成特征向量
    val featuredData=hashTF.transform(wordsData)
    featuredData.show(false)
    val idf=new IDF().setInputCol("rawsFeatures").setOutputCol("features")
    val idfModel=idf.fit(featuredData)
    val result=idfModel.transform(featuredData)
    result.show(false)
    result.select("label","features").show(false)

  }
}