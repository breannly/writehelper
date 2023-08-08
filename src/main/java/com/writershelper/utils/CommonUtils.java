package com.writershelper.utils;

import com.writershelper.model.Identifiable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CommonUtils {

    public static<T> List<T> emptyIfNull(List<T> list) {
        return Objects.isNull(list) ? new ArrayList<>() : list;
    }

    public static<T extends Identifiable<ID>, ID> List<T> updateById(List<T> list, ID id, T t) {
        List<T> items = CommonUtils.emptyIfNull(list);
        for (int i = 0; i < items.size(); i++) {
            T currentItem = items.get(i);
            if (currentItem.getId().equals(id)) {
                list.set(i, t);
                break;
            }
        }
        return items;
    }
}
