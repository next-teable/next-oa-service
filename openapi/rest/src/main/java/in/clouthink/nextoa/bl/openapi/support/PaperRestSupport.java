package in.clouthink.nextoa.bl.openapi.support;

import in.clouthink.nextoa.bl.model.User;
import in.clouthink.nextoa.bl.openapi.dto.*;
import in.clouthink.nextoa.shared.domain.params.PageQueryParameter;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 *
 */
public interface PaperRestSupport {

	Page<PaperSummary> listAllPapers(PaperQueryParameter queryRequest, User user);

	Page<PaperSummary> listDraftPapers(PaperQueryParameter queryRequest, User user);

	Page<PaperSummary> listProcessingPapers(PaperQueryParameter queryRequest, User user);

	Page<PaperSummary> listRevokedPapers(PaperQueryParameter queryRequest, User user);

	long countOfAllPapers(PaperQueryParameter queryRequest, User user);

	long countOfDraftPapers(PaperQueryParameter queryRequest, User user);

	long countOfProcessingPapers(PaperQueryParameter queryRequest, User user);

	long countOfRevokedPapers(PaperQueryParameter queryRequest, User user);

	PaperDetail getPaperDetail(String id, User user);

	PaperDetail copyPaperDetail(String id, User user);

	List<String> getPaperAllowedActions(String id, User user);

	String createPaper(SavePaperParameter request, User user);

	void updatePaper(String id, SavePaperParameter request, User user);

	void deletePaper(String id, User user);

	void revokePaper(String id, User user);

	void startPaper(String id, StartPaperParameter request, User user);

	void replyPaper(String id, ReplyPaperParameter request, User user);

	void forwardPaper(String id, ForwardPaperParameter request, User user);

	void printPaper(String id, User user);

	void markPaperAsRead(String id, User user);

	void markPaperAsDone(String id, User user);

	Page<PaperReadSummary> getPaperReadHistory(String id, PaperActionQueryParameter queryRequest);

	Page<PaperPrintSummary> getPaperPrintHistory(String id, PaperActionQueryParameter queryRequest);

	Page<PaperTransitionSummary> getPaperTransitionHistory(String id, PaperActionQueryParameter queryRequest);

	Page<PaperTransitionSummary> getPaperEndHistory(String id, PaperActionQueryParameter queryRequest);

	Page<PaperProcessSummary> getPaperProcessHistory(String id, PaperActionQueryParameter queryRequest);

	List<PaperProcessSummary> getPaperProcessHistory(String id, User user);

	void deletePaperAttachment(String paperId, String fileId, User user);

	Page<PaperMessageSummary> getPaperMessages(String id, PageQueryParameter queryRequest);

}
