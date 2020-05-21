package cn.com.starest.nextoa.project.service.impl;

import cn.com.starest.nextoa.exception.MessageException;
import cn.com.starest.nextoa.exception.MessageNotFoundException;
import cn.com.starest.nextoa.exception.PaperNotFoundException;
import cn.com.starest.nextoa.model.*;
import cn.com.starest.nextoa.project.domain.model.*;
import cn.com.starest.nextoa.project.domain.request.*;
import cn.com.starest.nextoa.project.domain.rule.ReimburseReference;
import cn.com.starest.nextoa.project.exception.PaymentException;
import cn.com.starest.nextoa.project.exception.ReimburseException;
import cn.com.starest.nextoa.project.exception.ReimburseNotFoundException;
import cn.com.starest.nextoa.project.repository.ReimburseRepository;
import cn.com.starest.nextoa.project.service.ModulePermissionService;
import cn.com.starest.nextoa.project.service.PaymentService;
import cn.com.starest.nextoa.project.service.ReimburseService;
import cn.com.starest.nextoa.service.MessageService;
import cn.com.starest.nextoa.service.PaperService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author dz
 */
@Service
public class ReimburseServiceImpl extends AbstractPaymentServiceImpl implements ReimburseService {

    private static final Log logger = LogFactory.getLog(ReimburseServiceImpl.class);

    @Autowired
    private MessageService messageService;

    @Autowired
    private PaperService paperService;

    @Autowired
    private PaymentService paymentService;

    @Autowired(required = false)
    private List<ReimburseReference> reimburseReferenceList;

    @Autowired
    private ModulePermissionService modulePermissionService;

    @Autowired
    private ReimburseRepository reimburseRepository;

    @Override
    public ModuleActions.ModuleAction[] resolveGrantedActions(ReimburseContext context, User byWho) {
        if (ReimburseRequestRefer.PAPER == context.getRequestReferType()) {
            Paper paper = paperService.findPaperById(context.getRequestReferId());
            if (paper == null) {
                return ModuleActions.EMPTY_ACTIONS;
            }

            if (PaperStatus.DRAFT != paper.getStatus() && PaperStatus.REVOKED != paper.getStatus()) {
                return ModuleActions.EMPTY_ACTIONS;
            }

            if (paper.getCreatedBy().getId().equals(byWho.getId())) {
                return new ModuleActions.ModuleAction[]{ModuleActions.CREATE_ACTION};
            }
        } else if (ReimburseRequestRefer.MESSAGE == context.getRequestReferType()) {
            // only process the settle action if the user get the list from message
            Message message = messageService.findMessageById(context.getRequestReferId());
            if (message == null) {
                return ModuleActions.EMPTY_ACTIONS;
            }

            String paperId = message.getBizRefId();
            Paper paper = paperService.findPaperById(paperId);
            if (paper == null) {
                return ModuleActions.EMPTY_ACTIONS;
            }

            if (paper.isBusinessComplete()) {
                return ModuleActions.EMPTY_ACTIONS;
            }

            List<ModuleActions.ModuleAction> grantedActions = modulePermissionService.listGrantedActions(Module.REIMBURSE,
                    byWho);

            if (paper.getCreatedBy().getId().equals(byWho.getId())) {
                return ModuleActions.EMPTY_ACTIONS;
            } else if (grantedActions.contains(ModuleActions.SETTLE_ACTION)) {
                return new ModuleActions.ModuleAction[]{ModuleActions.SETTLE_ACTION};
            }

            return ModuleActions.EMPTY_ACTIONS;

            //another strategy, please don't remove this , maybe recover in the future
            //
            //			Message message = messageService.findMessageById(context.getRequestReferId());
            //			if (message == null) {
            //				return ModuleActions.EMPTY_ACTIONS;
            //			}
            //			if (MessageStatus.PENDING != message.getStatus()) {
            //				return ModuleActions.EMPTY_ACTIONS;
            //			}
            //
            //			String paperId = message.getBizRefId();
            //			Paper paper = paperService.findPaperById(paperId);
            //			if (paper == null) {
            //				return ModuleActions.EMPTY_ACTIONS;
            //			}
            //
            //			if (paper.isBusinessComplete()) {
            //				return ModuleActions.EMPTY_ACTIONS;
            //			}
            //
            //			List<ModuleActions.ModuleAction> grantedActions = modulePermissionService.listGrantedActions(Module.REIMBURSE,
            //																										 byWho);
            //			if (paper.getCreatedBy().getId().equals(byWho.getId())) {
            //				return new ModuleActions.ModuleAction[]{ModuleActions.CREATE_ACTION};
            //			}
            //			else if (grantedActions.contains(ModuleActions.SETTLE_ACTION)) {
            //				return new ModuleActions.ModuleAction[]{ModuleActions.SETTLE_ACTION};
            //			}
            //
            //			return grantedActions.toArray(ModuleActions.EMPTY_ACTIONS);
        }

        return ModuleActions.EMPTY_ACTIONS;
    }

