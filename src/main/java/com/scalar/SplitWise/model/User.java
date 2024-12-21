package com.scalar.SplitWise.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "splitwise_user")
public class User extends BaseModel{
    private String name;
    private String email;
    private String mobile;
    @ManyToMany(mappedBy = "users")
    private List<Group> groups;
}
