package tsp

import graph.AdjacencyMatrixGraph

/**
 * Created by caiofilipemr on 21/06/17.
 */
object TSPGraph {
    private var _matrixGraph: AdjacencyMatrixGraph<Int> = AdjacencyMatrixGraph(arrayListOf(arrayListOf()))
    var matrixGraph: AdjacencyMatrixGraph<Int>
        get() = _matrixGraph
        set(value) {
            _matrixGraph = value
        }
}