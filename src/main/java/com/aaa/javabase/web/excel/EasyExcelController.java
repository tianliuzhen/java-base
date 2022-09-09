package com.aaa.javabase.web.excel;

import com.aaa.javabase.web.excel.model.ComplexHeadData;
import com.aaa.javabase.web.excel.model.ImageDemoData;
import com.aaa.javabase.web.excel.model.IndexData;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.data.ImageData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.util.FileUtils;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.excel.write.metadata.WriteSheet;
import lombok.SneakyThrows;
import org.assertj.core.util.Lists;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author liuzhen.tian
 * @version 1.0 EasyExcelController.java  2022/9/9 20:08
 */

@RestController
@RequestMapping(value = "/easyExcel")
public class EasyExcelController {

    public static List<ComplexHeadData> complexHeadData = Lists.newArrayList();
    public static List<IndexData> indexData = Lists.newArrayList();

    static {
        for (int i = 0; i < 4; i++) {
            ComplexHeadData data = new ComplexHeadData();
            data.setDate(new Date());
            data.setString("字符串" + i);
            data.setDesc("描述");
            data.setDoubleData((double) i);
            complexHeadData.add(data);
        }

        for (int i = 0; i < 5; i++) {
            indexData.add(new IndexData("字符串" + i, new Date(), (double) i));
        }
    }

    @SneakyThrows
    private void setResponseHeader(HttpServletResponse response, String fileName) {
        // 告诉浏览器用什么软件可以打开此文件
        response.setHeader("content-Type", "application/vnd.ms-excel");
        // 下载文件的默认名称
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8") + ".xlsx");
    }

    /**
     * 测试导出 单个sheet
     */
    @SneakyThrows
    @GetMapping("/exportOne")
    public void exportOne(HttpServletResponse response) {
        setResponseHeader(response, "easyExcel.exportOne");

        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 导出单个sheet不需要手动 close
        EasyExcel.write(response.getOutputStream(), ComplexHeadData.class).sheet("模板").doWrite(complexHeadData);

    }


    /**
     * 测试导出 多个sheet
     */
    @SneakyThrows
    @GetMapping("/exportMany")
    public void exportMany(HttpServletResponse response) {
        setResponseHeader(response, "easyExcel.exportMay");
        ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream(), ComplexHeadData.class).build();

        // 导出sheet1
        WriteSheet sheet1 = EasyExcel.writerSheet(0, "模板一").head(ComplexHeadData.class).build();
        excelWriter.write(complexHeadData, sheet1);

        // 导出sheet2
        WriteSheet sheet2 = EasyExcel.writerSheet(1, "模板二").head(IndexData.class).build();
        excelWriter.write(indexData, sheet2);

        // todo 注意：这里必须要调用  excelWriter.close() 或者 excelWriter.finish();
        excelWriter.close();
    }

    /**
     * 测试导出 单个图片
     */
    @SneakyThrows
    @GetMapping("/imageWrite")
    public void imageWrite(HttpServletResponse response) {
        String fileName = "easyExcel.imageWrite";
        setResponseHeader(response, fileName);
        String imagePath = "F:\\我的\\tree.jpg";

        try (InputStream inputStream = FileUtils.openInputStream(new File(imagePath))) {
            List<ImageDemoData> list = ListUtils.newArrayList();
            ImageDemoData imageDemoData = new ImageDemoData();
            list.add(imageDemoData);
            // 放入五种类型的图片 实际使用只要选一种即可
            imageDemoData.setByteArray(FileUtils.readFileToByteArray(new File(imagePath)));
            imageDemoData.setFile(new File(imagePath));
            imageDemoData.setString(imagePath);
            imageDemoData.setInputStream(inputStream);
            imageDemoData.setUrl(new URL("https://img1.baidu.com/it/u=2475127973,1009717621&fm=253&app=138&size=w931&n=0&f=JPEG&fmt=auto?sec=1662829200&t=25d1a736a307f3232223300873784f31"));

            // 这里演示
            // 需要额外放入文字
            // 而且需要放入2个图片
            // 第一个图片靠左
            // 第二个靠右 而且要额外的占用他后面的单元格
            WriteCellData<Void> writeCellData = new WriteCellData<>();
            imageDemoData.setWriteCellDataFile(writeCellData);
            // 这里可以设置为 EMPTY 则代表不需要其他数据了
            writeCellData.setType(CellDataTypeEnum.STRING);
            writeCellData.setStringValue("额外的放一些文字");

            // 可以放入多个图片
            List<ImageData> imageDataList = new ArrayList<>();
            ImageData imageData = new ImageData();
            imageDataList.add(imageData);
            writeCellData.setImageDataList(imageDataList);
            // 放入2进制图片
            imageData.setImage(FileUtils.readFileToByteArray(new File(imagePath)));
            // 图片类型
            imageData.setImageType(ImageData.ImageType.PICTURE_TYPE_PNG);
            // 上 右 下 左 需要留空
            // 这个类似于 css 的 margin
            // 这里实测 不能设置太大 超过单元格原始大小后 打开会提示修复。暂时未找到很好的解法。
            imageData.setTop(5);
            imageData.setRight(20);
            imageData.setBottom(5);
            imageData.setLeft(5);

            // 放入第二个图片
            imageData = new ImageData();
            imageDataList.add(imageData);
            writeCellData.setImageDataList(imageDataList);
            imageData.setImage(FileUtils.readFileToByteArray(new File(imagePath)));
            imageData.setImageType(ImageData.ImageType.PICTURE_TYPE_PNG);
            imageData.setTop(5);
            imageData.setRight(5);
            imageData.setBottom(5);
            imageData.setLeft(30);
            // 设置图片的位置 假设 现在目标 是 覆盖 当前单元格 和当前单元格右边的单元格
            // 起点相对于当前单元格为0 当然可以不写
            imageData.setRelativeFirstRowIndex(0);
            imageData.setRelativeFirstColumnIndex(0);
            imageData.setRelativeLastRowIndex(0);
            // 前面3个可以不写  下面这个需要写 也就是 结尾 需要相对当前单元格 往右移动一格
            // 也就是说 这个图片会覆盖当前单元格和 后面的那一格
            imageData.setRelativeLastColumnIndex(1);

            // 写入数据
            EasyExcel.write(response.getOutputStream(), ImageDemoData.class).sheet().doWrite(list);
        }
    }

}
