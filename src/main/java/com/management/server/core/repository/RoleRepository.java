package com.management.server.core.repository;

import com.management.server.core.model.Role;
import com.management.server.core.model.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Łukasz on 03.12.2018
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(RoleType name);

}
