import graph.AdjacencyMatrixGraph
import tsp.GeneticAlgorithmTSP
import tsp.SolutionTSP
import tsp.TSPGraph
import java.io.File
import java.util.*

fun aleatorySolution(size: Int) : SolutionTSP {
    var list = mutableListOf<Int>()
    var i = 0
    while (i < size) {
        list.plusAssign(i)
        ++i
    }

    var solution = mutableListOf<Int>()
    val rand = Random()
    while (!list.isEmpty()) {
        i = (rand.nextInt(list.size))
        solution.plusAssign(list.removeAt(i))
    }
    return SolutionTSP(solution.toTypedArray())
}

/**
 * Tests for comparison
 */
fun main(args: Array<String>) {
    val nVertices = arrayOf(10, 250, 500, 750)
    val popSize = arrayOf(100, 500, 1000, 5000)
    val years = arrayOf(100, 500, 1000, 10000)
    val reportFile = File("report/report.txt")
    val report = StringBuilder()

    var i = 0
    while (i < nVertices.size) {
        val bufferedReader = File("files/grafo-caixeiro-matriz-" + nVertices[i] + ".txt").bufferedReader()
        val lineList = mutableListOf<String>()

        bufferedReader.useLines { lines -> lines.forEach { lineList.add(it) } }
        val matrix = arrayListOf<ArrayList<Int>>()
        var arrayList: ArrayList<Int>

        lineList.forEach { line ->
            arrayList = arrayListOf<Int>()
            line.split(';').forEach({ arrayList.plusAssign(it.toInt()) })
            matrix += arrayList
        }

        TSPGraph.matrixGraph = AdjacencyMatrixGraph(matrix)
        val solution = aleatorySolution(nVertices[i])

        var j = 0
        while (j < popSize.size) {
            var k = 0
            while (k < years.size) {
                var l = 0
                while (l < 1) {
                    val ga = GeneticAlgorithmTSP(solution, popSize[j], years[k], 20)
                    ga.generate()
                    val str = getResults(ga.champion, nVertices[i], popSize[j], years[k], l+1, reportFile)
                    report.append(str)
                    println("$l-$k-$j-$i")
                    ++l
                }
                ++k
            }
            ++j
        }
        ++i
    }
    reportFile.bufferedWriter().use { it.write(report.toString()) }
}

fun getResults(champion: SolutionTSP, vertices: Int, popSize: Int, years: Int, iteration: Int, reportFile: File): String {
    return "Tamanho do grafo: $vertices, Tamanho da população: $popSize, Qtde. anos: " +
            "$years, Tentativa: $iteration, Melhor solução: ${champion.cost()}\n"
}