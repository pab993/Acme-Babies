
package converters;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import repositories.KindergartenRepository;
import domain.Kindergarten;

@Component
@Transactional
public class StringToKindergartenConverter implements Converter<String, Kindergarten> {

	@Autowired
	KindergartenRepository	kindergartenRepository;


	@Override
	public Kindergarten convert(final String text) {
		Kindergarten result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.kindergartenRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}
}
