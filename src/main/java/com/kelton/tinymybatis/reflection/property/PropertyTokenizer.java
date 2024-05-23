package com.kelton.tinymybatis.reflection.property;

import java.util.Iterator;

/**
 * @Author zhouzekun
 * @Date 2024/5/20 11:13
 */
public class PropertyTokenizer implements Iterable<PropertyTokenizer>, Iterator<PropertyTokenizer> {

    private String name;

    private String indexedName;

    private String index;

    private String children;

    public PropertyTokenizer(String fullName) {

        // class[0].student.score
        // 使用“.”进行分割
        int delim = fullName.indexOf('.');
        if (delim > -1) {
            name = fullName.substring(0, delim);
            children = fullName.substring(delim+1);
        } else {
            name = fullName;
            children = null;
        }

        indexedName = name;
        delim = indexedName.indexOf('[');
        if (delim > -1) {
            name = indexedName.substring(0, delim);
            index = indexedName.substring(delim+1, indexedName.length() -1);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIndexedName() {
        return indexedName;
    }

    public void setIndexedName(String indexedName) {
        this.indexedName = indexedName;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getChildren() {
        return children;
    }

    public void setChildren(String children) {
        this.children = children;
    }

    @Override
    public Iterator<PropertyTokenizer> iterator() {
        return this;
    }

    @Override
    public boolean hasNext() {
        return this.children != null;
    }

    @Override
    public PropertyTokenizer next() {
        return new PropertyTokenizer(children);
    }
}
