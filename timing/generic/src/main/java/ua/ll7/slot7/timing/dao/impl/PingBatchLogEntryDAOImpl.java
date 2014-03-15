package ua.ll7.slot7.timing.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.ll7.slot7.timing.dao.IPingBatchLogEntryDAO;
import ua.ll7.slot7.timing.model.PingBatchLogEntry;

import java.util.List;

/**
 * @author Oleksandr Velychko
 *         20.12.13 : 18:28
 */
@Repository("PingBatchLogEntryDAO")
@Transactional(propagation = Propagation.REQUIRED)
public class PingBatchLogEntryDAOImpl implements IPingBatchLogEntryDAO {
	private static Logger LOGGER = Logger.getLogger(PingBatchLogEntryDAOImpl.class);

	@Qualifier("sessionFactory")
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void createPingBatchLogEntry(final PingBatchLogEntry pingBatchLogEntry) {
		Session session = sessionFactory.getCurrentSession();
		session.save(pingBatchLogEntry);
	}

	@Override
	public PingBatchLogEntry getByID(final long batchId) {
		Session session = sessionFactory.getCurrentSession();
		PingBatchLogEntry pingBatchLogEntry;

		String selectString =
			"select pingBatchLogEntry " +
				"from PingBatchLogEntry pingBatchLogEntry " +
				"where pingBatchLogEntry.id = :id";

		Query query = session.createQuery(selectString);
		query.setParameter("id", batchId);
		pingBatchLogEntry = (PingBatchLogEntry) query.uniqueResult();
		return pingBatchLogEntry;
	}

	@Override
	public PingBatchLogEntry getByUUID(final String uuid) {
		Session session = sessionFactory.getCurrentSession();
		PingBatchLogEntry pingBatchLogEntry;

		String selectString =
			"select pingBatchLogEntry " +
				"from PingBatchLogEntry pingBatchLogEntry " +
				"where pingBatchLogEntry.pingBatchUUID = :uuid";

		Query query = session.createQuery(selectString);
		query.setParameter("uuid", uuid);
		pingBatchLogEntry = (PingBatchLogEntry) query.uniqueResult();
		return pingBatchLogEntry;
	}

	@Override
	public List<String> getUUIDs() {
		Session session = sessionFactory.getCurrentSession();
		List<String> resultList = null;

		String selectString = "select pingBatchLogEntry.pingBatchUUID as uuid " +
			"from PingBatchLogEntry as pingBatchLogEntry " +
			"order by uuid asc";

		Query query = session.createQuery(selectString);
		resultList = query.list();
		return resultList;
	}

	@Override
	public List<String> getCustomIDs() {
		Session session = sessionFactory.getCurrentSession();
		List<String> resultList = null;

		String selectString = "select pingBatchLogEntry.pingBatchCustomID as customID " +
			"from PingBatchLogEntry as pingBatchLogEntry " +
			"order by customID asc";

		Query query = session.createQuery(selectString);
		resultList = query.list();
		return resultList;
	}

	@Override
	public Long getCustomIDsCount(String customIDToCount) {
		Session session = sessionFactory.getCurrentSession();
		Long result;

		String selectString =
			"select count(pingBatchLogEntry.pingBatchCustomID) " +
				"from PingBatchLogEntry pingBatchLogEntry " +
				"where pingBatchLogEntry.pingBatchUUID = :iDToCount " +
				"group by pingBatchLogEntry.pingBatchCustomID";

		Query query = session.createQuery(selectString);
		query.setParameter("iDToCount", customIDToCount);

		result = (Long) query.uniqueResult();
		return result;
	}

	@Override
	public List<Long> getIDList(final String customIDToSearch) {
		Session session = sessionFactory.getCurrentSession();
		List<Long> resultList = null;

		String selectString = "select pingBatchLogEntry.id as id " +
			"from PingBatchLogEntry as pingBatchLogEntry " +
			"where id = :cid " +
			"order by id asc";

		Query query = session.createQuery(selectString);
		query.setParameter("cid", customIDToSearch);
		resultList = query.list();
		return resultList;
	}


}
