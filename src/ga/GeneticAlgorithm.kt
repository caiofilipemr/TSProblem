package ga

/**
 * Created by caiofilipemr on 08/06/17.
 */
interface GeneticAlgorithm<out T> {
    val feasibleSolution: T

    fun populate()
    fun avaliation()
    fun selection()
    fun crossover()
    fun mutation()
    fun extinction()
    fun generate()
}