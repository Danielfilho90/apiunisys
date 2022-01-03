package com.daniel.unisystest.repository.specification;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.daniel.unisystest.entity.Job;
import com.daniel.unisystest.entity.Job_;

public class JobEspecification {

	private JobEspecification() {
	}

	public static Specification<Job> findAll(String name) {
		return (root, criteriaQuery, criteriaBuilder) -> {

			Collection<Predicate> predicates = new ArrayList<>();

			if (StringUtils.hasText(name)) {
				predicates.add(criteriaBuilder.equal(root.get(Job_.NAME), name));
			}

			return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));

		};
	}

}
