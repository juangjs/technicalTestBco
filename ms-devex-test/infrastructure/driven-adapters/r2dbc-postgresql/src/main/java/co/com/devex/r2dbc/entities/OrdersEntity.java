package co.com.devex.r2dbc.entities;

import java.time.LocalDateTime;

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
@Table(value = "public.tbl_pedido")
public class OrdersEntity {
	@Id
	@Column(value = "id")
	private Long id;
	
	@Column(value = "fecha_creacion")
	private LocalDateTime createDate;
	
	@Column(value = "fecha_modificacion")
	private LocalDateTime updateDate;
	
	@Column(value = "estado")
	private String state;
	
	@Column(value = "detalle")
	private String detail;
	
	@Column(value = "nombre_cliente")
	private String customerName;
	
	@Column(value = "documento_cliente")
	private String customerDocument;
	
	@Column(value = "tipo_documento_cliente")
	private String customerDocumentType;
	
	@Column(value = "telefono_cliente")
	private String customerPhone;
	
	@Column(value = "correo_cliente")
	private String customerMail;
	
	@Column(value = "direccion_cliente")
	private String customerAddress;
	
	@Column(value = "fk_usuario_comercial")
	private String userId;
}
