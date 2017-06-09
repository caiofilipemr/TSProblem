package graph

import java.util.*

/**
 * Created by caiofilipemr on 08/06/17.
 */
class AdjacencyMatrixGraph(val matrix: Array<Array<Number>>) : Graph<Int, Pair<Int, Int>> {
    override fun vertices(): List<Int> = matrix.indices.toList()

    override fun edges(): List<Pair<Int, Int>> {
        val edges = LinkedList<Pair<Int, Int>>()
        for (i in matrix.indices)
            for (j in matrix.indices)
                edges += Pair<Int, Int>(i, j)
        return edges
    }

    override fun edgesFrom(vertex: Int): List<Pair<Int, Int>> {
        val edges = LinkedList<Pair<Int, Int>>()
        for (i in matrix.indices)
            edges += Pair<Int, Int>(vertex, i)
        return edges
    }

    override fun weight(edge: Pair<Int, Int>): Number {
        return matrix[edge.first][edge.second]
    }

    override fun source(edge: Pair<Int, Int>): Int {
        return edge.first
    }

    override fun destination(edge: Pair<Int, Int>): Int {
        return edge.second
    }
}