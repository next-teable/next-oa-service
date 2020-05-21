package cn.com.starest.nextoa.project.web;

import cn.com.starest.nextoa.project.domain.model.Module;

import java.lang.annotation.*;

/**
 * 模块授权注解（RestController方法级）
 *
 * @author dz
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ModuleAuthority {

	Module feature();

	String[] actions();

}
