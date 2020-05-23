package in.clouthink.nextoa.shared.util;

import in.clouthink.nextoa.shared.util.impl.BaiduShortUrlGenerator;
import in.clouthink.nextoa.shared.util.impl.DefaultShortUrlGenerator;
import in.clouthink.nextoa.shared.util.impl.ShortUrlGenerator;
import in.clouthink.nextoa.shared.util.impl.SinaShortUrlGenerator;

/**
 * @author dz
 */
public class ShortenUrlUtils {

	private static ShortUrlGenerator NEXTOA_SHORT_URL_GENERATOR = new DefaultShortUrlGenerator();

	private static ShortUrlGenerator SINA_SHORT_URL_GENERATOR = new SinaShortUrlGenerator();

	private static ShortUrlGenerator BAIDU_SHORT_URL_GENERATOR = new BaiduShortUrlGenerator();

	public static String nextoa(String url) {
		return NEXTOA_SHORT_URL_GENERATOR.shorten(url);
	}

	public static String baidu(String url) {
		return BAIDU_SHORT_URL_GENERATOR.shorten(url);
	}

	public static String sina(String url) {
		return SINA_SHORT_URL_GENERATOR.shorten(url);
	}

}
