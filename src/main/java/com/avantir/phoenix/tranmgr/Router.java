package com.avantir.phoenix.tranmgr;

import com.avantir.phoenix.model.*;
import com.avantir.phoenix.services.*;
import com.avantir.phoenix.utils.ConstantUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by lekanomotayo on 05/01/2018.
 */

@Component
public class Router {

    private static final Logger logger = LoggerFactory.getLogger(Router.class);

    @Autowired
    CardAcceptorService cardAcceptorService;
    @Autowired
    RoutingGroupService routingGroupService;
    @Autowired
    RouteByReceivingInstService routeByReceivingInstService;
    @Autowired
    CardSetBinService cardSetBinService;
    @Autowired
    AccountTypeService accountTypeService;
    @Autowired
    CardSetAccountTypeService cardSetAccountTypeService;
    @Autowired
    CardProductService cardProductService;
    @Autowired
    RouteByCardService routeByCardService;


    public Long getRouteByReceivingInst(Node fromNode, String cardAcceptorCode, String receivingInst){

        Long routingGroupId = getRoutingGroup(cardAcceptorCode,fromNode);
        if(routingGroupId == null || routingGroupId < 1)
            return 0L;
        RouteByReceivingInst routeByReceivingInst = routeByReceivingInstService.findByReceivingInst(routingGroupId, receivingInst);
        if(routeByReceivingInst == null)
            return 0L;

        return routeByReceivingInst.getSinkNodeId();
    }


    public Long getRouteByCard(Node fromNode, String cardAcceptorCode, Long totalsGroupId){

        Long routingGroupId = getRoutingGroup(cardAcceptorCode,fromNode);
        if(routingGroupId == null)
            return 0L;

        RouteByCard routeByCard = routeByCardService.findByRoutingGroupIdTotalsGroupId(routingGroupId, totalsGroupId);
        if(routeByCard == null)
            return 0L;

        return routeByCard.getSinkNodeId();
    }


    public Long getRoutingGroup(String cardAcceptorCode, Node srcNode){
        //Get Routing Group
        CardAcceptor cardAcceptor = cardAcceptorService.findByCode(cardAcceptorCode);
        if(cardAcceptor == null || cardAcceptor.getId() < 1)
            return 0L;

        Long cardAcceptorRoutingGrpId = cardAcceptor.getRoutingGroupId();
        Long nodeRoutingGrpId = srcNode.getRoutingGroupId();
        RoutingGroup defaultRoutingGroup =  routingGroupService.findByRoutingGroupName(ConstantUtil.DEFAULT_GROUTING_GROUP_NAME);
        Long routingGroupId = cardAcceptorRoutingGrpId;

        if(cardAcceptorRoutingGrpId == defaultRoutingGroup.getId())
            routingGroupId = nodeRoutingGrpId;

        return routingGroupId;
    }


    public Long getTotalsGroup(String pan, String accountTypeCode){
        // Get Totals Group
        CardSetBin cardSetBin = cardSetBinService.findByPan(pan);
        if(cardSetBin == null)
            return 0L;

        AccountType accountType = accountTypeService.findByAccountTypeCode(accountTypeCode);
        if(accountType == null)
            return 0L;

        CardSetAccountType cardSetAccountType = cardSetAccountTypeService.findByCardSetIdAccountTypeId(cardSetBin.getCardSetId(), accountType.getId());
        if(cardSetAccountType == null)
            return 0L;

        CardProduct cardProduct = cardProductService.findByCardSetId(cardSetAccountType.getCardSetAccountTypeId().getCardSetId());
        if(cardProduct == null)
            return 0L;

        return cardProduct.getTotalsGroupId();
    }
}
