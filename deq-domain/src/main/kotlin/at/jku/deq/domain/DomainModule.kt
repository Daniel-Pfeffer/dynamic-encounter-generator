package at.jku.deq.domain

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories


@EnableJpaRepositories(basePackages = ["at.jku.deq.domain.repository"])
@EntityScan(basePackages = ["at.jku.deq.domain.entity"])
class DomainModule