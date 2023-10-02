package br.uece.sgv.v1.negocio;

import java.util.List;

import org.springframework.stereotype.Service;

import br.uece.sgv.v1.persistencia.RepositorioVeiculo;

@Service
public class ServicoVeiculo {

	private RepositorioVeiculo repositorioVeiculo;

	public ServicoVeiculo(RepositorioVeiculo repositorioVeiculo) {
		this.repositorioVeiculo = repositorioVeiculo;
	}

	public List<Veiculo> listarVeiculos() {
		List<Veiculo> veiculos = repositorioVeiculo.listarVeiculos();
		if (veiculos.isEmpty()) {
			throw new NegocioException("Nenhuma veiculo encontrado.");
		}
		return veiculos;
	}

	public Veiculo criarVeiculo(Veiculo novoVeiculo) {
		return repositorioVeiculo.salvarVeiculo(novoVeiculo);
	}

	public Veiculo atualizarVeiculo(Veiculo veiculo) {
		repositorioVeiculo.atualizarVeiculo(veiculo);
		return buscarVeiculoPorId(veiculo.getId());
	}
		
	public void removerVeiculo(Veiculo veiculo) {
		Veiculo veiculoExistente = this.buscarVeiculoPorId(veiculo.getId());
		repositorioVeiculo.excluirVeiculo(veiculoExistente);
	}
	
	public Veiculo buscarVeiculoPorId(Long id) {
		Veiculo veiculoExistente = repositorioVeiculo.buscarVeiculoPorId(id);
		
		if (veiculoExistente == null) {
			throw new NegocioException("Veículo não encontrado.");
		}
		
		return veiculoExistente;
	}

}
