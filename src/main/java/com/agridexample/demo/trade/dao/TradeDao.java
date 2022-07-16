package com.agridexample.demo.trade.dao;

// import com.ag.grid.enterprise.oracle.demo.builder.OracleSqlQueryBuilder;
import com.agridexample.demo.aggrid.request.ColumnVO;
import com.agridexample.demo.aggrid.request.EnterpriseGetRowsRequest;
import com.agridexample.demo.aggrid.request.SortModel;
import com.agridexample.demo.aggrid.response.EnterpriseGetRowsResponse;
import com.agridexample.demo.trade.data.TradeDB;
import com.agridexample.demo.trade.model.Trade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.apache.commons.beanutils.BeanComparator;


import java.util.*;

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
        this.allTrades = tradeDB.createTradeData().subList(0, 1000);
        System.out.println(allTrades.size());
    }

    public EnterpriseGetRowsResponse getData(EnterpriseGetRowsRequest request) {
        String tableName = "trade"; // could be supplied in request as a lookup key?

        // first obtain the pivot values from the DB for the requested pivot columns
        Map<String, List<String>> pivotValues = getPivotValues(request.getPivotCols());

        // generate sql
        // String sql = queryBuilder.createSql(request, tableName, pivotValues);

        // query db for rows
       //  List<Map<String, Object>> rows = template.queryForList(sql);




        // sort
        List<SortModel> sortModel = request.getSortModel();
        // only support 1 sort for now
        if (sortModel.size() > 0) {
            SortModel firstSort = sortModel.get(0);
            String property = firstSort.getColId();
            String direction = firstSort.getSort();
            System.out.println("Sorting by: " + property + " " + direction);

            Comparator comp = null;

            if (direction.equalsIgnoreCase("asc")){
                comp = new BeanComparator(property);
            } else {
                comp = new BeanComparator(property).reversed();
            }

            Collections.sort(this.allTrades, comp);
        }

        int startRow = request.getStartRow();
        int endRow = request.getEndRow();

        if (endRow > this.allTrades.size()) {
            endRow = this.allTrades.size();
        }

        // reduce size
        List<Object> rows = this.allTrades.subList(startRow, endRow);//new ArrayList<>();
        // System.out.println(rows.size());

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