    @Override
    public ModuleActions.ModuleAction[] resolveGrantedActions(ReimburseContext context,
                                                              Reimburse reimburse,
                                                              User byWho) {
        if (reimburse.isSettled()) {
            return ModuleActions.EMPTY_ACTIONS;
        }

        if (ReimburseRequestRefer.PAPER == context.getRequestReferType()) {
            Paper paper = paperService.findPaperById(context.getRequestReferId());
            if (paper == null) {
                return ModuleActions.EMPTY_ACTIONS;
            }

            if (PaperStatus.DRAFT != paper.getStatus() && PaperStatus.REVOKED != paper.getStatus()) {
                return ModuleActions.EMPTY_ACTIONS;
            }

            if (paper.getCreatedBy().getId().equals(byWho.getId())) {
                return new ModuleActions.ModuleAction[]{ModuleActions.CREATE_ACTION,
                        ModuleActions.EDIT_ACTION,
                        ModuleActions.DELETE_ACTION};
            }
        } else if (ReimburseRequestRefer.MESSAGE == context.getRequestReferType()) {
            //always return empty actions if the user get the list from message entry;
            return ModuleActions.EMPTY_ACTIONS;


            //another strategy, please don't remove this , maybe recover in the future
            //			Message message = messageService.findMessageById(context.getRequestReferId());
            //			if (message == null) {
            //				return ModuleActions.EMPTY_ACTIONS;
            //			}
            //			if (MessageStatus.PENDING != message.getStatus()) {
            //				return ModuleActions.EMPTY_ACTIONS;
            //			}
            //
            //			String paperId = message.getBizRefId();
            //			Paper paper = paperService.findPaperById(paperId);
            //			if (paper == null) {
            //				return ModuleActions.EMPTY_ACTIONS;
            //			}
            //
            //			if (paper.isBusinessComplete()) {
            //				return ModuleActions.EMPTY_ACTIONS;
            //			}
            //
            //			if (reimburse.getCreatedBy() != null && reimburse.getCreatedBy().getId().equals(byWho.getId())) {
            //				return new ModuleActions.ModuleAction[]{ModuleActions.CREATE_ACTION,
            //														ModuleActions.EDIT_ACTION,
            //														ModuleActions.DELETE_ACTION};
            //			}
        }

        return ModuleActions.EMPTY_ACTIONS;
    }

