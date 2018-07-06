package com.fq.fqltdwasbackstage.domain;

import lombok.Data;
import java.util.Date;

/**
 * 钱包地址到账情况表
 * 
 * @author liuyingjie
 * @email liuyingjie@fengqun.ltd
 * @date 2018-07-06 15:58:33
 */
@Data
public class WasAddress{

	/**
	 * 自动增长ID
	 */
		private Long wasId;
	/**
	 * 币种类型，如BTC、ETH等等
	 */
	private String wasType;
	/**
	 * 钱包地址，用来查询币是否有到账
	 */
	private String wasAddress;
	/**
	 * 到期时间，以天为单位
	 */
	private Integer wasExpireTime;
	/**
	 * 区块高度
	 */
	private Integer wasBlockNumber;
	/**
	 * 备注
	 */
	private String wasRemark;
	/**
	 * 创建时间
	 */
	private Date wasCreateTime;
	/**
	 * 最近一次延期操作时间
	 */
	private Date wasUpdateTime;
	/**
	 * 来源系统，比如Pizza（P）
	 */
	private String wasSource;
	/**
	 * 
	 */
	private Date wasInitTime;
}
