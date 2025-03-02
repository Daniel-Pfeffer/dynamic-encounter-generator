package at.jku.deq.api.dto

import kotlinx.serialization.Serializable

interface Page<T> {
    val content: List<T>
    val total: Long
    val page: Long

    val totalPages: Long
        get() = total / size + if (total % size == 0L) 0 else 1

    val size: Int
        get() = content.size

    val isEmpty: Boolean
        get() = content.isEmpty()

    val isNotEmpty: Boolean
        get() = content.isNotEmpty()

    val first: T?
        get() = content.firstOrNull()

    val last: T?
        get() = content.lastOrNull()

    val hasNext: Boolean
        get() = total > size

    companion object {
        fun <T> empty(): Page<T> = PageImpl(emptyList(), 0, 0)

        fun <T> of(coll: List<T>, page: Long, total: Long): Page<T> = PageImpl(coll, page, total)
    }
}

interface Pageable {
    val currentPage: Int
    val pageSize: Int

    companion object {
        fun unpaged(): Pageable = Unpaged
        fun of(page: Int, size: Int): Pageable = PageRequest(page, size)
    }
}

@Serializable
internal data class PageImpl<T>(
    override val content: List<T>,
    override val page: Long,
    override val total: Long,
) : Page<T>

@Serializable
internal data class PageRequest(
    override val currentPage: Int,
    override val pageSize: Int
) : Pageable

@Serializable
object Unpaged : Pageable {
    override val currentPage: Int
        get() = throw UnsupportedOperationException()
    override val pageSize: Int
        get() = throw UnsupportedOperationException()
}