    @Override
    public Reimburse createReimburse(SaveReimburseRequest request, User byWho) {
        checkReceiveInvoiceAmount(request, null);
        checkProjectProfitAmount(request);
        //		checkPendingPaymentForOutsource(request);

        Reimburse reimburse = new Reimburse();

        BeanUtils.copyProperties(request, reimburse);

        // permission & status check
        ReimburseRequestRefer requestReferType = request.getRequestReferType();
        if (ReimburseRequestRefer.MESSAGE == requestReferType) {
            Message message = messageService.findMessageById(request.getRequestReferId());
            if (message == null) {
                throw new MessageNotFoundException("无效的快文任务id");
            }
            if (MessageStatus.PENDING != message.getStatus()) {
                throw new ReimburseException("该任务已处理或者已结束,请通过待办任务来处理报销申请.");
            }

            String paperId = message.getBizRefId();
            Paper paper = paperService.findPaperById(paperId);
            if (paper == null) {
                throw new PaperNotFoundException("无效的快文id");
            }

            reimburse.setPaper(paper);
        } else if (ReimburseRequestRefer.PAPER == requestReferType) {
            Paper paper = paperService.findPaperById(request.getRequestReferId());
            if (paper == null) {
                throw new PaperNotFoundException("无效的快文id");
            }
            if (PaperStatus.DRAFT != paper.getStatus() && PaperStatus.REVOKED != paper.getStatus()) {
                throw new ReimburseException("该快文已流转,请通过待办任务来处理报销申请");
            }

            reimburse.setPaper(paper);
        }

        handlePaymentReference(request, reimburse, byWho);

        if (reimburse.getPaper() != null) {
            SystemSetting systemSetting = systemSettingService.getSystemSetting();
            if (reimburse.getSubAccountSubject() != null &&
                    reimburse.getSubAccountSubject().getName().equals(systemSetting.getProfitAccountSubjectName())) {

                List<Reimburse> reimburseList = reimburseRepository.findListByPaperOrderByCreatedAtAsc(reimburse.getPaper());
                for (Reimburse existedReimburse : reimburseList) {
                    if (existedReimburse.getSubAccountSubject() != null &&
                            existedReimburse.getSubAccountSubject()
                                    .getName()
                                    .equals(systemSetting.getProfitAccountSubjectName())) {
                        Order order = existedReimburse.getOrder();
                        Contract contract = existedReimburse.getContract();
                        User existedPayToUser = existedReimburse.getPayTo();

                        if (order != null &&
                                existedPayToUser != null &&
                                existedPayToUser.getId().equals(reimburse.getPayTo().getId())) {
                            throw new ReimburseException(String.format("报销费用类型[%s]下已经存在相同的主订单[%s]和付款人员[%s]",
                                    reimburse.getSubAccountSubject().getName(),
                                    order.getName(),
                                    reimburse.getPayTo().getUsername()));
                        } else if (contract != null &&
                                existedPayToUser != null &&
                                existedPayToUser.getId().equals(reimburse.getPayTo().getId())) {
                            throw new ReimburseException(String.format("报销费用类型[%s]下已经存在相同的主合同[%s]和付款人员[%s]",
                                    reimburse.getSubAccountSubject().getName(),
                                    contract.getName(),
                                    reimburse.getPayTo().getUsername()));
                        }
                    }
                }
            }

        }

        Reimburse.onCreate(reimburse, byWho);

        return reimburseRepository.save(reimburse);
    }

    @Override
    public Reimburse updateReimburse(String reimburseId, SaveReimburseRequest request, User byWho) {
        Reimburse reimburse = reimburseRepository.findById(reimburseId);
        if (reimburse == null) {
            throw new ReimburseNotFoundException("没有找到对应的报销");
        }

        checkReceiveInvoiceAmount(request, reimburse);
        checkProjectProfitAmount(request);
        //		checkPendingPaymentForOutsource(request);

        List<ModuleActions.ModuleAction> grantedActions = Arrays.asList(resolveGrantedActions(request,
                reimburse,
                byWho));
        //		List<ModuleActions.ModuleAction> grantedActions = modulePermissionService.listGrantedActions(Module.REIMBURSE,
        //																									 byWho);

        if (!grantedActions.contains(ModuleActions.EDIT_ACTION)) {
            throw new ReimburseException("用户无修改权限");
        }

        //		if (StringUtils.isEmpty(request.getSubContractorId()) && StringUtils.isEmpty(request.getPayToId())) {
        //			throw new ReimburseException("报销公司和报销用户不能同时为空");
        //		}
        //		if (!StringUtils.isEmpty(request.getSubContractorId()) && !StringUtils.isEmpty(request.getPayToId())) {
        //			throw new ReimburseException("报销公司和报销用户只需要设置其中一个值");
        //		}

        BeanUtils.copyProperties(request, reimburse);

        // permission & status check
        ReimburseRequestRefer requestReferType = request.getRequestReferType();
        if (ReimburseRequestRefer.MESSAGE == requestReferType) {
            Message message = messageService.findMessageById(request.getRequestReferId());
            if (message == null) {
                throw new MessageNotFoundException("无效的快文任务id");
            }
            if (MessageStatus.PENDING != message.getStatus()) {
                throw new ReimburseException("该任务已处理或者已结束,请通过待办任务来处理报销申请.");
            }

            String paperId = message.getBizRefId();
            Paper paper = paperService.findPaperById(paperId);
            if (paper == null) {
                throw new PaperNotFoundException("无效的快文id");
            }

            if (reimburse.getPaper() == null) {
                reimburse.setPaper(paper);
            }

            if (!reimburse.getPaper().getId().equals(paper.getId())) {
                throw new ReimburseException("非法请求,请在报销所属的快文中操作");
            }
        } else if (ReimburseRequestRefer.PAPER == requestReferType) {
            Paper paper = paperService.findPaperById(request.getRequestReferId());
            if (paper == null) {
                throw new PaperNotFoundException("无效的快文id");
            }

            if (PaperStatus.DRAFT != paper.getStatus() && PaperStatus.REVOKED != paper.getStatus()) {
                throw new ReimburseException("该快文已流转,请通过待办任务来处理报销申请");
            }

            if (reimburse.getPaper() == null) {
                reimburse.setPaper(paper);
            }

            if (!reimburse.getPaper().getId().equals(paper.getId())) {
                throw new ReimburseException("非法请求,请在报销所属的快文中操作");
            }
        }

        handlePaymentReference(request, reimburse, byWho);
        Reimburse.onModify(reimburse, byWho);

        return reimburseRepository.save(reimburse);
    }

