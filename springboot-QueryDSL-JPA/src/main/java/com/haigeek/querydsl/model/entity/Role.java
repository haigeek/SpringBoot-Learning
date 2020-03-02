package com.haigeek.querydsl.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author zhaohj
 * @date 2019/4/28 下午10:46
 */
@Entity
@Table(name = "ROLE" )
@NoArgsConstructor
@Data
public class Role implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
    @Column(name = "role")
    private String role;
    @Column(name = "description")
    private String description;

}
