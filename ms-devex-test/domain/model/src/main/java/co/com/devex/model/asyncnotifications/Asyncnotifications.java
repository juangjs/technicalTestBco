package co.com.devex.model.asyncnotifications;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Asyncnotifications {
	private String message;
	private String mail;
}
