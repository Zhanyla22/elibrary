package com.example.neolabs.util;

import com.example.neolabs.enums.EntityEnum;
import com.example.neolabs.enums.OperationType;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OperationUtil {

    final static String createDescriptionFormat = "Created %s with ID of %d";
    final static String deleteDescriptionFormat = "Deleted %s with ID of %d";
    final static String archiveDescriptionFormat = "Archived %s with ID of %d";
    final static String updateDescriptionFormat = "Archived %s with ID of %d";
    final static String enrollDescriptionFormat = "Enrolled student with ID of %d to the group with of %d";

    public String buildDescription(OperationType operationType, EntityEnum entityEnum, Long entityId){
        if (operationType == OperationType.CREATE){
            return buildCreateDescription(entityEnum, entityId);
        } else if (operationType == OperationType.UPDATE){
            return buildUpdateDescription(entityEnum, entityId);
        } else if (operationType == OperationType.ARCHIVE){
            return buildArchiveDescription(entityEnum, entityId);
        } else {
            return buildDeleteDescription(entityEnum, entityId);
        }
    }

    public String buildCreateDescription(EntityEnum entityEnum, Long entityId){
        return String.format(createDescriptionFormat, entityEnum, entityId);
    }

    public String buildDeleteDescription(EntityEnum entityEnum, Long entityId){
        return String.format(deleteDescriptionFormat, entityEnum, entityId);
    }

    public String buildArchiveDescription(EntityEnum entityEnum, Long entityId){
        return String.format(archiveDescriptionFormat, entityEnum, entityId);
    }

    public String buildUpdateDescription(EntityEnum entityEnum, Long entityId){
        return String.format(updateDescriptionFormat, entityEnum, entityId);
    }

    public String buildEnrollDescription(Long groupId, Long studentId){
        return String.format(enrollDescriptionFormat, studentId, groupId);
    }
}
