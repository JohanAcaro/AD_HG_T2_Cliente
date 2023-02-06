package org.example.infrastructure.dto;


import org.example.infrastructure.entities.Incidencia;

@FunctionalInterface
public interface IncidenciaConverter {

    Incidencia build();

    static IncidenciaConverter convertFrom(IncidenciaDto incidenciaDto) {
        return () -> new Incidencia(
                incidenciaDto.client(),
                incidenciaDto.fecha(),
                incidenciaDto.motivo(),
                incidenciaDto.tipoIncidencia(),
                incidenciaDto.tipoReparacion(),
                incidenciaDto.solucionado()
        );
    }
}
