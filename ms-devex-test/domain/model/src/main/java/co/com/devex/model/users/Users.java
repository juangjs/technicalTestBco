package co.com.devex.model.users;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Users {
	private String userDocument;
	private String userDocumentType;
	private String userName;
	private String userPhone;
	private String userMail;
}
