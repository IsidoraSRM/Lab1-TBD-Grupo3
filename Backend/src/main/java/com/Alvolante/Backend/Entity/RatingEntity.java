package com.Alvolante.Backend.Entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RatingEntity {
    private long id;
    private Integer orderId;
    private Integer score;
    private String comment;

}