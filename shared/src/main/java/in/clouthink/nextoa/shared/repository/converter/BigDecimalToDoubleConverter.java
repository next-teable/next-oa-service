package in.clouthink.nextoa.shared.repository.converter;

import org.springframework.core.convert.converter.Converter;

import java.math.BigDecimal;

/**
 * @author dz
 */
public class BigDecimalToDoubleConverter implements Converter<BigDecimal,Double> {

	@Override
	public Double convert(BigDecimal source) {
		return source.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

}
