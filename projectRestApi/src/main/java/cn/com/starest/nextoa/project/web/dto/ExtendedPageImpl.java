package cn.com.starest.nextoa.project.web.dto;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author dz
 */
public class ExtendedPageImpl<T> extends PageImpl<T> {

	private Object extension;

	public ExtendedPageImpl(List<T> content, Pageable pageable, long total) {
		super(content, pageable, total);
	}

	public ExtendedPageImpl(List<T> content, Pageable pageable, long total, Object extension) {
		super(content, pageable, total);
		this.extension = extension;
	}

	public Object getExtension() {
		return extension;
	}

	public void setExtension(Object extension) {
		this.extension = extension;
	}
}