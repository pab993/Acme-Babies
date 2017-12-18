
package converters;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import repositories.CaretakerRepository;
import domain.Caretaker;

@Component
@Transactional
public class StringToCaretakerConverter implements Converter<String, Caretaker> {

	@Autowired
	CaretakerRepository	caretakerRepository;


	@Override
	public Caretaker convert(final String text) {
		Caretaker result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.caretakerRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}
}
