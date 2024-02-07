package co.com.devex.api.model.request.createOrder;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class CustomerReq {
	@Valid
	@NotNull(message = "customerName required")
	@Size(min = 1, max = 100, message = "Identification number min 1 max 100")
	private String customerName;
	
	@Valid
	@NotNull(message = "customerDocument required")
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Invalid identification number")
    @Size(min = 1, max = 15, message = "Identification number min 1 max 15")
	private String customerDocument;
	
	@Valid
	@NotNull(message = "customerDocumentType required")
    @Pattern(regexp = "^[a-zA-Z]*$", message = "Invalid identification type")
    @Size(min = 1, max = 5, message = "Identification type min 1 max 5")
	private String customerDocumentType;
	
	@Valid
	@NotNull(message = "customerPhone required")
	@NotEmpty(message = "customerName not empty")
	@NotBlank(message = "customerName not blank")
	private String customerPhone;
	
	@Valid
	@NotNull(message = "customerMail required")
	@NotEmpty(message = "customerName not empty")
	@NotBlank(message = "customerName not blank")
	 @Pattern(regexp = "([a-zA-Z0-9]([^ @&%$\\\\\\/()=?¿!.,:;]?|\\d?)+[a-zA-Z0-9][\\.]){1,2}", message = "Invalid customerMail format")
	private String customerMail;
	
	@Valid
	@NotNull(message = "customerAddress required")
	@NotEmpty(message = "customerName not empty")
	@NotBlank(message = "customerName not blank")
	private String customerAddress;
}
