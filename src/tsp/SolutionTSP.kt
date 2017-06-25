package tsp

import ga.Solution

/**
 * Created by caiofilipemr on 08/06/17.
 */
open class SolutionTSP(var solution: Array<Int>) : Solution<Int> {
    private var _cost: Int? = null
    override fun cost(): Int {
        if (_cost != null) {
            return _cost as Int
        } else {
            var sum = 0
            var i: Int = 1
            while (i < solution.size) {
                sum += TSPGraph.matrixGraph.weight(Pair(solution[i - 1], solution[i]))
                ++i
            }
            sum += TSPGraph.matrixGraph.weight(Pair(solution[solution.size-1], solution[0]))
            _cost = sum
            return _cost as Int
        }
    }

    fun switch(i: Int, j: Int) {
        _cost = null
        val aux = solution[i]
        solution[i] = solution[j]
        solution[j] = aux
    }
}