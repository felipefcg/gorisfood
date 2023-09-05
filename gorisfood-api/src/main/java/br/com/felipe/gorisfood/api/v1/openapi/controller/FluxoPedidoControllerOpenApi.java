package br.com.felipe.gorisfood.api.v1.openapi.controller;

import org.springframework.http.ResponseEntity;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Pedidos")
@SecurityRequirement(name = "security_auth")public interface FluxoPedidoControllerOpenApi {
	public ResponseEntity<Void> confirmar(String codigoPedido);
	public ResponseEntity<Void> entregar(String codigoPedido);
	public ResponseEntity<Void> cancelar(String codigoPedido);
}
