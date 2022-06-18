package com.eprod.school.requestWrappers;

import lombok.Data;

import java.util.List;

@Data
public class StudentStreamWrapper {
    private Long streamId;
    private List<Long> studentIds;


//public List<AudienceGroupEntity> AudienceToGroup(){
//    return audience.stream().map(audienceId ->{
//        final AudienceGroupEntity audienceGroupEntity = new AudienceGroupEntity();
//        audienceGroupEntity.setGroupId(groupId);
//        audienceGroupEntity.setAudienceId(audienceId);
//        return audienceGroupEntity;
//
//    }).collect(Collectors.toList());
//}
}
