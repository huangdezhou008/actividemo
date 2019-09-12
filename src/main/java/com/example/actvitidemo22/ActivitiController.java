package com.example.actvitidemo22;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 工作流控制器
 */
@Controller
@RequestMapping("/act")
public class ActivitiController {


    public static Logger getLogger() {
        return logger;
    }

    @Autowired
    private ActivitiService activitiService;


    private static final Logger logger = LoggerFactory.getLogger(ActivitiController.class);

    /**
     * 部署流程
     *
     * @return
     */
    @GetMapping(value = "/process/{actKey}")
    @ResponseBody
    public Map processActivit(@PathVariable String actKey) {
        Map<Object, Object> map = new HashMap<>();

        String deployId = activitiService.processActivit(actKey);
        map.put("deployId", deployId);
        return map;
    }

    /**
     * 启动流程
     *
     * @return
     */
    @GetMapping(value = "/start/{processInstanceKey}")
    @ResponseBody
    public Map<String, Object> start(@PathVariable String processInstanceKey) {
        Map<String, Object> resmap = new HashMap<>();
        Map<String, Object> reqmap = new HashMap<>();

        long etime1 = System.currentTimeMillis() + 5 * 60 * 1000;//延时函数，单位毫秒，这里是延时了5分钟
        SimpleDateFormat time2 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat time3 = new SimpleDateFormat("HH:mm:ss");
        String etimeYear = time2.format(new Date(etime1));
        String etimeDate = time3.format(new Date(etime1));

        reqmap.put("time", etimeYear + "T" + etimeDate);

        String BUSSINESS_KEY = processInstanceKey + "." + "1";

        String processInstanaceId = activitiService.startActivit(processInstanceKey, BUSSINESS_KEY, reqmap);

        resmap.put("processInstanaceId", processInstanaceId);
        return resmap;
    }

    /**
     * 查询个人的任务
     *
     * @return
     */
    @GetMapping(value = "/findTask")
    @ResponseBody
    public List<Map<String, Object>> findTask(@RequestParam String assign) {
        List<Map<String, Object>> personalTask = activitiService.findPersonalTask(assign);

           /* for (Task task : personalTask) {
                Map<String, Object> map = new HashMap<>();
                map.put("taskid", task.getId());
                map.put("pdid", task.getProcessDefinitionId());
                map.put("piid", task.getProcessInstanceId());
                res.add(map);
            }*/
        return personalTask;
    }

    /**
     * 查询组任务
     *
     * @return
     */
    /*    @GetMapping(value = "/findGroupTask/{assign}")
        @ResponseBody
        public R findGroupTask(@PathVariable String assign) {
            List<Map<String, Object>> groupTask = activitiService.findGroupTask(assign);


            return     R.ok().put("list",groupTask);
        }*/

    /**
     * 查询批注信息
     *
     * @return
     */
      /*  @GetMapping(value = "/getNotationByTaskId/{taskId}")
        @ResponseBody
        public List<Comment> getNotationByTaskId(@PathVariable String taskId) {
            List<Comment> notationByTaskId = activitiService.getNotationByTaskId(taskId);

            return notationByTaskId;
        }*/

    /**
     * 设置流程变量
     *
     * @param taskId
     * @param jieKuanEntity
     * @param
     * @return
     */
       /* @GetMapping(value = "/setVariable/{taskId}/{varkey}")
        @ResponseBody
        public String setVariable(@PathVariable String taskId, @RequestBody JieKuanEntity jieKuanEntity, @PathVariable String varkey) {

            try {
                activitiService.setVariable(taskId, jieKuanEntity, varkey);
            } catch (Exception e) {
                e.printStackTrace();
                return "false";
            }

            return "success";
        }*/

    /**
     * 获取流程变量
     *
     * @param taskId
     * @param varkey
     * @return
     */
        /*@GetMapping(value = "/getVariableByTaskId/{taskId}/{varkey}")
        @ResponseBody
        public JieKuanEntity getVariableByTaskId(@PathVariable String taskId, @PathVariable String varkey) {

            try {
                JieKuanEntity jieKuanEntity = (JieKuanEntity) activitiService.getVariableByTaskId(taskId, varkey);
                return jieKuanEntity;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;

        }*/

    /**
     * 认领任务
     *
     * @param taskId
     * @return
     */
      /*  @GetMapping(value = "/claim/{taskId}/{userId}")
        @ResponseBody
        public Result claim(@PathVariable String taskId, @PathVariable Integer userId) {

            try {
                activitiService.claim(taskId, userId);
                return Result.SUCCESS();
            } catch (Exception e) {
                e.printStackTrace();
                return Result.ERROR();
            }

        }*/


    /**
     * 提交个人的任务
     * oneAgree 是否同意： 0表示拒绝，1表示通过
     * twoAgree 是否同意： 0表示拒绝，1表示通过
     *
     * @return
     */
    @GetMapping(value = "/commit/{taskId}")
    @ResponseBody
    public String commit(@PathVariable String taskId) {

        try {
            Map<String, Object> map = new HashMap<>();
               /* if (StringUtils.isNotEmpty(baseEntity.getAgreeName())) {
                    map.put(baseEntity.getAgreeName(), baseEntity.getAgreeValue());
                }*/
                /*if (StringUtils.isNotEmpty(baseEntity.getAuditOinion())) {
                    map.put("审核信息", baseEntity.getAuditOinion());
                }*/
            map.put("hello", "hello");
            String res = activitiService.completePersonalTask(taskId, map);

            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据名字查询历史任务
     *
     * @return
     */
    @GetMapping(value = "/historyTaskList")
    @ResponseBody
    public List<Map<String, String>> historyTaskList(@RequestParam String assign) {
        List<Map<String, String>> maps = activitiService.historyTaskList(assign);

        return maps;
    }

}