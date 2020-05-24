package in.clouthink.nextoa.bl.model;

/**
 * The paper transition request (from -> to)
 */
public class PaperTransitionRequest {

	private PaperAction fromAction;

	private PaperAction toAction;

	public PaperTransitionRequest(PaperAction fromAction, PaperAction toAction) {
		this.fromAction = fromAction;
		this.toAction = toAction;
	}

	public PaperAction getFromAction() {
		return fromAction;
	}

	public PaperAction getToAction() {
		return toAction;
	}

}
