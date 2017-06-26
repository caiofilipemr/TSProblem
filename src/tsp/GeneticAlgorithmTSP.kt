package tsp

import aleatorySolution
import ga.GeneticAlgorithm
import java.util.*

/**
 * Created by caiofilipemr on 08/06/17.
 */
class GeneticAlgorithmTSP(override val feasibleSolution: SolutionTSP,
                          val populationSize: Int,
                          val years: Int,
                          val solutionsOnTournament: Int) : GeneticAlgorithm<SolutionTSP> {
    private var population = mutableListOf<SolutionTSP>()
    private var toCrossover = mutableListOf<SolutionTSP>()
    private var toMutate = mutableListOf<SolutionTSP>()
    private var newPopulation = mutableListOf<SolutionTSP>()
    private var _champion = feasibleSolution
    var champion: SolutionTSP
        get() = _champion
        set(value) {
            _champion = value
        }
    val solutionSize = champion.solution.size
    val rand = Random()

    override fun populate() {
        var i = 0
        var solution: SolutionTSP
        while (i < populationSize) {
            solution = aleatorySolution(solutionSize)
            population.plusAssign(solution)
            if (solution.cost() < champion.cost()) {
                champion = solution
            }
            ++i
        }
        champion = SolutionTSP(champion.solution.clone())
    }

    override fun avaliation() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun selection() {
        var i = 0
        while (i < populationSize / 2) {
            toCrossover.plusAssign(selectToTournament())
            ++i
        }
    }

    private fun crossRand(solution1: SolutionTSP, solution2: SolutionTSP) : SolutionTSP {
        var i = rand.nextInt(solutionSize)
        var j = solution2.solution.find { it == solution1.solution[i] }
        val clonedSolution = SolutionTSP(solution2.solution.clone())
        clonedSolution.switch(i, j as Int)
        return clonedSolution
    }

    private fun cross(solution1: SolutionTSP, solution2: SolutionTSP): List<SolutionTSP> {
        return listOf(crossRand(solution1, solution2), crossRand(solution2, solution1))
    }

    private fun selectToTournament() : SolutionTSP {
        var solution: SolutionTSP
        var bestSolution = SolutionTSP(emptyArray())
        var costBestSolution = Int.MAX_VALUE
        var i = 0
        while (i < solutionsOnTournament) {
            solution = population[rand.nextInt(populationSize)]
            if (solution.cost() < costBestSolution) {
                bestSolution = solution
            }
            ++i
        }
        return bestSolution
    }

    override fun crossover() {
        var i = 0
        while (i < toCrossover.size) {
            toMutate.plusAssign(cross(toCrossover[i], toCrossover[i+1]))
            i += 2
        }
    }

    fun mutate(solution: SolutionTSP) : SolutionTSP {
        val newSolution = SolutionTSP(solution.solution.clone())
        val i = rand.nextInt(solutionSize)
        var j = rand.nextInt(solutionSize)
        while (i == j) j = rand.nextInt(solutionSize)
        newSolution.switch(i, j)
        return newSolution
    }

    override fun mutation() {
        toMutate.forEach { newPopulation.plusAssign(mutate(it)) }
    }

    override fun extinction() {
        newPopulation.plusAssign(toMutate)
        while (newPopulation.size < populationSize) {
            newPopulation.plusAssign(mutate(champion))
        }
        population.sortBy { it.cost() }
        newPopulation.sortBy { it.cost() }
        population.removeAll(population.subList((populationSize * 0.4).toInt(), populationSize))
        population.addAll(newPopulation.subList(0, populationSize - population.size))
        population.forEach { sol ->
            if (sol.cost() < champion.cost()) {
                champion = sol
            }
        }
        champion = SolutionTSP(champion.solution.clone())
        toCrossover.clear()
        toMutate.clear()
        newPopulation.clear()
    }

    override fun generate() {
        populate()
        var i = 0
        while (i < years) {
            selection()
            crossover()
            mutation()
            extinction()
            ++i
        }
    }
}