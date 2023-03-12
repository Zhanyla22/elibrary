package com.example.neolabs.util;

import com.example.neolabs.enums.EntityEnum;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OperationUtil {

    final static String createDescriptionFormat = "Created %s with ID of %d";
    final static String deleteDescriptionFormat = "Deleted %s with ID of %d";
    final static String archiveDescriptionFormat = "Archived %s with ID of %d";

    public String buildCreateDescription(EntityEnum entityEnum, Long entityId){
        return String.format(createDescriptionFormat, entityEnum, entityId);
    }

    public String buildDeleteDescription(EntityEnum entityEnum, Long entityId){
        return String.format(deleteDescriptionFormat, entityEnum, entityId);
    }

    public String buildArchiveDescription(EntityEnum entityEnum, Long entityId){
        return String.format(archiveDescriptionFormat, entityEnum, entityId);
    }

    // TODO: 12.03.2023 need to add description for updates with <number of the changed fields>
    public String buildUpdateDescription(EntityEnum entityEnum, Object oldEntity, Object newEntity){
        return "";
    }
}
