package com.ssafy.greenEarth.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "green_earth_logs")
public class GreenEarthLog extends CreatedTimeEntity{

    @EmbeddedId
    private GreenEarthLogId id;

    @CreatedDate
    private LocalDateTime clearedAt;
}
