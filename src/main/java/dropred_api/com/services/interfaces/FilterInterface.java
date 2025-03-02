package dropred_api.com.services.interfaces;

import java.util.List;
import java.util.Map;

import dropred_api.com.responses.BaseResponse;

public interface FilterInterface<T> {
    /**
     * count all page size
     * 
     * @param filter filter with conditions(category, name, price, etc)
     * @param size  page size
     * @return total page size
     */
    default BaseResponse<Map<String, Integer>> maxPage(Map<String, String> filters, int size) {
        return maxPage(filters, size);
    }

    /**
     * get all data
     * 
     * @param filter filter with conditions(category, name, price, etc)
     * @return list of data
     */
    
    /**
     * get date with sorting
     * 
     * @param filter filters with conditions(category, name, price, etc)
     * @param page page number
     * @param size page size
     * @param sort sort by
     * @return list of data
     */
     default BaseResponse<List<T>> filter(Map<String, String> filters, int page, int size, String sortDiraction, String sortBy)
        {
            return filter(filters, page, size, sortDiraction, sortBy);
        }

}
