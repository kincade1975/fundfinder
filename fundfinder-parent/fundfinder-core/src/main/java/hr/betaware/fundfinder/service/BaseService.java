package hr.betaware.fundfinder.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Root;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import hr.betaware.fundfinder.uigrid.UiGridResource;
import hr.betaware.fundfinder.uigrid.UiGridSortResource;

public class BaseService {

	@PersistenceContext
	protected EntityManager entityManager;

	protected Pageable createPageable(UiGridResource resource) {
		if (resource.getSort() != null && !resource.getSort().isEmpty()) {
			return new PageRequest(resource.getPagination().getPage(), resource.getPagination().getSize(), createSort(resource));
		} else {
			return new PageRequest(resource.getPagination().getPage(), resource.getPagination().getSize());
		}
	}

	protected Sort createSort(UiGridResource resource) {
		List<Order> orders = new ArrayList<>();
		for (UiGridSortResource uiGridSortResource : resource.getSort()) {
			orders.add(new Order((uiGridSortResource.getDirection().equals("asc")) ? Direction.ASC : Direction.DESC, uiGridSortResource.getName()));
		}
		return new Sort(orders);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void bulkDelete(Class clazz, String where, Object object) {
		CriteriaDelete criteriaDelete = entityManager.getCriteriaBuilder().createCriteriaDelete(clazz);
		Root root = criteriaDelete.from(clazz);
		criteriaDelete.where(entityManager.getCriteriaBuilder().equal(root.get(where), object));
		entityManager.createQuery(criteriaDelete).executeUpdate();
	}

	protected List<Date> getDateRange(String date) {
		return Arrays.asList(
				DateTime.parse(date.substring(0, 10), DateTimeFormat.forPattern("yyyy.MM.dd")).withTime(0, 0, 0, 0).toDate(),
				DateTime.parse(date.substring(13, date.length()), DateTimeFormat.forPattern("yyyy.MM.dd")).withTime(23, 59, 59, 999).toDate());
	}

}
