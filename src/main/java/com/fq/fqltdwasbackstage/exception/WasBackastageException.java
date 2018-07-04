package com.fq.fqltdwasbackstage.exception;

import com.fq.fqltdwasbackstage.enums.ResultEnum;
import lombok.Data;

@Data
public class WasBackastageException extends RuntimeException{
    private Integer code;


    public WasBackastageException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

}
