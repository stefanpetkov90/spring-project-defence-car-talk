package com.springprojectdefence.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
@DiscriminatorValue("basic_user")
public class BasicUser extends User {
}
