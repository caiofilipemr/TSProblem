package tsp

import ga.GeneticAlgorithm

/**
 * Created by caiofilipemr on 08/06/17.
 */
class GeneticAlgorithmTSP(override val feasibleSolution: SolutionTSP) : GeneticAlgorithm<SolutionTSP> {
    override fun populate() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun avaliation() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun selection() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun crossover() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun mutation() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun extinction() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun generate() {
        feasibleSolution.cost()
    }
}