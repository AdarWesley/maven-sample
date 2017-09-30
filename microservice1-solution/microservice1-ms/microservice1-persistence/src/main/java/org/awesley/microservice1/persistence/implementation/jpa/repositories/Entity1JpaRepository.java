package org.awesley.microservice1.persistence.implementation.jpa.repositories;

import org.awesley.microservice1.persistence.implementation.jpa.entities.JpaEntity1;
import org.springframework.data.repository.CrudRepository;

public interface Entity1JpaRepository extends CrudRepository<JpaEntity1, String> {

}
