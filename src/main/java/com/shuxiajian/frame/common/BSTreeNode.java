package com.shuxiajian.frame.common;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * Bootstrap 节点对象
 *
 * @author pengzx
 * @create 2018-08-07 17:48
 */

public class BSTreeNode {

    private String id;  //本节点ID

    private String parId;   //父节点ID

    @JSONField(ordinal = 1)
    private String text;    //显示文本

    @JSONField(ordinal = 2)
    private String icon;//节点图标

    @JSONField(ordinal = 3)
    private String selectedIcon;    //被选择后的节点图标

    @JSONField(ordinal = 4)
    private BSTreeNodeState state;  //状态

    @JSONField(ordinal = 5)
    private List<BSTreeNode> nodes; //当前节点的二级子节点

    /**
     * Bootstrap treeview 节点构造函数
     * @param id 本节点id
     * @param parId 父节点id
     * @param text 显示文本
     * @param bsTreeNodeState 节点状态
     */
    public BSTreeNode(String id,String parId,String text,BSTreeNodeState bsTreeNodeState) {
        this.state = bsTreeNodeState;
        this.id = id;
        this.parId = parId;
        this.text = text;
        this.icon = icon;
    }

    /**
     * Bootstrap treeview 节点构造函数 状态保持默认
     * @param id 本节点id
     * @param parId 父节点id
     * @param text 显示文本
     */
    public BSTreeNode(String id,String parId,String text) {
        this.id = id;
        this.parId = parId;
        this.text = text;
        this.icon = icon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParId() {
        return parId;
    }

    public void setParId(String parId) {
        this.parId = parId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getSelectedIcon() {
        return selectedIcon;
    }

    public void setSelectedIcon(String selectedIcon) {
        this.selectedIcon = selectedIcon;
    }

    public BSTreeNodeState getState() {
        return state;
    }

    public void setState(BSTreeNodeState state) {
        this.state = state;
    }

    public List<BSTreeNode> getNodes() {
        return nodes;
    }

    public void setNodes(List<BSTreeNode> nodes) {
        this.nodes = nodes;
    }
}
