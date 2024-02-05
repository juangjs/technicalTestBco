package co.com.devex.r2dbc.entities.repositories;

import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import co.com.devex.r2dbc.entities.UserEntity;
import reactor.core.publisher.Mono;

public interface IUserEntityRepository extends ReactiveCrudRepository<UserEntity, String>{
	Mono<UserEntity> findByUserDocument(@Param("userDocument") int userDocument);
}
