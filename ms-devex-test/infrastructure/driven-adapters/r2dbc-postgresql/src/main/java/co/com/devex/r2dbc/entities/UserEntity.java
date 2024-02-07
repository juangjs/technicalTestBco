package co.com.devex.r2dbc.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(value = "public.tbl_usuario")
public class UserEntity {
	@Id
	@Column(value = "identificacion")
	private String userDocument;
	
	@Column(value = "tipo_identificacion")
	private String userDocumentType;
	
	@Column(value = "nombre")
	private String userName;
	
	@Column(value = "celular")
	private String userPhone;
	
	@Column(value = "correo")
	private String userMail;
}
