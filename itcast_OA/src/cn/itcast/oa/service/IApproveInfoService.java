package cn.itcast.oa.service;

import java.util.List;

import cn.itcast.oa.domain.ApproveInfo;

public interface IApproveInfoService {

	public List<ApproveInfo> findApproveInfoListByApplicationId(Long applicationId);

}
