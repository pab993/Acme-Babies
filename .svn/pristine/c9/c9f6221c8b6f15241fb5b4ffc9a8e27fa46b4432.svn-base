
package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.Shift;

@Component
@Transactional
public class ShiftToStringConverter implements Converter<Shift, String> {

	@Override
	public String convert(final Shift shift) {

		String result;
		if (shift == null)
			result = null;
		else
			result = String.valueOf(shift.getId());
		return result;
	}

}
