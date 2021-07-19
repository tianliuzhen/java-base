package com.aaa.javabase.web;

import com.aaa.javabase.domain.BaseMain;
import com.aaa.javabase.pattern.behavior.strategy.InspectionSolver;
import com.aaa.javabase.pattern.behavior.strategy.InspectionSolverChooser;
import com.aaa.javabase.pattern.behavior.strategy.constant.InspectionEnum;
import com.aaa.javabase.service.GoodsService;
import com.aaa.javabase.spring.conditionBean.service.People;
import com.aaa.javabase.spring.injection.construction.Abean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author liuzhen.tian
 * @version $ Id: WebTest.java, v 0.1 2020/6/18 11:02 liuzhen.tian Exp $
 */
@RestController
public class WebController {
    @Autowired
    private InspectionSolverChooser inspectionSolverChooser;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private Abean abean;

    @GetMapping(value = "/inspectionSolver")
    public void test(){
        //准备数据
        InspectionEnum taskType = InspectionEnum.INSPECTION_TASK_TYPE_BATCH_REPLACE_ORDER_GOODS;
        Long orderId = 123L;
        Long userId = 1L;
        // 获取任务类型对应的 solver
        InspectionSolver solver = inspectionSolverChooser.choose(taskType);
        if (solver==null) {
            throw new RuntimeException("任务类型无法处理");
        }
        // 调用不同的solve的方法进行处理
        solver.slove(orderId,userId);


    }

    /**
     * 用户列表
     * @param listForm
     */
    @RequestMapping(path = "list", method = {RequestMethod.GET,  RequestMethod.POST}  )
    public String list(@RequestParam(required=true) String listForm){
        return "str";
    }


    @GetMapping(value = "/getGoodsServiceGoodsNames")
    public void getGoodsServiceGoodsNames(){
        System.out.println(goodsService.getGoodsName());
    }

    /**
     * 测试返回两种时间格式
     * Date
     * DateTime
     * @return
     */
    @PostMapping("/objectResponseDate")
    public Object response(){
        BaseMain baseMain = new BaseMain();
        baseMain.setDate(new Date());
        baseMain.setLocalDateTime(LocalDateTime.now());

        return baseMain;
    }



    @GetMapping ("/testInt")
    public People testInt() {
        People people = new People();
        return people;

    }

    @GetMapping ("/testBean")
    public void testBean() {
        abean.get();
    }
}
