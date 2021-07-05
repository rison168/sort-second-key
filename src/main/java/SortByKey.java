import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;
import scala.Tuple2;

/**
 * @author : Rison 2021/7/5 下午3:02
 * 二次排序
 */
public class SortByKey {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("sort").setMaster("local[2]");
        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaRDD<String> originDataRDD = sc.textFile("data/sort.txt", 1);
        originDataRDD.mapToPair(new PairFunction<String, SecondSortKey, String>() {
            @Override
            public Tuple2<SecondSortKey, String> call(String s) throws Exception {
                String[] sp = s.split(" ");
                SecondSortKey secondSortKey = new SecondSortKey(Integer.parseInt(sp[0]), Integer.parseInt(sp[1]));
                return new Tuple2<>(secondSortKey, s);
            }
        }).sortByKey().foreach(new VoidFunction<Tuple2<SecondSortKey, String>>() {
            @Override
            public void call(Tuple2<SecondSortKey, String> secondSortKeyStringTuple2) throws Exception {
                System.out.println(secondSortKeyStringTuple2._2);
            }
        });
        sc.stop();

    }
}
