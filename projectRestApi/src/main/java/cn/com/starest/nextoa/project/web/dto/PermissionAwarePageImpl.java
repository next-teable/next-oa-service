package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.project.domain.model.ModuleActions;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author dz
 */
public class PermissionAwarePageImpl<T> extends ExtendedPageImpl<T> {

	private ModuleActions.ModuleAction[] grantedActions;

	public PermissionAwarePageImpl(List<T> content,
								   Pageable pageable,
								   long total,
								   ModuleActions.ModuleAction[] grantedActions) {
		super(content, pageable, total);
		this.grantedActions = grantedActions;
	}

	public PermissionAwarePageImpl(List<T> content,
								   Pageable pageable,
								   long total,
								   List<ModuleActions.ModuleAction> grantedActions) {
		super(content, pageable, total);
		this.grantedActions = grantedActions.toArray(ModuleActions.EMPTY_ACTIONS);
	}

	public PermissionAwarePageImpl(List<T> content,
								   Pageable pageable,
								   long total,
								   Object extension,
								   ModuleActions.ModuleAction[] grantedActions) {
		super(content, pageable, total, extension);
		this.grantedActions = grantedActions;
	}

	public PermissionAwarePageImpl(List<T> content,
								   Pageable pageable,
								   long total,
								   Object extension,
								   List<ModuleActions.ModuleAction> grantedActions) {
		super(content, pageable, total, extension);
		this.grantedActions = grantedActions.toArray(ModuleActions.EMPTY_ACTIONS);
	}


	public ModuleActions.ModuleAction[] getGrantedActions() {
		return grantedActions;
	}

	public void setGrantedActions(ModuleActions.ModuleAction[] grantedActions) {
		this.grantedActions = grantedActions;
	}

}
