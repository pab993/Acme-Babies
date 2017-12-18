
package converters;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import repositories.ShiftRepository;
import domain.Shift;

@Component
@Transactional
public class StringToShiftConverter implements Converter<String, Shift> {

	@Autowired
	ShiftRepository	shiftRepository;


	@Override
	public Shift convert(final String text) {
		Shift result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.shiftRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}

}
