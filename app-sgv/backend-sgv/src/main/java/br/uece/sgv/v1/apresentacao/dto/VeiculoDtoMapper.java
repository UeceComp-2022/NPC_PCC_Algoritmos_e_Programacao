package br.uece.sgv.v1.apresentacao.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.uece.sgv.v1.negocio.Veiculo;

public class VeiculoDtoMapper {

    public static VeiculoDto toDto(Veiculo veiculo) {
        VeiculoDto dto = new VeiculoDto();
        dto.setId(veiculo.getId());
        dto.setValor1(veiculo.getValor1());
        dto.setValor2(veiculo.getValor2());
        dto.setValor3(veiculo.getValor3());
        return dto;
    }
    
    public static Veiculo fromDto(VeiculoDto dto) {
        Veiculo veiculo = new Veiculo();
        veiculo.setId(dto.getId());
        veiculo.setValor1(dto.getValor1());
        veiculo.setValor2(dto.getValor2());
        veiculo.setValor3(dto.getValor3());
        return veiculo;
    }

    public static List<VeiculoDto> toDtoList(List<Veiculo> veiculos) {
        return veiculos.stream()
                .map(VeiculoDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    public static List<Veiculo> fromDtoList(List<VeiculoDto> dtos) {
        return dtos.stream()
                .map(VeiculoDtoMapper::fromDto)
                .collect(Collectors.toList());
    }
}
