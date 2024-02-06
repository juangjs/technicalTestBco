package co.com.devex.usecase.asyncnotifications;

import co.com.devex.model.asyncnotifications.Asyncnotifications;
import co.com.devex.model.asyncnotifications.gateways.IAsyncnotificationsGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class AsyncnotificationsUseCase {
	private final IAsyncnotificationsGateway iAsyncnotificationsRepository;
	
	public Mono<Void> sendAsyncNotificationMail(Asyncnotifications asyncnotifications){
		return iAsyncnotificationsRepository.sendAsyncNotificationMail(asyncnotifications);
	}
}
