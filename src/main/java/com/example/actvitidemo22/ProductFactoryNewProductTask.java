package com.example.actvitidemo22;

import org.activiti.engine.EngineServices;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.history.HistoricActivityInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service("productFactoryNewProductTask")
public class ProductFactoryNewProductTask implements Serializable {
    private static Logger logger = LoggerFactory.getLogger(ProductFactoryNewProductTask.class);
    @Autowired
    private ActivitiService activitiService;

    public void execute(DelegateExecution delegateExecution) throws Exception {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        logger.info(" begin！===" + df.format(new Date()));
        logger.info(" =>business:{} prossId:{}  ", delegateExecution.getBusinessKey(), delegateExecution.getProcessInstanceId());
        //统计谁，是否是过期任务

        EngineServices engineServices = delegateExecution.getEngineServices();

        List<HistoricActivityInstance> list = engineServices.getHistoryService().createNativeHistoricActivityInstanceQuery().list();
        if(list.size()>0){
        for (HistoricActivityInstance historicActivityInstance : list) {
            System.out.println(historicActivityInstance);
        }}
    }
}