package co.com.devex.model.asyncnotifications.gateways;

import co.com.devex.model.asyncnotifications.Asyncnotifications;
import reactor.core.publisher.Mono;

public interface IAsyncnotificationsGateway {
	Mono<Void> sendAsyncNotificationMail(Asyncnotifications asyncnotifications);
}
