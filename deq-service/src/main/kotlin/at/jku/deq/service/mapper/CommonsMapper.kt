package at.jku.deq.service.mapper

import at.jku.deq.api.dto.Page
import org.springframework.data.domain.Page as DomainPage

fun <T, X> DomainPage<T>.toCommonsPage(contentMapper: (T) -> X): Page<X> {
    return Page.of(content.map(contentMapper), number.toLong(), totalElements)
}