package dropred_api.com.services.interfaces;

import java.util.List;

import dropred_api.com.responses.BaseResponse;

public interface baseinterface<T> {
    BaseResponse<T> add(T t);
    BaseResponse<T> update(T t);
    BaseResponse<Boolean> delete(String id);
    BaseResponse<T> getById(String id);
    BaseResponse<List<T>> getAll();
    
}
