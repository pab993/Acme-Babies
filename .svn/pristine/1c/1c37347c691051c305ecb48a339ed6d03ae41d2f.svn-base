
package converters;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import repositories.ComentableRepository;
import domain.Comentable;

@Component
@Transactional
public class StringToComentableConverter implements Converter<String, Comentable> {

	@Autowired
	ComentableRepository	comentableRepository;


	@Override
	public Comentable convert(final String text) {
		Comentable result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.comentableRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}
}
