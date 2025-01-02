package org.karensystem.core.permissions;

public enum Permissions {
    R("R"),
    W("W"),
    E("E"),
    D("D"),
    X("X"),
    P("P");

    private String pr;

    public String getPr()
    {
        return this.pr;
    }

    private Permissions(String pr)
    {
        this.pr = pr;
    }
}
