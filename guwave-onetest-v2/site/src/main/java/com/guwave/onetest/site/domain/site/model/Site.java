package com.guwave.onetest.site.domain.site.model;

import com.guwave.onetest.common.domain.Entity;
import com.guwave.onetest.common.util.UUIDUtil;
import com.guwave.onetest.site.constant.ErrorCode;
import com.guwave.onetest.site.exception.ConnectionAlreadyExistException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Site extends Entity<String> {
    private boolean isActive = false;
    // number start by 0
    private Long number;
    private final List<Connection> connections = new ArrayList<>();

    public static Site create(long number) {
        return new Site(UUIDUtil.uuid(), number);
    }

    public Site(String id, long number) {
        super(id);
        this.number = number;
    }

    public boolean isActive() {
        return isActive;
    }

    public void addConnection(Connection connection) {
        if (this.connections.stream().anyMatch(it -> it.equals(connection))) {
            throw new ConnectionAlreadyExistException(
                ErrorCode.CONNECTION_ALREADY_EXIST,
                connection.id() + " already exist");
        }
        this.connections.add(connection);
    }

    public void removeConnectionByPinId(Connection connection) {
        this.connections.removeIf(it -> it.id().equals(connection.id()));
    }

    public void removeConnectionByPinId(String pinId) {
        this.connections.removeIf(it -> it.getPinId().equals(pinId));
    }

    public void removeConnection(String connectionId) {
        this.connections.removeIf(it -> it.id().equals(connectionId));
    }

    public boolean hasConnection(String connectionId) {
        return this.connections.stream().anyMatch(it -> it.id().equals(connectionId));
    }

    public Optional<Connection> connection(String connectionId) {
        return this.connections.stream().filter(it -> it.id().equals(connectionId)).findFirst();
    }

    public Long number() {
        return number;
    }

    public void resetNumber(long number) {
        this.number = number;
    }

    public List<Connection> connections() {
        return Collections.unmodifiableList(this.connections);
    }
}
