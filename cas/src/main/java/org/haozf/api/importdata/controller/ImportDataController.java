package org.haozf.api.importdata.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.haozf.api.importdata.service.ImportDataService;
import org.haozf.common.annotation.WithOutOauth;
import org.haozf.common.annotation.WithOutlogin;
import org.haozf.common.base.BaseController;
import org.haozf.common.enums.ApiStatus;
import org.haozf.common.model.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/import")
public class ImportDataController extends BaseController {

	private boolean importData;

	private List<ImportDataService> importDataServices = new ArrayList<ImportDataService>();

	@Autowired
	public ImportDataController(ImportDataService erShouImportDataService, ImportDataService d1cmImportDataService, ImportDataService maiPeiJianImportDataService) {
		importDataServices.add(erShouImportDataService);
		importDataServices.add(d1cmImportDataService);
		importDataServices.add(maiPeiJianImportDataService);
	}

	@RequestMapping("/user")
	@ResponseBody
	@WithOutOauth
	@WithOutlogin
	public Json importUser(String source,HttpServletRequest request) {
		Enumeration<String> enumer =  request.getAttributeNames();
		while(enumer.hasMoreElements()){
			String attr = enumer.nextElement();
			System.out.println(attr);
			System.out.println("\nsession item value="+request.getSession().getAttribute(attr));  
		}
		
		try {
			if (!importData) {
				json.setStatus(ApiStatus.fail);
				json.setMessage("cas不允许导入数据");
				return json;
			}
			// 导入数据
			for (ImportDataService importDataService : importDataServices) {
				List<String> sourceList = Arrays.asList(source.split(","));
				if (sourceList.contains(importDataService.support())) {
					// importDataService.importData();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			json.setStatus(ApiStatus.fail);
			json.setMessage("导入数据失败");
			return json;
		}
		json.setStatus(ApiStatus.success);
		json.setMessage("导入数据成功");
		return json;
	}

	@Value("#{propertyConfigurer['cas.importData']}")
	public void setImportData(boolean importData) {
		this.importData = importData;
	}
}
