package in.clouthink.nextoa.bl.openapi.support;

import in.clouthink.nextoa.bl.model.User;
import in.clouthink.nextoa.bl.openapi.dto.*;
import in.clouthink.nextoa.shared.domain.params.PageQueryParameter;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 *
 */
public interface SysPaperRestSupport {

	Page<PaperSummary> listAllPapers(PaperQueryParameter queryRequest);

	PaperDetail getPaperDetail(String id, User user);

	List<String> getPaperAllowedActions(String id, User user);

	Page<PaperReadSummary> getPaperReadHistory(String id, PaperActionQueryParameter queryRequest);

	Page<PaperPrintSummary> getPaperPrintHistory(String id, PaperActionQueryParameter queryRequest);

	Page<PaperTransitionSummary> getPaperTransitionHistory(String id, PaperActionQueryParameter queryRequest);

	Page<PaperProcessSummary> getPaperProcessHistory(String id, PaperActionQueryParameter queryRequest);

	List<PaperProcessSummary> getPaperProcessHistory(String id);

	Page<PaperMessageSummary> getPaperMessages(String id, PageQueryParameter queryRequest);

	void terminatePaper(String id, User user);
}
