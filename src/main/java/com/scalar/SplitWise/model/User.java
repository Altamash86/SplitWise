package com.scalar.SplitWise.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.*;

import java.util.List;

@Entity(name = "splitwise_user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseModel{
    private String name;
    private String email;
    private String mobile;
    @ManyToMany(mappedBy = "users")
    private List<Group> groups;
}
