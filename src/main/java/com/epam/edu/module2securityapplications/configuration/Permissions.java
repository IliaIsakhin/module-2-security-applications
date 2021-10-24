package com.epam.edu.module2securityapplications.configuration;

public enum Permissions {
    VIEW_ADMIN("ROLE_VIEW_ADMIN"),
    VIEW_INFO("ROLE_VIEW_INFO");

    Permissions(String value) {
        this.authority = value;
    }
    
    private final String authority;
    
    public String getRole() {
       return authority.substring(5, authority.length()-1);
    }
    
    public String getAuthority() {
        return authority;
    }
}
