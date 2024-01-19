package com.NymblE.task.Exceptions;

public class ActivityNotFoundException extends Exception {

    public ActivityNotFoundException(Long activityId) {
        super("Activity not found with id: " + activityId);
    }
}