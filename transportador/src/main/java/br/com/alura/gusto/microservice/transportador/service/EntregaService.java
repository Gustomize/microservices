package br.com.alura.gusto.microservice.transportador.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.gusto.microservice.transportador.dto.VoucherDTO;
import br.com.alura.gusto.microservice.transportador.model.Entrega;
import br.com.alura.gusto.microservice.transportador.repository.EntregaRepository;
import br.com.alura.microservice.transportador.dto.EntregaDTO;

@Service
public class EntregaService {
	
	@Autowired
	private EntregaRepository repository;

	public VoucherDTO reservaEntrega(EntregaDTO pedidoDTO) {
		
		Entrega entrega = new Entrega();
		entrega.setDataParaBusca(pedidoDTO.getDataParaEntrega());
		entrega.setPrevisaoParaEntrega(pedidoDTO.getDataParaEntrega().plusDays(1l));
		entrega.setEnderecoDestino(pedidoDTO.getEnderecoDestino());
		entrega.setEnderecoOrigem(pedidoDTO.getEnderecoOrigem());
		entrega.setPedidoId(pedidoDTO.getPedidoId());
		
		repository.save(entrega);
		
		VoucherDTO voucher = new VoucherDTO();
		voucher.setNumero(entrega.getId());
		voucher.setPrevisaoParaEntrega(entrega.getPrevisaoParaEntrega());
		return voucher;
	}

}