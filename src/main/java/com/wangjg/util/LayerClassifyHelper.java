package com.wangjg.util;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 将平铺的数据按层级结构组装
 *
 * @param <OriginalType> 原始类型（例如 数据库映射对象类型 Entity）
 * @param <WrapType> 封装类型（例如 VO、DO、DTO ...）
 * @param <IdTYPE> ID的类型
 */
public abstract class LayerClassifyHelper<OriginalType, WrapType, IdTYPE> {

    /**
     * 处理数据层级封装
     *
     * @param originalList 原始对象集合
     * @param wrapList 处理容器结果容器
     */
    public final void dynamicHandleLayer(
        List<OriginalType> originalList
        , List<WrapType> wrapList) {
        Map<IdTYPE, List<OriginalType>> mapKeyIdValueDirectChildren = originalList.stream()
            .collect(Collectors.groupingBy(this::getOriginalPid));

        Map<IdTYPE, OriginalType> mapKeyIdValueSelf = originalList.stream().collect(
            Collectors.toMap(this::getOriginalTypeId, Function.identity(), (oldV, newV) -> oldV));

        // 父不存在
        List<OriginalType> topLayer = originalList.stream()
            .filter(i -> mapKeyIdValueSelf.get(this.getOriginalPid(i)) == null)
            .collect(Collectors.toList());

        if (CollectionUtil.isEmpty(topLayer)) {
            return;
        }

        Deque<WrapType> queue = new LinkedList<>();

        WrapType tmp;
        for (OriginalType original : topLayer) {
            tmp = this.transDataFromOriginalTypeToWrapType(original);
            queue.push(tmp);
            wrapList.add(tmp);
        }

        WrapType item;
        List<WrapType> tmpList;

        while (!queue.isEmpty()) {
            item = queue.poll();
            List<OriginalType> childrenOfTmp = mapKeyIdValueDirectChildren
                .get(this.getWrapTypeId(item));

            if (CollectionUtil.isEmpty(childrenOfTmp)) {
                continue;
            }

            tmpList = new ArrayList<>();
            for (OriginalType original : childrenOfTmp) {
                tmp = this.transDataFromOriginalTypeToWrapType(original);
                tmpList.add(tmp);
                queue.push(tmp);
            }
            this.setWarpTypeChildren(item, tmpList);
        }

    }

    /**
     * 获取原始类型的 ID
     *
     * @param item 原始类型的对象
     * @return 原始对象的ID
     */
    protected abstract IdTYPE getOriginalTypeId(OriginalType item);

    /**
     * 获取封装类型的 ID
     *
     * @param warpItem 封装类型的对象
     * @return 封装对象的 ID
     */
    protected abstract IdTYPE getWrapTypeId(WrapType warpItem);

    /**
     * 获取原始类型的ParentID
     *
     * @param item 原始类型的对象
     * @return 原始对象的ParentID
     */
    protected abstract IdTYPE getOriginalPid(OriginalType item);

    /**
     * 将原始对象转换为封装对象
     *
     * @param item 原始对象
     * @return 封装对象
     */
    protected abstract WrapType transDataFromOriginalTypeToWrapType(OriginalType item);

    /**
     * 设置封装对象的 children 属性
     *
     * @param warpItem 封装对象
     * @param children 封装对象的直接下级集合
     */
    protected abstract void setWarpTypeChildren(WrapType warpItem, List<WrapType> children);

    ////    --------------------------------------测试用例------------------------------------------

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    private static class Org {

        private String id;
        private String name;
        private String pid;
    }

    @Data
    private static class OrgVO {

        private String id;
        private String name;
        private String pid;
        private List<OrgVO> children;
    }

    public static void main(String[] args) {
        List<Org> dataFromDatabase = new ArrayList<>();
//        dataFromDatabase.add(new Org("1", "公司", ""));
        dataFromDatabase.add(new Org("2", "人事", "1"));
        dataFromDatabase.add(new Org("3", "行政", "1"));
        dataFromDatabase.add(new Org("4", "财务", "1"));
        dataFromDatabase.add(new Org("5", "HR", "2"));
        dataFromDatabase.add(new Org("6", "薪酬福利", "2"));
        dataFromDatabase.add(new Org("7", "后勤", "3"));

        LayerClassifyHelper<Org, OrgVO, String> helper = new LayerClassifyHelper<Org, OrgVO, String>() {
            @Override
            protected String getOriginalTypeId(Org item) {
                return item.getId();
            }

            @Override
            protected String getWrapTypeId(OrgVO warpItem) {
                return warpItem.getId();
            }

            @Override
            protected String getOriginalPid(Org item) {
                return item.getPid();
            }

            @Override
            protected OrgVO transDataFromOriginalTypeToWrapType(Org item) {
                OrgVO orgVO = new OrgVO();
                orgVO.setId(item.getId());
                orgVO.setName(item.getName());
                orgVO.setPid(item.getPid());
                return orgVO;
            }

            @Override
            protected void setWarpTypeChildren(OrgVO warpItem, List<OrgVO> children) {
                warpItem.setChildren(children);
            }
        };

        List<OrgVO> orgVOS = new ArrayList<>();
        helper.dynamicHandleLayer(dataFromDatabase, orgVOS);

        System.out.println(orgVOS);

    }
}