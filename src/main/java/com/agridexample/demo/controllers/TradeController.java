package com.agridexample.demo.controllers;

import com.agridexample.demo.aggrid.request.EnterpriseGetRowsRequest;
import com.agridexample.demo.aggrid.response.EnterpriseGetRowsResponse;
// import com.agridexample.demo.aggrid.dao.TradeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class TradeController {

    //private TradeDao tradeDao;

//    @Autowired
//    public TradeController(@Qualifier("tradeDao") TradeDao tradeDao) {
//        this.tradeDao = tradeDao;
//    }

    @RequestMapping(method = POST, value = "/getRows")
    @ResponseBody
    public EnterpriseGetRowsResponse getRows(@RequestBody EnterpriseGetRowsRequest request) {
        // return tradeDao.getData(request);
        System.out.println(request);
        return new EnterpriseGetRowsResponse();
    }
}
