package org.example.infrastructure.dto;


import org.example.infrastructure.entities.Client;
import org.example.infrastructure.entities.TipoIncidencia;
import org.example.infrastructure.entities.TipoReparacion;

public record IncidenciaDto(Client client, String fecha, String motivo, TipoIncidencia tipoIncidencia,
                            TipoReparacion tipoReparacion, Boolean solucionado) {
}