    @Override
    public Reimburse updateReimburseSettlement(String id, SaveReimbursePendingPaymentRequest request, User byWho) {
        return null;
    }

    @Override
    public Reimburse findReimburseById(String reimburseId, User byWho) {
        return reimburseRepository.findById(reimburseId);
    }

    @Override
    public void deleteReimburseById(String reimburseId, ReimburseContext request, User byWho) {

        Reimburse reimburse = reimburseRepository.findById(reimburseId);
        if (reimburse == null) {
            throw new ReimburseNotFoundException("无效的报销申请id");
        }

        List<ModuleActions.ModuleAction> grantedActions = Arrays.asList(resolveGrantedActions(request,
                reimburse,
                byWho));

        //
        //		List<ModuleActions.ModuleAction> grantedActions = modulePermissionService.listGrantedActions(Module.REIMBURSE,
        //																									 byWho);
        if (!grantedActions.contains(ModuleActions.DELETE_ACTION)) {
            throw new ReimburseException("用户无删除权限");
        }

        if (!reimburse.getCreatedBy().getId().equals(byWho.getId())) {
            throw new ReimburseException("非法操作,只有发起人可以删除该报销申请");
        }

        if (reimburse.isSettled()) {
            throw new ReimburseException("该报销已结算,不能删除");
        }

        if (reimburseReferenceList != null) {
            reimburseReferenceList.forEach(ref -> {
                if (ref.hasReference(reimburse)) {
                    throw new ReimburseException("该报销已被其他业务数据引用,不能删除");
                }
            });
        }

        ReimburseRequestRefer requestReferType = request.getRequestReferType();
        if (ReimburseRequestRefer.MESSAGE == requestReferType) {
            Message message = messageService.findMessageById(request.getRequestReferId());
            if (message == null) {
                throw new MessageNotFoundException("无效的快文任务id");
            }
            if (MessageStatus.PENDING != message.getStatus()) {
                throw new ReimburseException("该任务已处理或者已结束,请通过待办任务来删除报销申请");
            }
        } else if (ReimburseRequestRefer.PAPER == requestReferType) {
            Paper paper = paperService.findPaperById(request.getRequestReferId());
            if (paper == null) {
                throw new PaperNotFoundException("无效的快文id");
            }

            if (PaperStatus.DRAFT != paper.getStatus() && PaperStatus.REVOKED != paper.getStatus()) {
                throw new ReimburseException("该快文已流转,请通过待办任务来删除报销申请");
            }
        }

        reimburseRepository.delete(reimburse);
    }

    @Override
    public Page<Reimburse> listReimburses(ReimburseQueryRequest request, User byWho) {
        return reimburseRepository.queryPage(request);
    }

    @Override
    public List<Reimburse> listReimbursesByPaperId(String paperId) {
        Paper paper = paperService.findPaperById(paperId);
        if (paper == null) {
            return Collections.emptyList();
        }

        return reimburseRepository.findListByPaperOrderByCreatedAtAsc(paper);
    }

    @Override
    public List<Reimburse> listReimbursesByBizRefIdAndPayToId(String bizRefId, String payToId) {
        User payTo = accountService.findById(payToId);
        if (payTo == null) {
            return Collections.emptyList();
        }
        return reimburseRepository.findListByBizRefIdAndPayToOrderByCreatedAtAsc(bizRefId, payTo);
    }

