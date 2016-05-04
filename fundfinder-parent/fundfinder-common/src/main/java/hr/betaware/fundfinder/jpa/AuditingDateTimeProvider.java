package hr.betaware.fundfinder.jpa;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.auditing.DateTimeProvider;

public class AuditingDateTimeProvider implements DateTimeProvider {

	private Calendar calendar;

	@Autowired
	public AuditingDateTimeProvider() {
		calendar = GregorianCalendar.getInstance();
	}

	@Override
	public Calendar getNow() {
		return calendar;
	}

}
