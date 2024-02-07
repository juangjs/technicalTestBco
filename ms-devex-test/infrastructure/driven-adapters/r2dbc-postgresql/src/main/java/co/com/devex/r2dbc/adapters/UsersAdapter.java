package co.com.devex.r2dbc.adapters;

import org.springframework.stereotype.Component;

import co.com.devex.model.users.Users;
import co.com.devex.model.users.gateways.IUsersRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class UsersAdapter implements IUsersRepository {
	
	@Override
	public Mono<Users> getUser(String userDocument) {
		return null;
	}

}
