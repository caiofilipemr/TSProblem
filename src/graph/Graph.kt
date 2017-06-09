package graph

/**
 * Created by caiofilipemr on 08/06/17.
 */
interface Graph<V, E> {
    fun vertices(): List<V>
    fun edges(): List<E>
    fun edgesFrom(vertex: V): List<Pair<Int, Int>>
    fun weight(edge: E): Number
    fun source(edge: E): V
    fun destination(edge: E): V
}