package com.cloud.lesson.cloud.item.domain;

import lombok.Data;
import javax.persistence.Id;

@Data
public class Items {

    @Id
    private String id;
    private String name;
    private Integer counts;
}
