
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ComentableRepository;
import domain.Comentable;

@Transactional
@Service
public class ComentableService {

	//Repository
	//======================================================================

	@Autowired
	private ComentableRepository	comentableRepository;


	//Services
	// ======================================================================

	//CRUD methods
	//=======================================================================

	public Comentable findOneAux(final int comentableId) {
		Assert.isTrue(comentableId != 0);
		Comentable result;

		result = this.comentableRepository.findOneAux(comentableId);
		return result;
	}

	public Comentable findOne(final int comentableId) {
		Assert.isTrue(comentableId != 0);
		Comentable result;

		result = this.comentableRepository.findOne(comentableId);
		return result;
	}

	public Collection<Comentable> findAll() {
		Collection<Comentable> result;

		result = this.comentableRepository.findAll();

		return result;
	}

	//Other bussiness methods
	// ==================================================================

}
