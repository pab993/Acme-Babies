
package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.Invoice;

@Component
@Transactional
public class InvoiceToStringConverter implements Converter<Invoice, String> {

	@Override
	public String convert(final Invoice invoice) {

		String result;
		if (invoice == null)
			result = null;
		else
			result = String.valueOf(invoice.getId());
		return result;
	}

}
