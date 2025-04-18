/**
 * @author Lim Yuet Yang
 */
package com.tarumt.entity;

import java.io.Serializable;
import com.tarumt.adt.list.ListInterface;
import com.tarumt.adt.list.DoublyLinkedList;

public abstract class BaseEntity implements Serializable {
    private final String id;
    
    protected BaseEntity(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    abstract public String toShortString();

    public static <T extends BaseEntity> T getById(String id, ListInterface<T> entities) {
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

    public static <T extends BaseEntity> ListInterface<String> getIds(ListInterface<T> entities) {
        ListInterface<String> ids = new DoublyLinkedList<>();
        for (T entity : entities) {
            ids.add(entity.getId());
        }
        return ids;
    }
}