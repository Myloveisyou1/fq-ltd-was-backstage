package com.fq.fqltdwasbackstage.utils;

import com.fq.fqltdwasbackstage.domain.common.Pages;
import com.fq.fqltdwasbackstage.domain.common.Result;
import com.fq.fqltdwasbackstage.enums.ResultEnum;

/**
 * @Author: 刘英杰
 * @Description: 统一处理返回值
 * @Date: Created in 2017/12/26 13:09
 */
public class ResultUtil {

    public static Result success(Object object, Pages pages){
        Result result = new Result();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMsg(ResultEnum.SUCCESS.getMsg());

        result.setData(object);
        result.setPage(pages);
        if(CommonUtil.isNotEmpty(pages)) {

            result.setTotalCount(pages.getTotalCount());
        }

        return result;
    }

    public static Result success(){
        return success(null,null);
    }

    public static Result error(Integer code, String msg){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(null);
        result.setPage(null);
        return result;
    }
}
