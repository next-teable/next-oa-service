package in.clouthink.nextoa.shared.repository.converter;

import org.springframework.core.convert.converter.Converter;

import java.math.BigDecimal;

/**
 * @author dz
 */
public class DoubleToBigDecimalConverter implements Converter<Double,BigDecimal> {

	@Override
	public BigDecimal convert(Double source) {
		return new BigDecimal(source).setScale(2, BigDecimal.ROUND_HALF_UP);
	}

}
