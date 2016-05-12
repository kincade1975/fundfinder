package hr.betaware.fundfinder.jpa.domain;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractEntity {

	@Column(name = "creation_date", nullable = false)
	@CreatedDate
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@Getter @Setter
	private DateTime creationDate;

	@Column(name = "created_by", nullable = true, columnDefinition = "varchar(128)")
	@CreatedBy
	@Getter @Setter
	private String createdBy;

	@Column(name = "last_modified_date", nullable = false)
	@LastModifiedDate
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@Getter @Setter
	private DateTime lastModifiedDate;

	@Column(name = "last_modified_by", nullable = true, columnDefinition = "varchar(128)")
	@LastModifiedBy
	@Getter @Setter
	private String lastModifiedBy;

}
