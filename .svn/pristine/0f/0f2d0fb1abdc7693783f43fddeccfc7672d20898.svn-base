
package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.Offer;

@Component
@Transactional
public class OfferToStringConverter implements Converter<Offer, String> {

	@Override
	public String convert(final Offer offer) {

		String result;

		if (offer == null)
			result = null;
		else
			result = String.valueOf(offer.getId());
		return result;
	}

}
