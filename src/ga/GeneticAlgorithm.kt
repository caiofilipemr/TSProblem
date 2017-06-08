package ga

/**
 * Created by caiofilipemr on 08/06/17.
 */
interface GeneticAlgorithm {
    fun populate();
    fun mutation();
    fun crossover();
    fun selection();
    fun generations();
}