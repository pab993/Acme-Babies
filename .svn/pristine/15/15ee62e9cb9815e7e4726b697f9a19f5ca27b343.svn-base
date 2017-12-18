
package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.Caretaker;

@Component
@Transactional
public class CaretakerToStringConverter implements Converter<Caretaker, String> {

	@Override
	public String convert(final Caretaker caretaker) {

		String result;

		if (caretaker == null)
			result = null;
		else
			result = String.valueOf(caretaker.getId());
		return result;
	}

}
