package com.NymblE.task.DTO;

import com.NymblE.task.Model.Destination;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class DestinationActivitiesDto {
    private Long id;
    private String destinationName;
    private List<ActivityDto> activities;

    public DestinationActivitiesDto(Destination destination) {
        this.id = destination.getId();
        this.destinationName = destination.getName();
        this.activities = destination.getActivities().stream()
                .map(ActivityDto::new)
                .collect(Collectors.toList());
    }
}
