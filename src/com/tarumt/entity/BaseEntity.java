package com.tarumt.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public abstract class BaseEntity implements Serializable {
    private final String id;
    private static final Map<Class<? extends BaseEntity>, Integer> ID_COUNTER_MAP = new HashMap<>();
    private static final Map<Class<? extends BaseEntity>, String> PREFIX_MAP = new HashMap<>();

    protected BaseEntity() {
        Class<? extends BaseEntity> clazz = getClass();

        if (!PREFIX_MAP.containsKey(clazz))
            throw new IllegalStateException("Prefix not registered for " + clazz.getSimpleName());

        int nextId = ID_COUNTER_MAP.getOrDefault(clazz, 1);
        this.id = PREFIX_MAP.get(clazz) + nextId;
        ID_COUNTER_MAP.put(clazz, nextId + 1);
    }

    protected static void registerPrefix(Class<? extends BaseEntity> clazz, String prefix) {
        PREFIX_MAP.put(clazz, prefix);
    }

    public static String getNextId(Class<? extends BaseEntity> clazz) {
        if (!PREFIX_MAP.containsKey(clazz)) {
            throw new IllegalStateException("Prefix not registered for " + clazz.getSimpleName());
        }
        return PREFIX_MAP.get(clazz) + ID_COUNTER_MAP.getOrDefault(clazz, 1);
    }

    public String getId() {
        return id;
    }

    abstract public String toShortString();

    public static <T extends BaseEntity> T getById(String id, List<T> entities) {
        if (entities == null || id == null) {
            return null;
        }
        for (T entity : entities) {
            if (entity.getId().equals(id)) {
                return entity;
            }
        }
        return null;
    }

    public static <T extends BaseEntity> List<String> getIds(List<T> entities) {
        List<String> ids = new LinkedList<>();
        for (T entity : entities) ids.add(entity.getId());
        return ids;
    }
}
