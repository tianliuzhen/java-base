package com.aaa.javabase.common.recurison;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author liuzhen.tian
 * @version 1.0 TestNodeModel.java  2021/7/25 16:32
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestNodeModel {

    /**
     * 节点主键ID
     */
    private Long id;

    /**
     * 标签、名称
     */
    private String label;

    /**
     * 无父节点ID默认是0
     */
    private Long parentId;

    private List<TestNodeModel> children;

}
