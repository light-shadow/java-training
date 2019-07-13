package com.general;

import java.util.*;

/**
 * @description: 仿写的arraylist
 * @author: general
 * @version: 1.0
 * @create: 2019-06-29 15:53
 **/
public class ArrayList<E> extends AbstractList<E> implements List<E> , RandomAccess, Cloneable, java.io.Serializable {


    private static final long serialVersionUID = 8783452581122892189L;

    // 设置默认的容量为10
    private static final int DEFAULT_CAPACITY = 10;

    // 一个空对象
    private static final Object[] EMPTY_ELEMENTDATA = {};

    // 一个空对象 如果使用默认构造函数创建 则默认内容默认是该值
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

    // 当前数据对象存放的地方 当前对象不参与序列化
    transient Object[] elementData;

    // 数组的长度
    private int size;

    // 带有初始容量的构造方法
    public ArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = EMPTY_ELEMENTDATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: "+
                    initialCapacity);
        }
    }

    // 不带初始容量的构造方法
    public ArrayList() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    // 带有collection参数的构造方法
    public ArrayList(Collection<? extends E> c) {
        elementData = c.toArray();
        // 判断原来collection的长度是否为0 为0 直接构造一个空的arraylist
        // 否则判断原来的collection类型是否为object 不是的话转换为object 是的话直接返回
        if ((size = elementData.length) != 0) {
            // c.toArray might (incorrectly) not return Object[] (see 6260652)
            if (elementData.getClass() != Object[].class)
                elementData = Arrays.copyOf(elementData, size, Object[].class);
        } else {
            // replace with empty array.
            this.elementData = EMPTY_ELEMENTDATA;
        }
    }
    // 压缩arraylist的内存溶剂
    public void trimToSize() {
        modCount++;
        // 如果现在list中的真实存在的元素数量小于现在数组的长度
        // 如果数组长度为0 返回一个空数组
        // 将存在的元素拷贝到
        if (size < elementData.length) {
            elementData = (size == 0)
                    ? EMPTY_ELEMENTDATA
                    : Arrays.copyOf(elementData, size);
        }
    }

    public void ensureCapacity(int minCapacity) {
        int minExpand = (elementData != DEFAULTCAPACITY_EMPTY_ELEMENTDATA)
                // any size if not default element table
                ? 0
                // larger than default for default empty table. It's already
                // supposed to be at default size.
                : DEFAULT_CAPACITY;

        if (minCapacity > minExpand) {
            ensureExplicitCapacity(minCapacity);
        }
    }

    private void ensureExplicitCapacity(int minCapacity) {
        modCount++;

        // overflow-conscious code
        if (minCapacity - elementData.length > 0)
            grow(minCapacity);
    }

    private void grow(int minCapacity) {
        // overflow-conscious code
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity < 0)
            newCapacity = minCapacity;
        if (newCapacity - MAX_ARRAY_SIZE > 0)
            newCapacity = hugeCapacity(minCapacity);
        // minCapacity is usually close to size, so this is a win:
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }
}
