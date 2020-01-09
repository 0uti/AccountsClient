package com.mojang.api.profiles;

import java.util.UUID;

public class Profile {
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public UUID getUUID()
    {
        return UUID.fromString(this.id
                .replaceFirst (
                "(\\p{XDigit}{8})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}+)",
                "$1-$2-$3-$4-$5"
        ));
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
