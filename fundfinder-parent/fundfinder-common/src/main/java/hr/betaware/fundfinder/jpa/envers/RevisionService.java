package hr.betaware.fundfinder.jpa.envers;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.RevisionType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hr.betaware.fundfinder.service.BaseService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RevisionService extends BaseService {

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<RevisionResource> getRevisions(Class<?> clazz, Integer id) {
		List<RevisionResource> result = new ArrayList<>();
		log.debug("Getting revisions for entity [{}] with ID [{}]", clazz.getSimpleName(), id);
		List<Object[]> objects = AuditReaderFactory.get(entityManager).createQuery().forRevisionsOfEntity(clazz, false, true).getResultList();
		for (Object[] object : objects) {
			RevisionResource resource = new RevisionResource();
			resource.setEntity(object[0]);
			Revision revision = (Revision) object[1];
			resource.setId(revision.getId());
			resource.setCreationDate(revision.getCreationDate());
			resource.setCreatedBy(revision.getCreatedBy());
			resource.setType(RevisionType.valueOf(object[2].toString()));
			result.add(resource);
		}
		return result;
	}

}
