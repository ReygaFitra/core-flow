package com.reyga.dev.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "custom.security")
public class CustomSecurityConfigProperties {
    private String prefixSecured;
    private String prefixPublic;

    public String getPrefixSecured() {
        return prefixSecured;
    }

    public void setPrefixSecured(String prefixSecured) {
        this.prefixSecured = prefixSecured;
    }

    public String getPrefixPublic() {
        return prefixPublic;
    }

    public void setPrefixPublic(String prefixPublic) {
        this.prefixPublic = prefixPublic;
    }

    @Override
    public String toString() {
        return "CustomSecurityConfigProperties{" +
                "prefixSecured='" + prefixSecured + '\'' +
                ", prefixPublic='" + prefixPublic + '\'' +
                '}';
    }
}
