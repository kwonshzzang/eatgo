package kr.co.kwonshzzang.eatgo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuItem {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Long restaurantId;

    @Transient
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private boolean destory;
}
