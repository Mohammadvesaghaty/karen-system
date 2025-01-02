package org.karensystem.core.generic.abstracts.controller;

import lombok.Data;
import org.karensystem.core.permissions.Permissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Data
public class AbstractCustomController {

    @Autowired
    private  IGroupResourceService groupResourceService;

    public void authorization(String resourceName, Permissions pr){
        groupResourceService.preAuthorize(resourceName, pr.name());

    }
}
