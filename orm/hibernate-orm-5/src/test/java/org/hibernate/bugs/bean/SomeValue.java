package org.hibernate.bugs.bean;

import java.util.UUID;

public class SomeValue {
    private final UUID uuid;

    public SomeValue(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }
}
