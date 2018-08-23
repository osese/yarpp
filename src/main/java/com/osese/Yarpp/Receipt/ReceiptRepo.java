package com.osese.Yarpp.Receipt;

import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReceiptRepo extends CrudRepository<Receipt, Long>{

	@Query("select r from Receipt r where endDate >= ?1 and endDate <= endDate")
	Iterable<Receipt> findFromTo(Date startDate, Date endDate);

}
