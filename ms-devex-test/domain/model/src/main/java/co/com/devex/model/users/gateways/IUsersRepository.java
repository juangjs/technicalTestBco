package co.com.devex.model.users.gateways;

import co.com.devex.model.users.Users;
import reactor.core.publisher.Mono;

public interface IUsersRepository {
	Mono<Users> getUser(String userDocument);
}
