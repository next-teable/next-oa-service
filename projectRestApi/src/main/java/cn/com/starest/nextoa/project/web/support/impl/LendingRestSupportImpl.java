package cn.com.starest.nextoa.project.web.support.impl;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.project.domain.model.Lending;
import cn.com.starest.nextoa.project.domain.model.LendingAggregation;
import cn.com.starest.nextoa.project.domain.model.LendingObject;
import cn.com.starest.nextoa.project.domain.model.ModuleActions;
import cn.com.starest.nextoa.project.domain.request.SaveLendingRequest;
import cn.com.starest.nextoa.project.service.LendingService;
import cn.com.starest.nextoa.project.web.dto.*;
import cn.com.starest.nextoa.project.web.support.LendingRestSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * @author dz
 */
@Component
public class LendingRestSupportImpl implements LendingRestSupport {
    
    @Autowired
    private LendingService lendingService;
    
    @Override
    public ModuleActions.ModuleAction[] resolveGrantedActions(User user) {
        return lendingService.resolveGrantedActions(user);
    }
    
    @Override
    public LendingSummary createLending(SaveLendingRequest request, User byWho) {
        Lending lending = lendingService.createLending(request, byWho);
        LendingSummary summary = LendingSummary.from(lending);
        summary.setGrantedActions(lendingService.resolveGrantedActions(lending,
                                                                       byWho));
        return summary;
    }
    
    @Override
    public LendingSummary updateLending(String id,
                                        SaveLendingRequest request,
                                        User byWho) {
        Lending lending = lendingService.updateLending(id, request, byWho);
        LendingSummary summary = LendingSummary.from(lending);
        summary.setGrantedActions(lendingService.resolveGrantedActions(lending,
                                                                       byWho));
        return summary;
    }
    
    @Override
    public LendingDetail findLendingById(String id, User byWho) {
        Lending lending = lendingService.findLendingById(id, byWho);
        LendingDetail summary = LendingDetail.from(lending);
        summary.setGrantedActions(lendingService.resolveGrantedActions(lending,
                                                                       byWho));
        return summary;
    }
    
    @Override
    public Page<LendingSummary> listLendings(LendingQueryParameter request,
                                             User byWho) {
        Page<Lending> lendingPage = lendingService.listLending(request, byWho);
        return new PermissionAwarePageImpl<>(lendingPage.getContent()
                                                        .stream()
                                                        .map(item -> {
                                                            LendingSummary summary = LendingSummary.from(item);
                                                            summary.setGrantedActions(lendingService.resolveGrantedActions(item,
                                                                                                                           byWho));
                                                            return summary;
                                                        })
                                                        .collect(Collectors.toList()),
                                             new PageRequest(request.getStart(),
                                                             request.getLimit()),
                                             lendingPage.getTotalElements(),
                                             lendingService.resolveGrantedActions(byWho));
    }
    
    @Override
    public void deleteLendingById(String id, User user) {
        lendingService.deleteLendingById(id, user);
    }
    
    @Override
    public Page<LendingAggregationSummary> listLendingAggregationBySubContractor(LendingAggregationQueryParameter request,
                                                                                 User user) {
        request.setLendingObject(LendingObject.COMPANY);
        Page<LendingAggregation> lendingAggregationPage = lendingService.listLendingAggregations(request,
                                                                                                 user);
        return new PageImpl<>(lendingAggregationPage.getContent()
                                                    .stream()
                                                    .map(LendingAggregationSummary::from)
                                                    .collect(Collectors.toList()),
                              new PageRequest(request.getStart(),
                                              request.getLimit()),
                              lendingAggregationPage.getTotalElements());
    }
    
    @Override
    public Page<LendingAggregationSummary> listLendingAggregationByUser(LendingAggregationQueryParameter request,
                                                                        User user) {
        request.setLendingObject(LendingObject.PERSONAL);
        Page<LendingAggregation> lendingAggregationPage = lendingService.listLendingAggregations(request,
                                                                                                 user);
        return new PageImpl<>(lendingAggregationPage.getContent()
                                                    .stream()
                                                    .map(LendingAggregationSummary::from)
                                                    .collect(Collectors.toList()),
                              new PageRequest(request.getStart(),
                                              request.getLimit()),
                              lendingAggregationPage.getTotalElements());
    }
    
    @Override
    public Page<LendingSummary> listLendingBySubContractor(String id,
                                                           LendingQueryParameter request,
                                                           User byWho) {
        request.setLendingToId(id);
        request.setLendingById(null);
        request.setLendingObject(LendingObject.COMPANY);
        Page<Lending> lendingPage = lendingService.listLending(request, byWho);
        return new PermissionAwarePageImpl<>(lendingPage.getContent()
                                                        .stream()
                                                        .map(item -> {
                                                            LendingSummary summary = LendingSummary.from(item);
                                                            summary.setGrantedActions(lendingService.resolveGrantedActions(item,
                                                                                                                           byWho));
                                                            return summary;
                                                        })
                                                        .collect(Collectors.toList()),
                                             new PageRequest(request.getStart(),
                                                             request.getLimit()),
                                             lendingPage.getTotalElements(),
                                             lendingService.resolveGrantedActions(byWho));
    }
    
    @Override
    public Page<LendingSummary> listLendingByUser(String id,
                                                  LendingQueryParameter request,
                                                  User byWho) {
        request.setLendingById(id);
        request.setLendingToId(null);
        request.setLendingObject(LendingObject.PERSONAL);
        Page<Lending> lendingPage = lendingService.listLending(request, byWho);
        return new PermissionAwarePageImpl<>(lendingPage.getContent()
                                                        .stream()
                                                        .map(item -> {
                                                            LendingSummary summary = LendingSummary.from(item);
                                                            summary.setGrantedActions(lendingService.resolveGrantedActions(item,
                                                                                                                           byWho));
                                                            return summary;
                                                        })
                                                        .collect(Collectors.toList()),
                                             new PageRequest(request.getStart(),
                                                             request.getLimit()),
                                             lendingPage.getTotalElements(),
                                             lendingService.resolveGrantedActions(byWho));
    }
}
