package ga

/**
 * Created by caiofilipemr on 08/06/17.
 */
interface Solution<T> {
    fun cost()
    operator fun get(i: Int)
}