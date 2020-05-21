package cn.com.starest.nextoa.project.domain.model;

/**
 * @author dz
 */
public class ModuleActions {

	public static class ModuleAction {

		private String code;

		private String name;

		public ModuleAction(String code, String name) {
			this.code = code;
			this.name = name;
		}

		public String getCode() {
			return code;
		}

		public String getName() {
			return name;
		}
	}

	public static final ModuleAction CREATE_ACTION = new ModuleAction(ModuleActions.CREATE, "新增");
	public static final ModuleAction EDIT_ACTION = new ModuleAction(ModuleActions.EDIT, "修改");
	public static final ModuleAction DELETE_ACTION = new ModuleAction(ModuleActions.DELETE, "删除");
	public static final ModuleAction PUBLISH_ACTION = new ModuleAction(ModuleActions.PUBLISH, "发布");
	public static final ModuleAction UNPUBLISH_ACTION = new ModuleAction(ModuleActions.UNPUBLISH, "取消发布");
	public static final ModuleAction CLOSE_PROJECT_ACTION = new ModuleAction(ModuleActions.CLOSE_PROJECT, "结项");
	public static final ModuleAction PROFIT_ACTION = new ModuleAction(ModuleActions.PROFIT, "提成比例");
	public static final ModuleAction SETTLE_ACTION = new ModuleAction(ModuleActions.SETTLE, "结算");
	public static final ModuleAction IMPORT_ACTION = new ModuleAction(ModuleActions.IMPORT, "导入");
	public static final ModuleAction RESORT_ACTION = new ModuleAction(ModuleActions.RESORT, "计算排序");
	public static final ModuleAction REPORT_ACTION = new ModuleAction(ModuleActions.REPORT, "统计报表");

	public static final String CREATE = "CREATE";
	public static final String EDIT = "EDIT";
	public static final String DELETE = "DELETE";
	public static final String PUBLISH = "PUBLISH";
	public static final String UNPUBLISH = "UNPUBLISH";
	public static final String CLOSE_PROJECT = "CLOSE_PROJECT";
	public static final String PROFIT = "PROFIT";
	public static final String SETTLE = "SETTLE";
	public static final String IMPORT = "IMPORT";
	public static final String RESORT = "RESORT";
	public static final String REPORT = "REPORT";

	public static final ModuleAction[] EMPTY_ACTIONS = new ModuleAction[0];

	public static ModuleAction parseModuleAction(String moduleActionCode) {
		switch (moduleActionCode) {
			case CREATE:
				return CREATE_ACTION;
			case EDIT:
				return EDIT_ACTION;
			case DELETE:
				return DELETE_ACTION;
			case PUBLISH:
				return PUBLISH_ACTION;
			case UNPUBLISH:
				return UNPUBLISH_ACTION;
			case CLOSE_PROJECT:
				return CLOSE_PROJECT_ACTION;
			case PROFIT:
				return PROFIT_ACTION;
			case SETTLE:
				return SETTLE_ACTION;
			case IMPORT:
				return IMPORT_ACTION;
			case RESORT:
				return RESORT_ACTION;
			case REPORT:
				return REPORT_ACTION;
		}
		return null;
	}

	public static ModuleAction[] listAvailableActions(Module feature) {
		switch (feature) {
			case PROJECT:
				return new ModuleAction[]{CREATE_ACTION,
										  EDIT_ACTION,
										  PUBLISH_ACTION,
										  DELETE_ACTION,
										  CLOSE_PROJECT_ACTION};
			case CONTRACT:
				return new ModuleAction[]{CREATE_ACTION, EDIT_ACTION, PUBLISH_ACTION, UNPUBLISH_ACTION, DELETE_ACTION};
			case SUBCONTRACT:
				return new ModuleAction[]{CREATE_ACTION, EDIT_ACTION, PUBLISH_ACTION, UNPUBLISH_ACTION, DELETE_ACTION};
			case ORDER:
				return new ModuleAction[]{CREATE_ACTION, EDIT_ACTION, PUBLISH_ACTION, UNPUBLISH_ACTION, DELETE_ACTION};
			case SUBORDER:
				return new ModuleAction[]{CREATE_ACTION, EDIT_ACTION, PUBLISH_ACTION, UNPUBLISH_ACTION, DELETE_ACTION};
			case PROJECT_COMPLETION:
				return new ModuleAction[]{CREATE_ACTION, EDIT_ACTION, DELETE_ACTION};
			case PROJECT_SETTLEMENT:
				return new ModuleAction[]{PROFIT_ACTION, SETTLE_ACTION, DELETE_ACTION};
			case LICENSE:
				return new ModuleAction[]{CREATE_ACTION, EDIT_ACTION, DELETE_ACTION};
			case ISSUE_INVOICE:
				return new ModuleAction[]{CREATE_ACTION, EDIT_ACTION, DELETE_ACTION};
			case RECEIVE_INVOICE:
				return new ModuleAction[]{CREATE_ACTION, EDIT_ACTION, DELETE_ACTION};
			case REIMBURSE:
				return new ModuleAction[]{SETTLE_ACTION};
			case REIMBURSE_DEVOPS:
				return new ModuleAction[]{EDIT_ACTION};
			case DEPOSIT:
				return new ModuleAction[]{CREATE_ACTION, EDIT_ACTION, DELETE_ACTION};
			case PAYMENT:
				return new ModuleAction[]{CREATE_ACTION, EDIT_ACTION, DELETE_ACTION};
			case PENDING_PAYMENT:
				return new ModuleAction[]{CREATE_ACTION, EDIT_ACTION, DELETE_ACTION};
			case PROJECT_RECEIVED_PAYMENT:
				return new ModuleAction[]{CREATE_ACTION, EDIT_ACTION, DELETE_ACTION};
			case COMPANY_RECEIVED_PAYMENT:
				return new ModuleAction[]{CREATE_ACTION, EDIT_ACTION, DELETE_ACTION};
			case LENDING:
				return new ModuleAction[]{CREATE_ACTION, EDIT_ACTION, DELETE_ACTION};
			case SALARY:
				return new ModuleAction[]{DELETE_ACTION, IMPORT_ACTION};
			case COMPANY_CAPITAL:
				return new ModuleAction[]{CREATE_ACTION, EDIT_ACTION, DELETE_ACTION};
		}

		return EMPTY_ACTIONS;
	}

}
