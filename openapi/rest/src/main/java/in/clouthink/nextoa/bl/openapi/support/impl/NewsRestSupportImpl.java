package in.clouthink.nextoa.bl.openapi.support.impl;

import in.clouthink.nextoa.bl.model.News;
import in.clouthink.nextoa.bl.model.NewsReadHistory;
import in.clouthink.nextoa.bl.model.User;
import in.clouthink.nextoa.bl.openapi.dto.*;
import in.clouthink.nextoa.bl.openapi.support.NewsRestSupport;
import in.clouthink.nextoa.bl.service.NewsService;
import in.clouthink.nextoa.shared.domain.params.PageQueryParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

/**
 *
 */
@Service
public class NewsRestSupportImpl implements NewsRestSupport {

	@Autowired
	private NewsService newsService;

	@Override
	public Page<NewsSummary> listNews(NewsQueryParameter queryRequest) {
		Page<News> newsPage = newsService.listNews(queryRequest);
		return new PageImpl<>(newsPage.getContent().stream().map(NewsSummary::from).collect(Collectors.toList()),
							  new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
							  newsPage.getTotalElements());
	}

	@Override
	public NewsDetail getNewsDetail(String id) {
		return NewsDetail.from(newsService.findNewsById(id));
	}

	@Override
	public String createNews(SaveNewsParameter request, User user) {
		return newsService.createNews(request, user).getId();
	}

	@Override
	public void updateNews(String id, SaveNewsParameter request, User user) {
		newsService.updateNews(id, request, user);
	}

	@Override
	public void deleteNews(String id, User user) {
		newsService.deleteNews(id, user);
	}

	@Override
	public void publishNews(String id, User user) {
		newsService.publishNews(id, user);
	}

	@Override
	public void unpublishNews(String id, User user) {
		newsService.unpublishNews(id, user);
	}

	@Override
	public Page<ReadSummary> listReadHistory(String id, PageQueryParameter queryParameter) {
		Page<NewsReadHistory> readHistoryPage = newsService.listReadHistory(id, queryParameter);
		return new PageImpl<>(readHistoryPage.getContent().stream().map(ReadSummary::from).collect(Collectors.toList()),
							  new PageRequest(queryParameter.getStart(), queryParameter.getLimit()),
							  readHistoryPage.getTotalElements());
	}

    @Override
    public void deleteAttachment(String id, String fileId, User user) {
        newsService.deleteAttachment(id, fileId, user);
    }
}
