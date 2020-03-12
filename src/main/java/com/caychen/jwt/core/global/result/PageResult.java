package com.caychen.jwt.core.global.result;

import lombok.Data;

import java.util.List;

/**
 * @Author: Caychen
 * @Date: 2020-03-11
 * @Describe:
 */
@Data
public class PageResult<T> {

    private Long pageIndex;

    private Long totalCount;

    private Long pageSize;

    private List<T> pageData;
}
