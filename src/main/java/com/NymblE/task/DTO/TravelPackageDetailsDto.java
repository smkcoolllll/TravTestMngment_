package com.NymblE.task.DTO;

import com.NymblE.task.Model.TravelPackage;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class TravelPackageDetailsDto {
    private Long id;
    private String packageName;
    private List<DestinationActivitiesDto> destinations;

    public TravelPackageDetailsDto(TravelPackage travelPackage) {
        this.id = travelPackage.getId();
        this.packageName = travelPackage.getName();
        this.destinations = travelPackage.getDestinations().stream()
                .map(DestinationActivitiesDto::new)
                .collect(Collectors.toList());
    }
}