    @Override
    public Reimburse settleReimburse(String reimburseId, ReimburseContext request, User byWho) {
        List<ModuleActions.ModuleAction> grantedActions = Arrays.asList(resolveGrantedActions(request, byWho));

        if (!grantedActions.contains(ModuleActions.SETTLE_ACTION)) {
            throw new ReimburseException("用户无结算权限");
        }

        Reimburse reimburse = reimburseRepository.findById(reimburseId);
        if (reimburse == null) {
            throw new ReimburseNotFoundException("无效的报销申请id");
        }

        if (reimburse.isSettled()) {
            logger.warn("该报销已结算,请勿重复操作." + reimburse);
            return reimburse;
        }

        // permission & status check
        Paper paper = null;
        ReimburseRequestRefer requestReferType = request.getRequestReferType();
        if (ReimburseRequestRefer.PAPER == requestReferType) {
            throw new ReimburseException("非法请求,请在快文任务中处理结算");
        } else if (ReimburseRequestRefer.MESSAGE == requestReferType) {
            Message message = messageService.findMessageById(request.getRequestReferId());
            if (message == null) {
                throw new MessageNotFoundException("无效的快文任务id");
            }

            if (MessageStatus.PENDING != message.getStatus()) {
                throw new MessageException("该任务已处理或者已结束,请通过待办任务来处理报销申请.");
            }

            if (reimburse.getCreatedBy() != null && reimburse.getCreatedBy().getId().equals(byWho.getId())) {
                // 申请人即使有权限,也不能自己结算自己的申请
                throw new ReimburseException("用户无该报销申请的结算操作权限");
            }

            paper = paperService.findPaperById(message.getBizRefId());
        }

        Payment payment = paymentRepository.findById(reimburseId);
        if (payment != null) {
            reimburse.setSettled(true);
            reimburse.setSettleAt(reimburse.getCreatedAt());
            reimburse.setSettleBy(reimburse.getCreatedBy());
            reimburseRepository.save(reimburse);

            tryMarkPaperBusinessComplete(paper);

            throw new ReimburseException("该报销已付款,请联系系统管理员.");
        }

        //double check the constraint since the concurrent request is happening
        checkReceiveInvoiceAmount(reimburse);
        checkProjectProfitAmount(reimburse);
        checkPendingPaymentForOutsource(reimburse);

        //receive invoice available amount check
        reimburse.setSettled(true);
        reimburse.setSettleAt(new Date());
        reimburse.setSettleBy(byWho);
        reimburse = reimburseRepository.save(reimburse);
        // create payment from reimburse
        paymentService.createPayment(reimburse, byWho);
        tryMarkPaperBusinessComplete(paper);

        return reimburse;
    }

