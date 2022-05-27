package ru.itis.filter;

import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@UtilityClass
public class Specifications {

    public static <T> Specification<T> equal(String field, Object value) {
        return (root, query, builder) ->
                builder.equal(root.get(field), value);
    }

    public static <T> Specification<T> like(String field, String value) {
        return (root, query, builder) ->
                builder.like(
                        builder.lower(root.get(field)),
                        "%" + value.toLowerCase() + "%");
    }

    public static <T> Specification<T> in(JoinType joinType, String path, Object value) {
        return (root, query, builder) ->
                builder.in(Objects.requireNonNull(getPath(root, joinType, path)))
                        .value((T) value);
    }

    public static <T> Path<T> getPath(Root<T> root, JoinType joinType, String path) {
        List<String> attributes = Arrays.asList(path.split("\\."));
        List<String> attributesToJoin = attributes.subList(0, attributes.size() - 1);
        String searchField = attributes.get(attributes.size() - 1);
        if (!attributesToJoin.isEmpty()) {
            Join join = null;
            for (String attribute : attributesToJoin) {
                join = Objects.requireNonNullElse(join, root).join(attribute, joinType);
            }
            return join != null ? join.get(searchField) : null;
        } else {
            return root.get(searchField);
        }
    }
}
