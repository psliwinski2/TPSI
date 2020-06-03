package wizut.tpsi.ogloszenia.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import wizut.tpsi.ogloszenia.jpa.BodyStyle;
import wizut.tpsi.ogloszenia.jpa.CarManufacturer;
import wizut.tpsi.ogloszenia.jpa.CarModel;
import wizut.tpsi.ogloszenia.jpa.FuelType;
import wizut.tpsi.ogloszenia.jpa.Offer;
import wizut.tpsi.ogloszenia.web.OfferFilter;

@Service
@Transactional
public class OffersService {
	@PersistenceContext
	private EntityManager em;

	public Offer createOffer(Offer offer) {
		em.persist(offer);
		return offer;
	}

	// public CarModel getModel(int id) {
	// return em.find(CarModel.class, id);
	// }

	public List<CarManufacturer> getCarManufacturers() {
		String jpql = "select cm from CarManufacturer cm order by cm.name";
		TypedQuery<CarManufacturer> query = em.createQuery(jpql, CarManufacturer.class);
		List<CarManufacturer> result = query.getResultList();
		return result;
	}

	public List<BodyStyle> getBodyStyles() {
		String jpql = "select bs from BodyStyle bs order by bs.name";
		TypedQuery<BodyStyle> query = em.createQuery(jpql, BodyStyle.class);
		List<BodyStyle> result = query.getResultList();
		return result;
	}

	public List<FuelType> getFuelTypes() {
		String jpql = "select ft from FuelType ft order by ft.name";
		TypedQuery<FuelType> query = em.createQuery(jpql, FuelType.class);
		List<FuelType> result = query.getResultList();
		return result;
	}

	public List<CarModel> getCarModels() {
		String jpql = "select cm from CarModel cm order by cm.name";
		TypedQuery<CarModel> query = em.createQuery(jpql, CarModel.class);
		List<CarModel> result = query.getResultList();
		return result;
	}

	public List<CarModel> getCarModels(int manufacturerId) {
		String jpql = "select cm from CarModel cm where cm.manufacturer.id = :id order by cm.name";
		TypedQuery<CarModel> query = em.createQuery(jpql, CarModel.class);
		query.setParameter("id", manufacturerId);
		return query.getResultList();
	}

	public List<Offer> getOffers() {
		String jpql = "select off from Offer off order by off.title";
		TypedQuery<Offer> query = em.createQuery(jpql, Offer.class);
		List<Offer> result = query.getResultList();
		return result;
	}

	public List<Offer> getOffersByModel(int modelId) {
		String jpql = "select off from Offer off where off.model.id  = :id order by off.title";
		TypedQuery<Offer> query = em.createQuery(jpql, Offer.class);
		query.setParameter("id", modelId);
		return query.getResultList();
	}

	public List<Offer> getOffersByManufacturer(int manufacturerId) {
		String jpql = "select off from Offer off where off.model.manufacturer.id  = :id order by off.title";
		TypedQuery<Offer> query = em.createQuery(jpql, Offer.class);
		query.setParameter("id", manufacturerId);
		return query.getResultList();
	}

	public List<Offer> getOffer(int offerId) {
		String jpql = "select off from Offer off where off.id = :id";
		TypedQuery<Offer> query = em.createQuery(jpql, Offer.class);
		query.setParameter("id", offerId);
		query.getResultList().get(0);
		return query.getResultList();

	}

	public List<Integer> getYears() {
		String jpql = "select distinct off.year from Offer off";
		TypedQuery<Integer> query = em.createQuery(jpql, Integer.class);
		List<Integer> result = query.getResultList();
		return result;
	}

	public List<Offer> getOffers(OfferFilter filter) {
		String jpql = "select off from Offer off where ";
		boolean hasFilter = false;
		if (filter.getManufacturerId() != null) {
			jpql += "off.model.manufacturer.id = :manufId";
			hasFilter = true;
		}
		if (filter.getModelId() != null) {
			if (hasFilter) {
				jpql += " and ";
			}
			jpql += "off.model.id  = :modelId";
			hasFilter = true;
		}
		if (filter.getFuelTypeId() != null) {
			if (hasFilter) {
				jpql += " and ";
			}
			jpql += "off.fuelType.id = :fuelId";
			hasFilter = true;
		}
		if (filter.getYearMax() != null) {
			if (hasFilter) {
				jpql += " and ";
			}
			jpql += "off.year <= :yearMax";
			hasFilter = true;
		}
		if (filter.getYearMin() != null) {
			if (hasFilter) {
				jpql += " and ";
			}
			jpql += "off.year >= :yearMin";
			hasFilter = true;
		}
		if (filter.getDesc() != null) {
			if (hasFilter) {
				jpql += " and ";
			}
			jpql += "off.description like :desc";
			hasFilter = true;
		}
		if (!hasFilter) {
			jpql = "select off from Offer off order by off.title";
			TypedQuery<Offer> query = em.createQuery(jpql, Offer.class);
			return query.getResultList();
		}

		TypedQuery<Offer> query = em.createQuery(jpql, Offer.class);
		if (filter.getManufacturerId() != null) {
			query.setParameter("manufId", filter.getManufacturerId());
		}
		if (filter.getModelId() != null) {
			query.setParameter("modelId", filter.getModelId());
		}
		if (filter.getFuelTypeId() != null) {
			query.setParameter("fuelId", filter.getFuelTypeId());
		}
		if (filter.getYearMax() != null) {
			query.setParameter("yearMax", filter.getYearMax());
		}
		if (filter.getYearMin() != null) {
			query.setParameter("yearMin", filter.getYearMin());
		}
		if (filter.getDesc() != null) {
			query.setParameter("desc", "%" + filter.getDesc() + "%");
		}

		return query.getResultList();
	}

	public Offer deleteOffer(Integer id) {
		Offer offer = em.find(Offer.class, id);
		em.remove(offer);
		return offer;
	}

	public Offer saveOffer(Offer offer) {
		return em.merge(offer);
	}

}