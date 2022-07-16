package com.agridexample.demo.trade.dao;

// import com.ag.grid.enterprise.oracle.demo.builder.OracleSqlQueryBuilder;
import com.agridexample.demo.aggrid.request.ColumnVO;
import com.agridexample.demo.aggrid.request.EnterpriseGetRowsRequest;
import com.agridexample.demo.aggrid.response.EnterpriseGetRowsResponse;
import com.agridexample.demo.trade.data.TradeDB;
import com.agridexample.demo.trade.model.Trade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.agridexample.demo.aggrid.response.EnterpriseResponseBuilder.createResponse;
import static java.lang.String.format;
import static java.util.stream.Collectors.toMap;

@Repository("tradeDao")
public class TradeDao {
//
//    private JdbcTemplate template;
//    private OracleSqlQueryBuilder queryBuilder;

//    @Autowired
//    public TradeDao(JdbcTemplate template) {
//        this.template = template;
//        queryBuilder = new OracleSqlQueryBuilder();
//    }

    private List allTrades;

    public TradeDao() {
        TradeDB tradeDB = new TradeDB();
        this.allTrades = tradeDB.createTradeData();
    }

    public EnterpriseGetRowsResponse getData(EnterpriseGetRowsRequest request) {
        String tableName = "trade"; // could be supplied in request as a lookup key?

        // first obtain the pivot values from the DB for the requested pivot columns
        Map<String, List<String>> pivotValues = getPivotValues(request.getPivotCols());

        // generate sql
        // String sql = queryBuilder.createSql(request, tableName, pivotValues);

        // query db for rows
       //  List<Map<String, Object>> rows = template.queryForList(sql);

        List<Object> rows = this.allTrades.subList(request.getStartRow(), request.getEndRow());//new ArrayList<>();

        // create response with our results
        return createResponse(request, rows, pivotValues);
    }

    private Map<String, List<String>> getPivotValues(List<ColumnVO> pivotCols) {
        return pivotCols.stream()
                .map(ColumnVO::getField)
                .collect(toMap(pivotCol -> pivotCol, this::getPivotValues, (a, b) -> a, LinkedHashMap::new));
    }

    private List<String> getPivotValues(String pivotColumn) {
        // String sql = "SELECT DISTINCT %s FROM trade";
        // return template.queryForList(format(sql, pivotColumn), String.class);
        return new ArrayList<>();
    }
}
