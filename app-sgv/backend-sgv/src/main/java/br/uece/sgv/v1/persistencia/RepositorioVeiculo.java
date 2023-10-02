package br.uece.sgv.v1.persistencia;

import java.util.List;

import br.uece.sgv.v1.negocio.Veiculo;

public interface RepositorioVeiculo{//Porta de Saída
    Veiculo salvarVeiculo(Veiculo veiculo);
    Veiculo buscarVeiculoPorId(Long id);
    List<Veiculo> listarVeiculos();
    void atualizarVeiculo(Veiculo veiculo);
    void excluirVeiculo(Veiculo veiculo);
}
