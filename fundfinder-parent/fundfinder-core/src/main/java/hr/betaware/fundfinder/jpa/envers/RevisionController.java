package hr.betaware.fundfinder.jpa.envers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = { "/api/v1/revisions", "/e/api/v1/revisions" })
public class RevisionController {

	@Autowired
	private RevisionService revisionService;

	@RequestMapping(method = RequestMethod.POST)
	public List<RevisionResource> getRevisions(@RequestBody RevisionResource resource) {
		try {
			return revisionService.getRevisions(Class.forName(resource.getClazz()), resource.getEntityId());
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

}
