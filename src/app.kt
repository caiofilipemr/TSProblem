import tsp.GeneticAlgorithmTSP
import tsp.SolutionTSP

/**
 * Created by caiofilipemr on 08/06/17.
 */
fun main(args: Array<String>) {
    val ga = GeneticAlgorithmTSP(SolutionTSP(listOf(1, 2, 3, 4, 5, 6, 8, 10)))
    ga.generate()
}