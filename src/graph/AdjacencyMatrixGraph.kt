package graph

import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by caiofilipemr on 08/06/17.
 */
class AdjacencyMatrixGraph<T : Number>(val matrix: ArrayList<ArrayList<T>>) : Graph<Int, Pair<Int, Int>> {
    override fun vertices(): List<Int> = matrix.indices.toList()

    override fun edges(): List<Pair<Int, Int>> {
        val edges = LinkedList<Pair<Int, Int>>()
        for (i in matrix.indices)
            for (j in matrix.indices)
                edges += Pair(i, j)
        return edges
    }

    override fun edgesFrom(vertex: Int): List<Pair<Int, Int>> {
        val edges = LinkedList<Pair<Int, Int>>()
        for (i in matrix.indices)
            edges += Pair(vertex, i)
        return edges
    }

    override fun weight(edge: Pair<Int, Int>): T {
        return matrix[edge.first][edge.second]
    }

    override fun source(edge: Pair<Int, Int>): Int {
        return edge.first
    }

    override fun destination(edge: Pair<Int, Int>): Int {
        return edge.second
    }
}