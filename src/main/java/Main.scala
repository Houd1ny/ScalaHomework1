import org.apache.spark.graphx.{GraphLoader, PartitionStrategy}
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.log4j.Logger
import org.apache.log4j.Level

import org.apache.spark.graphx.lib.TriangleCountCopyPaste

object Main {
  def main(args:Array[String]): Unit = {
    Logger.getLogger("org").setLevel(Level.OFF)
    Logger.getLogger("akka").setLevel(Level.OFF)
    val conf = new SparkConf().setAppName("TriangleCount")
    val sc = new SparkContext(conf)
    val graph = GraphLoader.edgeListFile(sc, "followers.txt", true)
      .partitionBy(PartitionStrategy.RandomVertexCut)
    val triCounts = TriangleCountCopyPaste.run(graph).vertices;
    triCounts.take(6)
    triCounts.values.sum()
    triCounts.map(_._2).sum()
    println(triCounts.map(_._2).reduce(_ + _))
  }
}