    @Override
    public void settleBatchReimburses(String paperId, ReimburseContext request, User byWho) {
        Paper paper = paperService.findPaperById(paperId);
        if (paper == null) {
            throw new PaperNotFoundException("无效的快文id");
        }

        // permission & status check
        ReimburseRequestRefer requestReferType = request.getRequestReferType();
        if (ReimburseRequestRefer.PAPER == requestReferType) {
            throw new ReimburseException("非法请求,请在快文任务中处理结算");
        }

        Message message = messageService.findMessageById(request.getRequestReferId());
        if (message == null) {
            throw new MessageNotFoundException("无效的任务id");
        }

        if (MessageStatus.PENDING != message.getStatus()) {
            throw new MessageException("该任务已处理或者已结束,请通过待办任务来处理报销申请.");
        }

        List<Reimburse> reimburseList = reimburseRepository.findListByPaperOrderByCreatedAtAsc(paper);
        if (reimburseList.isEmpty()) {
            return;
        }

        for (Reimburse reimburse : reimburseList) {
            if (reimburse.getCreatedBy() != null && reimburse.getCreatedBy().getId().equals(byWho.getId())) {
                // 申请人即使有权限,也不能自己结算自己的申请
                throw new ReimburseException("用户无该报销申请的结算操作权限");
            }


        }

        for (Reimburse reimburse : reimburseList) {
            Payment payment = paymentRepository.findById(reimburse.getId());
            if (payment != null && !reimburse.isSettled()) {
                // sync up the payment and reimburse status
                reimburse.setSettled(true);
                reimburse.setSettleAt(reimburse.getCreatedAt());
                reimburse.setSettleBy(reimburse.getCreatedBy());
                reimburseRepository.save(reimburse);

                tryMarkPaperBusinessComplete(paper);

                continue;
            }
        }

        //double check the constraints (since concurrent request is happening)
        //1.check receive invoice amount
        HashMap<String, BigDecimal> idReimburseAmountMap = new HashMap<>();
        HashMap<String, ReceiveInvoice> idReceivedInvoiceMap = new HashMap<>();

        for (Reimburse reimburse : reimburseList) {
            if (reimburse.isSettled()) {
                continue;
            }
            ReceiveInvoice receiveInvoice = reimburse.getReceiveInvoice();
            if (receiveInvoice != null) {
                String receiveInvoiceId = receiveInvoice.getId();
                if (idReimburseAmountMap.get(receiveInvoiceId) == null) {
                    idReimburseAmountMap.put(receiveInvoiceId, BigDecimal.ZERO);
                    idReceivedInvoiceMap.put(receiveInvoiceId, receiveInvoice);
                }

                //sum the all payment associated with same receive invoice
                idReimburseAmountMap.put(receiveInvoiceId,
                        idReimburseAmountMap.get(receiveInvoiceId).add(reimburse.getAmount()));
            }
        }

        for (String receiveInvoiceId : idReceivedInvoiceMap.keySet()) {
            BigDecimal reimburseAmount = idReimburseAmountMap.get(receiveInvoiceId);
            ReceiveInvoice receiveInvoice = idReceivedInvoiceMap.get(receiveInvoiceId);

            BigDecimal receiveAmount = receiveInvoice.getAmount().setScale(2, BigDecimal.ROUND_HALF_UP);
            BigDecimal paymentAmount = paymentRepository.calculateTotalAmount(receiveInvoice);

            if (receiveAmount.subtract(paymentAmount).compareTo(reimburseAmount) < 0) {
                throw new PaymentException(String.format("已超出收票[%s]剩余可付款金额[%s]",
                        receiveAmount,
                        receiveAmount.subtract(paymentAmount)));
            }
        }

        //2.check project profit amount
        for (Reimburse reimburse : reimburseList) {
            if (reimburse.isSettled()) {
                continue;
            }

            checkProjectProfitAmount(reimburse);
            checkPendingPaymentForOutsource(reimburse);
        }

        //now do the settle
        for (Reimburse reimburse : reimburseList) {
            if (reimburse.isSettled()) {
                continue;
            }

            reimburse.setSettled(true);
            reimburse.setSettleAt(new Date());
            reimburse.setSettleBy(byWho);
            reimburse = reimburseRepository.save(reimburse);
            // create payment from reimburse
            paymentService.createPayment(reimburse, byWho);
            tryMarkPaperBusinessComplete(paper);
        }
    }

    @Override
    public void reimbursePendingPayment(String reimburseId, SaveReimbursePendingPaymentRequest request, User byWho) {
        Reimburse reimburse = reimburseRepository.findById(reimburseId);
        if (reimburse == null) {
            throw new ReimburseNotFoundException("无效的报销申请id");
        }

//		SystemSetting systemSetting = systemSettingService.getSystemSetting();
//		if (subAccountSubject.getName().equals(systemSetting.getOutsourceAccountSubjectName())) {
//			if (StringUtils.isEmpty(request.getPendingPaymentId())) {
//				throw new PaymentException(String.format("当二级费用类型为「%s」的时候,必须提供待付款",
//														 systemSetting.getOutsourceAccountSubjectName()));
//			}
//		}
//
//		if (!StringUtils.isEmpty(request.getPendingPaymentId())) {
//			PendingPayment pendingPayment = pendingPaymentRepository.findById(request.getPendingPaymentId());
//			if (pendingPayment == null) {
//				throw new EntityNotFoundException("无效的待付款id");
//			}
//			payment.setPendingPayment(pendingPayment);
//		}
//		else {
//			payment.setPendingPayment(null);
//		}

        if (!StringUtils.isEmpty(request.getPendingPaymentId())) {
            PendingPayment pendingPayment = pendingPaymentRepository.findById(request.getPendingPaymentId());
            if (pendingPayment == null) {
                throw new ReimburseException("无效的待付款id");
            }
            reimburse.setPendingPayment(pendingPayment);
        }

        reimburse.setAmount(request.getAmount());
        reimburseRepository.save(reimburse);
    }

    private void tryMarkPaperBusinessComplete(Paper paper) {
        if (paper == null) {
            return;
        }

        if (paper.isBusinessComplete()) {
            return;
        }

        List<Reimburse> reimburses = reimburseRepository.findListByPaperOrderByCreatedAtAsc(paper);
        boolean allSettled = true;
        for (Reimburse reimburse : reimburses) {
            if (!reimburse.isSettled()) {
                allSettled = false;
                break;
            }
        }

        if (allSettled) {
            paperService.markPaperBusinessComplete(paper);
        }
    }

}
