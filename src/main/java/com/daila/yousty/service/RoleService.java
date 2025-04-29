package com.daila.yousty.service;

import com.daila.yousty.entity.Role;
import com.daila.yousty.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    public Role getUserRole() {
        return roleRepository.findByName("user");
    }

}
