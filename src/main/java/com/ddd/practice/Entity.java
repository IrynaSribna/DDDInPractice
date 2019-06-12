package com.ddd.practice;

import java.util.Objects;
import java.util.function.Function;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public abstract class Entity {

    private long id;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Entity entity = (Entity) o;

        if (id==0 || entity.id== 0) {
            return false; // means that the id is not set yet
        }
        return id == entity.id;
    }



    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    Function<Entity, Function<Entity, Boolean>> equalsOperator = x -> y ->
        x == null && y == null || (x != null && y != null && x.equals(y));

    Function<Entity, Function<Entity, Boolean>> unequalsOperator = x -> y -> !equalsOperator.apply(x).apply(y);

    public boolean equalOperator(Entity entity1, Entity entity2) {
        return equalsOperator.apply(entity1).apply(entity2);
    }

    public boolean unequalOperator(Entity entity1, Entity entity2) {
        return !equalsOperator.apply(entity1).apply(entity2);
    }
}
