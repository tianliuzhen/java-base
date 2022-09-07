package com.aaa.javabase.web;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import com.aaa.javabase.excel.excel1.BeanCopyUtil;
import com.aaa.javabase.excel.excel1.EasyPoiUtils;
import com.aaa.javabase.excel.excel1.FullData;
import com.aaa.javabase.excel.excel1.FullDataExportDTO;
import com.aaa.javabase.excel.excel2.DeptUtil;
import com.aaa.javabase.excel.excel2.EmpUtil;
import com.aaa.javabase.excel.excel2.ExcelUtil;
import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Lists;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ExcelController {
    /**
     * 多sheet 测试导入
     */
    @PostMapping(value = "/importMany")
    @ResponseBody
    public String importMany(MultipartFile file) throws IOException {
        //根据file得到Workbook,主要是要根据这个对象获取,传过来的excel有几个sheet页
        Workbook hssfWorkbook = ExcelUtil.getWorkBook(file);
        StringBuilder sb = new StringBuilder();
        try {
            ImportParams params = new ImportParams();
            // 循环工作表Sheet
            for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
                //表头在第几行
                params.setTitleRows(0);
                //距离表头中间有几行不要的数据
                // params.setStartRows(1);
                //第几个sheet页
                params.setStartSheetIndex(numSheet);

                ExcelImportResult<FullDataExportDTO> result = null;
                if (numSheet == 0) {
                    result = ExcelImportUtil.importExcelMore(file.getInputStream(),
                            FullDataExportDTO.class, params);
                    // TODO: 这里对不同的表进行处理 也就是代码逻辑

                } else if (numSheet == 1) {
                    result = ExcelImportUtil.importExcelMore(file.getInputStream(),
                            FullDataExportDTO.class, params);
                    // TODO: 这里对不同的表进行处理 也就是代码逻辑
                }

                List list = null;
                //如果有些数据验证出来有误   为true
                if (result.isVerfiyFail()) {
                    //不合规定的数据
                    list = result.getFailList();
                    //拼凑错误信息,自定义
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i) instanceof FullDataExportDTO) {
                            ExcelUtil.getWrongInfo(sb, list, i, list.get(i), "orderUserNum", "FullDataExportDTO 填写信息不对");
                        }
                        //....
                    }
                }
            }
            if (sb.length() != 0) {

                return sb.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "导入失败！请检查导入文档的格式是否正确";
        }
        return "导入成功！";
    }

    /**
     * 多sheet 测试导出
     */
    @GetMapping("/exportMany")
    public void exportMany(HttpServletResponse response) {
        Workbook workBook = null;
        try {
            // todo 有一个问题就是如果sheet填充的数据源是一样的，那么第二个sheet的内容就会为空
            // 这里模拟两个数据源
            List<DeptUtil> exportList = addList();
            List<DeptUtil> exportList2 = addList2();
            System.err.println(JSONArray.toJSONString(exportList));

            // 创建参数对象（用来设定excel得sheet得内容等信息）
            ExportParams deptExportParams = new ExportParams();
            // 设置sheet得名称
            deptExportParams.setSheetName("员工报表1");
            // 创建sheet1使用得map
            Map<String, Object> deptExportMap = new HashMap<>();
            // title的参数为ExportParams类型，目前仅仅在ExportParams中设置了sheetName
            deptExportMap.put("title", deptExportParams);
            // 模版导出对应得实体类型
            deptExportMap.put("entity", DeptUtil.class);
            // sheet中要填充得数据
            deptExportMap.put("data", exportList);

            ExportParams empExportParams = new ExportParams();
            empExportParams.setSheetName("员工报表2");
            // 创建sheet2使用得map
            Map<String, Object> empExportMap = new HashMap<>();
            empExportMap.put("title", empExportParams);
            empExportMap.put("entity", DeptUtil.class);
            empExportMap.put("data", exportList);

            // 将sheet1、sheet2、sheet3使用得map进行包装
            List<Map<String, Object>> sheetsList = new ArrayList<>();
            sheetsList.add(deptExportMap);
            sheetsList.add(empExportMap);

            // 执行方法
            workBook = ExcelExportUtil.exportExcel(sheetsList, ExcelType.HSSF);
            EasyPoiUtils.download(workBook, response);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (workBook != null) {
                try {
                    workBook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public List<DeptUtil> addList() {
        List<DeptUtil> list = new ArrayList<>();
        List<EmpUtil> empUtilList = getEmpUtils();

        DeptUtil deptUtil = new DeptUtil();
        deptUtil.setDeptName("开发部");
        deptUtil.setId(1);
        deptUtil.setEmps(empUtilList);
        list.add(deptUtil);

        return list;
    }

    public List<DeptUtil> addList2() {
        List<DeptUtil> list = new ArrayList<>();
        List<EmpUtil> empUtilList = getEmpUtils();

        DeptUtil deptUtil = new DeptUtil();
        deptUtil.setDeptName("开发部2");
        deptUtil.setId(2);
        deptUtil.setEmps(empUtilList);
        list.add(deptUtil);

        return list;
    }

    private List<EmpUtil> getEmpUtils() {
        List<EmpUtil> empUtilList = Lists.newArrayList();

        EmpUtil empUtil = new EmpUtil();
        empUtil.setAge(1);
        empUtil.setEmpName("tom");
        empUtilList.add(empUtil);
        EmpUtil empUtil2 = new EmpUtil();
        empUtil2.setAge(2);
        empUtil2.setEmpName("tom2");
        empUtilList.add(empUtil2);
        return empUtilList;
    }

    /**
     * 测试导出 单个
     */
    @GetMapping("/exportOne")
    public void exportOne(HttpServletResponse response) {
        try {
            // 这里模拟测试数据
            List<FullData> fullData = addListACt();

            List<FullDataExportDTO> list = Lists.newArrayList();
            for (FullData fullDatum : fullData) {
                FullDataExportDTO fullDataExportDTO = new FullDataExportDTO();
                BeanCopyUtil.copyProperties(fullDatum, fullDataExportDTO, true);
                list.add(fullDataExportDTO);
            }

            Workbook workbook = null;
            ExportParams exportParams = new ExportParams();
            if (!list.isEmpty()) {
                workbook = ExcelExportUtil.exportBigExcel(exportParams, FullDataExportDTO.class, list);
                ExcelExportUtil.closeExportBigExcel();
                EasyPoiUtils.download(workbook, response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试导入 单个
     */
    @PostMapping("/importOne")
    public void importOne(@RequestParam(value = "importFile", required = false) MultipartFile importFile) {
        ExcelImportResult<FullDataExportDTO> exportDTOExcelImportResult = EasyPoiUtils.importExcel(importFile, FullDataExportDTO.class);
        List<FullDataExportDTO> list = exportDTOExcelImportResult.getList();
        for (FullDataExportDTO fullDataExportDTO : list) {
            System.out.println(fullDataExportDTO);
        }
    }


    public List<FullData> addListACt() {
        List<FullData> list = new ArrayList<>();
        FullData f1 = new FullData();
        f1.setPayNum("111");
        f1.setOrderUserNum("111");
        list.add(f1);
        FullData f2 = new FullData();
        f2.setPayNum("222");
        f2.setOrderUserNum("222");
        list.add(f2);
        FullData f3 = new FullData();
        f3.setPayNum("333");
        f3.setOrderUserNum("333");
        list.add(f3);
        return list;
    }


}
