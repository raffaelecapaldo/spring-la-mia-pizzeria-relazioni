package org.java.app.business.db.serv;

import org.java.app.business.db.pojo.SpecialOffer;
import org.java.app.business.db.repo.SpecialOfferRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpecialOfferService {
	
	@Autowired
	private SpecialOfferRepo specialOfferRepo;
	
	public void save(SpecialOffer specialOffer) {
		specialOfferRepo.save(specialOffer);
	}
	
	public SpecialOffer findById(int id) {
		return specialOfferRepo.findById(id).get();
	}
	
	public void delete(SpecialOffer specialOffer) {
		specialOfferRepo.delete(specialOffer);
	}

}
