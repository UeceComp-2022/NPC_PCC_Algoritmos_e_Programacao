package br.uece.sgv.v1.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Component;

import br.uece.sgv.v1.negocio.Veiculo;

@Component
public class RepositorioVeiculoJdbc implements RepositorioVeiculo {

	private final Connection conexao;

	public RepositorioVeiculoJdbc(DataSource dataSource) {
		try {

			this.conexao = dataSource.getConnection();

		} catch (SQLException e) {
			throw new PersistenciaException(e);
		}
	}
	
	public static void main(String[] args) {
		System.out.println(LocalDateTime.now());
	}
	
	private Veiculo mapearResultadoParaVeiculo(ResultSet resultado) throws SQLException {
		Long id = resultado.getLong("id");
		String valor1 = resultado.getString("valor1");
		String valor2 = resultado.getString("valor2");
		String valor3 = resultado.getString("valor3");
		String valor4 = resultado.getString("valor4");
		String valor5 = resultado.getString("valor5");
		return new Veiculo(id, valor1, valor2, valor3, valor4, valor5);
	}

	@Override
	public Veiculo salvarVeiculo(Veiculo veiculo) {
		String sql = "INSERT INTO veiculo (valor1, valor2, valor3) VALUES (?, ?, ?)";
		try (PreparedStatement stmt = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
			stmt.setString(1, veiculo.getValor1());
			stmt.setString(2, veiculo.getValor2());
			stmt.setString(3, veiculo.getValor3()== null ? null : veiculo.getValor3().toString());

			int affectedRows = stmt.executeUpdate();

			if (affectedRows == 0) {
				throw new PersistenciaException("A inserção falhou, nenhum registro foi adicionado.");
			}

			// Recupere o ID gerado para o registro inserido
			try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					veiculo.setId(generatedKeys.getLong(1));
					System.out.printf("[LOG] ID do registro 'inserido': %d!\n", veiculo.getId());
				} else {
					throw new PersistenciaException("Nenhum ID gerado.");
				}
			}
		} catch (SQLException e) {
			throw new PersistenciaException("Erro ao salvar veiculo.", e);
		}
		return veiculo;
	}

	@Override
	public Veiculo buscarVeiculoPorId(Long id) {
		String sql = "SELECT * FROM veiculo WHERE id = ?";
		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setLong(1, id);
			try (ResultSet resultado = stmt.executeQuery()) {
				if (resultado.next()) {
					return mapearResultadoParaVeiculo(resultado);
				}
			}
		} catch (SQLException e) {
			throw new PersistenciaException("Erro ao buscar veiculo por ID.", e);
		}
		return null;
	}

	@Override
	public List<Veiculo> listarVeiculos() {
		
		List<Veiculo> veiculos = new ArrayList<>();
		String sql = "SELECT * FROM veiculo ORDER BY id DESC";
		
		try (
				PreparedStatement stmt = conexao.prepareStatement(sql); 
				ResultSet resultado = stmt.executeQuery()
		) {
			
			while (resultado.next()) {
				veiculos.add(mapearResultadoParaVeiculo(resultado));
			}
			
		} catch (SQLException e) {
			throw new PersistenciaException("Erro ao listar veiculos.", e);
		}
		return veiculos;
	}

	@Override
	public void atualizarVeiculo(Veiculo veiculo) {
		String sql = "UPDATE veiculo SET valor1 = ?, valor2 = ?, valor3 = ? WHERE id = ?";
		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setString(1, veiculo.getValor1());
			stmt.setString(2, veiculo.getValor2());
			stmt.setString(3, veiculo.getValor3()== null ? null : veiculo.getValor3().toString());
			stmt.setLong(4, veiculo.getId());
			int affectedRows = stmt.executeUpdate();

			if (affectedRows == 0) {
				throw new PersistenciaException("A atualização falhou, nenhum registro foi atualizado.");
			}

			System.out.printf("[LOG] ID do registro 'atualizado': %d!\n", veiculo.getId());
		} catch (SQLException e) {
			throw new PersistenciaException("Erro ao atualizar veiculo.", e);
		}
	}

	@Override
	public void excluirVeiculo(Veiculo veiculo) {
		String sql = "DELETE FROM veiculo WHERE id = ?";
		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setLong(1, veiculo.getId());
			int affectedRows = stmt.executeUpdate();

			if (affectedRows == 0) {
				throw new PersistenciaException("A exclusão falhou, nenhum registro foi removido.");
			}

			System.out.printf("[LOG] ID do registro 'removido': %d!\n", veiculo.getId());
		} catch (SQLException e) {
			throw new PersistenciaException("Erro ao excluir veiculo.", e);
		}
	}
	
}
