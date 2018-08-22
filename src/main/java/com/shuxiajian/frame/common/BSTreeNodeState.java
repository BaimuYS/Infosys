package com.shuxiajian.frame.common;

/**
 * bootstrap treeview节点状态类
 *
 * @author pengzx
 * @create 2018-08-08 11:41
 */

public class BSTreeNodeState {
    private Boolean checked;    //是否处于checked状态
    private Boolean disabled;   //是否处于disabled状态
    private Boolean expanded;   //是否处于展开状态
    private Boolean selected;   //是否可以被选择

    public BSTreeNodeState(Boolean checked, Boolean disabled, Boolean expanded, Boolean selected) {
        this.checked = checked;
        this.disabled = disabled;
        this.expanded = expanded;
        this.selected = selected;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public Boolean getExpanded() {
        return expanded;
    }

    public void setExpanded(Boolean expanded) {
        this.expanded = expanded;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }
}
