package com.shuxiajian.frame.common;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Bootstrap树节点结构化类
 * 暂时仅支持二级菜单
 * @author pengzx
 * @create 2018-08-08 9:28
 */

public class BSTree {
    private List<BSTreeNode> bsTreeNodeList = new ArrayList<BSTreeNode>();  //树节点

    private Map<String ,List<BSTreeNode>> sonNodeListMap = new HashMap<String,List<BSTreeNode>>();  //子节点list map
    private List<BSTreeNode> parNodeList = new ArrayList<BSTreeNode>(); //父级节点list

    private String icon = "fa fa-file-o";   //子节点图标

    private String parIcon = "fa fa-folder-o";  //父节点图标

    /**
     * 将平级list初始化成父子层级，暂时仅支持二级
     * @param bsTreeNodeList node list
     */
    public BSTree(List<BSTreeNode> bsTreeNodeList){
        //循环 空间换时间 也可以多次访问数据库
        for (BSTreeNode bsTreeNode:bsTreeNodeList){
            if (bsTreeNode.getParId() != null && !bsTreeNode.getParId().equals("")){
                //获取父菜单的list
                List<BSTreeNode> sonNodeList = sonNodeListMap.get(bsTreeNode.getParId());

                //不存在则创建
                if (sonNodeList == null) {
                    sonNodeList = new ArrayList<BSTreeNode>();
                    //通过父菜单ID保存子菜单list
                    sonNodeListMap.put(bsTreeNode.getParId(),sonNodeList);
                }

                sonNodeList.add(bsTreeNode);
            }else {
                parNodeList.add(bsTreeNode);
            }
        }

        //循环父菜单 生成tree data
        for (BSTreeNode parNode:parNodeList){

            //获取子菜单list
            List<BSTreeNode> sonNodeList = sonNodeListMap.get(parNode.getId());

            if (sonNodeList != null){
                List<BSTreeNode> sonTreeList = new ArrayList<BSTreeNode>();
                for (BSTreeNode sonNode:sonNodeList){
                    sonNode.setIcon(this.icon);
                    sonTreeList.add(sonNode);
                }
                if (sonTreeList.size() != 0) {
                    parNode.setNodes(sonTreeList);  //设置子节点
                }
            }

            parNode.setIcon(this.parIcon);
            this.bsTreeNodeList.add(parNode);
        }
    }

    public String getNodeListJsonString(){
        SimplePropertyPreFilter filter = new SimplePropertyPreFilter(); // 构造方法里，也可以直接传需要序列化的属性名字
//        filter.getExcludes().add("id");
        filter.getExcludes().add("parId");
        return JSONObject.toJSONString(this.bsTreeNodeList,filter);
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setParIcon(String parIcon) {
        this.parIcon = parIcon;
    }
}
