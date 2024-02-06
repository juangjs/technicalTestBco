package co.com.devex.asyncnotifications.amazonses.adapter;

import org.springframework.stereotype.Component;

import co.com.devex.model.asyncnotifications.Asyncnotifications;
import co.com.devex.model.asyncnotifications.gateways.IAsyncnotificationsGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class AsyncnotificationsAdapter implements IAsyncnotificationsGateway{@Override
	
	public Mono<Void> sendAsyncNotificationMail(Asyncnotifications asyncnotifications) {
		return null;
	}
}
