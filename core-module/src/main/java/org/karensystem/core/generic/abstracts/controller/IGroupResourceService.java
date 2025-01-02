package org.karensystem.core.generic.abstracts.controller;

public interface IGroupResourceService {
    boolean preAuthorize(String resourceName, String permissionType);
}
