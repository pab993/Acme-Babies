
package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.Kindergarten;

@Component
@Transactional
public class KindergartenToStringConverter implements Converter<Kindergarten, String> {

	@Override
	public String convert(final Kindergarten kindergarten) {

		String result;

		if (kindergarten == null)
			result = null;
		else
			result = String.valueOf(kindergarten.getId());
		return result;
	}

}
