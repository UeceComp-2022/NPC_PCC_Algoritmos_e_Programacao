package br.uece.sgv.v1.apresentacao.controller;


import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.uece.sgv.v1.apresentacao.dto.VeiculoDto;
import br.uece.sgv.v1.apresentacao.dto.VeiculoDtoMapper;
import br.uece.sgv.v1.negocio.ServicoVeiculo;
import br.uece.sgv.v1.negocio.Veiculo;

@RestController
@RequestMapping("/v1/veiculos")
public class VeiculoController {

	private final ServicoVeiculo VeiculoService;

	public VeiculoController(ServicoVeiculo VeiculoService) {
		this.VeiculoService = VeiculoService;
	}

	@GetMapping
	public List<VeiculoDto> listarTodos() {
		List<Veiculo> veiculos = VeiculoService.listarVeiculos();
		return VeiculoDtoMapper.toDtoList(veiculos);
	}
	
	@GetMapping("/{id}")
	public VeiculoDto obterPorId(@PathVariable Long id) {
		Veiculo veiculo = VeiculoService.buscarVeiculoPorId(id);
		return VeiculoDtoMapper.toDto(veiculo);
	}

//	@GetMapping("/letra/{letra}")
//	public List<VeiculDto> listarPorLetraInicial(@PathVariable String letra) {
//		List<Veiculo> veiculos = veiculoService.listarPorLetraInicial(letra);
//		return VeiculoDtoMapper.toDtoList(veiculos);
//	}

//	@GetMapping("/nome/{nome}")
//	publicVeiculDto buscarPorNome(@PathVariable String nome) {
//		Veiculo funcionario = funcionarioService.buscarPorNome(nome);
//		return VeiculoDtoMapper.toDto(funcionario);
//	}

//	@GetMapping("/telefone/{telefone}")
//	public VeiculoDto buscarPorTelefone(@PathVariable String telefone) {
//		Veiculo funcionario = funcionarioService.buscarPorTelefone(telefone);
//		return VeiculoDtoMapper.toDto(funcionario);
//	}

	@PostMapping
	public VeiculoDto criarVeiculo(@RequestBody VeiculoDto veiculoDto) {
		Veiculo veiculo = VeiculoDtoMapper.fromDto(veiculoDto);
		veiculo = VeiculoService.criarVeiculo(veiculo);
		return VeiculoDtoMapper.toDto(veiculo);
	}

	@DeleteMapping("/{id}")
	public void deletarVeiculo(@PathVariable Long id) {
		VeiculoService.removerVeiculo(new Veiculo(id));
	}
	
	@PutMapping("/{id}")
	public VeiculoDto editarVeiculo(@PathVariable Long id, @RequestBody VeiculoDto veiculoDto) {
		veiculoDto.setId(id);
		Veiculo veiculo = VeiculoDtoMapper.fromDto(veiculoDto);
		Veiculo veiculoAtualizado = VeiculoService.atualizarVeiculo(veiculo);
	    return VeiculoDtoMapper.toDto(veiculoAtualizado);
	}

//    @PostMapping("/json/importar")
//    public void importarVeiculos(@RequestBody List<VeiculoDto> funcionariosImportados) {
//        funcionarioService.importarVeiculos(VeiculoDtoMapper.fromDtoList(funcionariosImportados));
//    }

//	@PostMapping("/csv/importar")
//	public void importarVeiculos(@RequestParam("file") MultipartFile arquivo) throws IOException {
//		try (BufferedReader reader = new BufferedReader(new InputStreamReader(arquivo.getInputStream()))) {
//
//			List<VeiculoDto> funcionariosDto = new ArrayList<>();
//			String linha;
//
//			for (int numLinha = 1; (linha = reader.readLine()) != null; numLinha++) {
//				// Divida a linha em campos usando a vírgula como delimitador
//
//				String[] campos = linha.split(";");
//				if (numLinha > 1 && campos.length == 3) { // Certifique-se de que há 3 campos: ID, Nome e Telefone
//					String id = campos[0].trim();
//					String nome = campos[1].trim();
//					String telefone = campos[2].trim();
//
//					// Crie um objeto VeiculoDto com os dados lidos
//					VeiculoDto funcionarioDto = new VeiculoDto();
//					funcionarioDto.setId(Long.parseLong(id));
//					funcionarioDto.setNome(nome);
//					funcionarioDto.setTelefone(telefone);
//					funcionariosDto.add(funcionarioDto);
//
//				}
//			}
//
//			// Chame o serviço para importar o funcionario
//			funcionarioService.importarVeiculos(funcionariosDto);
//
//		} catch (IOException e) {
//			throw e;
//		}
//	}
//
//	@GetMapping("/csv/exportar")
//	public void exportar(HttpServletResponse response) {
//		response.setContentType("text/csv");
//		response.setHeader("Content-Disposition", "attachment; filename=\"funcionarios.csv\"");
//
//		List<Veiculo> funcionarios = funcionarioService.listarTodos();
//
//		try (PrintWriter writer = response.getWriter()) {
//			// Escreve o cabeçalho do CSV
//			writer.println("ID;Nome;Telefone");
//
//			// Escreve os dados dos funcionarios no CSV
//			for (Veiculo funcionario : funcionarios) {
//				writer.println(funcionario.getId() + ";" + funcionario.getNome() + ";" + funcionario.getTelefone());
//			}
//		} catch (IOException e) {
//			// Trate exceções, se necessário
//		}
//	}
}
