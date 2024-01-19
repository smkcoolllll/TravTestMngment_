package com.NymblE.task.DTO;

import com.NymblE.task.Model.Destination;
import lombok.Data;

@Data
public class DestinationDto {
    private Long id;
    private String name;

    public DestinationDto(Destination destination) {
        this.id = destination.getId();
        this.name = destination.getName();
    }
}
