package co.com.devex.model.orders;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Orders {
	private Long id;
	private LocalDateTime createDate;
	private LocalDateTime updateDate;
	private String status;
	private String detail;
	private String customerName;
	private String customerDocument;
	private String customerDocumentType;
	private String customerPhone;
	private String customerMail;
	private String customerAddress;
	private String userId;
}
