package com.NymblE.task.DTO;

import com.NymblE.task.Model.Activity;
import lombok.Data;

@Data
public class ActivityDto {
    private Long id;
    private String activityName;
    private String description;
    private double cost;
    private int capacity;
    private DestinationDto destination;

    public ActivityDto(Activity activity) {
        this.id = activity.getId();
        this.activityName = activity.getName();
        this.description = activity.getDescription();
        this.cost = activity.getCost();
        this.capacity = activity.getCapacity();
        this.destination = new DestinationDto(activity.getDestination());
    }
}
