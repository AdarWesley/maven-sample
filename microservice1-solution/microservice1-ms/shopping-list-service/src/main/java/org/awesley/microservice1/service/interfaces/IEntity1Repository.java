package org.awesley.microservice1.service.interfaces;

import org.awesley.microservice1.service.model.Entity1;

public interface IEntity1Repository {
	Entity1 getById(String ID);
}
