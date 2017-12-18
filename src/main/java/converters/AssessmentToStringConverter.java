
package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.Assessment;

@Component
@Transactional
public class AssessmentToStringConverter implements Converter<Assessment, String> {

	@Override
	public String convert(final Assessment comment) {

		String result;

		if (comment == null)
			result = null;
		else
			result = String.valueOf(comment.getId());
		return result;
	}

}
