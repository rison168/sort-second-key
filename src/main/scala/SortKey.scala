import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

/**
 * @author : Rison 2021/7/5 下午3:42
 *
 */
object SortKey {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("sort").setMaster("local[2]")
    val sc = new SparkContext(conf)
    val originDataRDD: RDD[String] = sc.textFile("data/sort.txt")
    originDataRDD
      .map(
        data => {
          val strings: Array[String] = data.split(" ")
          (SecondSortKeyScala(strings(0).toInt, strings(1).toInt), data)
        }
      )
      .sortByKey()
      .foreach(data => println(data._2))
    sc.stop()
  }
}

case class SecondSortKeyScala(first: Int, second: Int) extends Ordered[SecondSortKeyScala] with Serializable {
  override def compare(that: SecondSortKeyScala): Int = {
    if (this.first - that.first == 0){
      this.second - that.second
    }else{
      this.first - that.first
    }
  }

}