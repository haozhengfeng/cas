package org.haozf.api.sync.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.haozf.api.sync.service.ISyncService;
import org.haozf.api.sync.validate.ISyncValidate;
import org.haozf.common.annotation.WithOutlogin;
import org.haozf.common.base.BaseController;
import org.haozf.common.enums.ApiStatus;
import org.haozf.common.enums.RegistSource;
import org.haozf.common.model.Json;
import org.haozf.identity.user.exception.UserException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/api/sync")
@Controller
public class SyncController extends BaseController {

	public ISyncService syncService;

	@Resource
	public ISyncService erShouSyncService;
	
	@Resource
	private ISyncValidate erShouSyncValidate;

	/**
	 * 通过source 实例化 syncService
	 * 
	 * @param source
	 */
	@ModelAttribute
	public void init(@RequestParam String source) {
		if (RegistSource.ershou.toString().equals(source))
			syncService = erShouSyncService;
	}

	/**
	 * 从子系统向cas数据库中添加数据
	 * 
	 * @param sources
	 *            格式为 userid|source
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/add", method = { RequestMethod.GET, RequestMethod.POST })
	@WithOutlogin
	@ResponseBody
	public Json add(@RequestParam(value = "sourceids")  String[] sourceids, HttpServletRequest request) {
		try {
			for(String sourceid:sourceids){
				int num = erShouSyncValidate.checkSourceidExist(sourceid);
				if(num > 0){
					throw new UserException("已存在用户【"+sourceid+"】！");
				}
			}
			
			syncService.add(sourceids);
		} catch (Exception e) {
			e.printStackTrace();
			json.setStatus(ApiStatus.fail);
			String message = e.getMessage();
			json.setMessage("同步数据失败");
			if(message!=null){
				json.setMessage(message);
			}
			return json;
		}
		json.setStatus(ApiStatus.success);
		json.setMessage("同步数据成功");
		return json;
	}
}
