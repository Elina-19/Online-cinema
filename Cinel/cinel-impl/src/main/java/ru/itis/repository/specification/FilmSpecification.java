package ru.itis.repository.specification;

import org.springframework.data.jpa.domain.Specification;
import ru.itis.model.Film;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;


public class FilmSpecification implements Specification<Film>{

    private List<SearchCriteria> list;

    public FilmSpecification() {
        this.list = new ArrayList<>();
    }

    public void add(SearchCriteria criteria) {
        list.add(criteria);
    }

    @Override
    public Predicate toPredicate(Root<Film> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        List<Predicate> predicates = new ArrayList<>();

        for (SearchCriteria criteria : list) {

            if (criteria.getOperation().equals(SearchOperation.EQUAL)) {
                predicates.add(builder.equal(
                        root.get(criteria.getKey()), criteria.getValue()));

            } else if (criteria.getOperation().equals(SearchOperation.LIKE)) {
                predicates.add(builder.like(
                        builder.lower(root.get(criteria.getKey())),
                        "%" + criteria.getValue().toString().toLowerCase() + "%"));

            } else if (criteria.getOperation().equals(SearchOperation.IN)) {
                predicates.add(builder.in(
                        root.join(criteria.getKey()))
                        .value(criteria.getValue()));
            }
        }

        return builder.and(predicates.toArray(new Predicate[0]));
    }
}
