package com.framework.core;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;

@SuppressWarnings("serial")
public class PageParam implements Pageable, Serializable {
    private int page;
    private int size;
    private Sort sort;

    public PageParam() {
        this(0, 10, null);
    }

    /**
     * Creates a new . Pages are zero indexed, thus providing 0 for {@code page} will return the first
     * page.
     *
     * @param size
     * @param page
     */
    public PageParam(int page, int size) {
        this(page, size, null);
    }

    /**
     * Creates a new  with sort parameters applied.
     *
     * @param page
     * @param size
     * @param direction
     * @param properties
     */
    public PageParam(int page, int size, Sort.Direction direction, String... properties) {
        this(page, size, new Sort(direction, properties));
    }

    /**
     * Creates a new  with sort parameters applied.
     *
     * @param page
     * @param size
     * @param sort can be {@literal null}.
     */
    public PageParam(int page, int size, Sort sort) {

        if (page < 0) {
            throw new IllegalArgumentException("Page index must not be less than zero!");
        }

        if (size < 0) {
            throw new IllegalArgumentException("Page size must not be less than zero!");
        }

        this.page = page;
        this.size = size;
        this.sort = sort;
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.domain.Pageable#getPageSize()
     */
    public int getPageSize() {

        return size;
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.domain.Pageable#getPageNumber()
     */
    public int getPageNumber() {
        return page;
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.domain.Pageable#getOffset()
     */
    public int getOffset() {
        return page * size;
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.domain.Pageable#getSort()
     */
    public Sort getSort() {
        return sort;
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.domain.Pageable#hasPrevious()
     */
    public boolean hasPrevious() {
        return page > 0;
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.domain.Pageable#next()
     */
    public Pageable next() {
        return new PageParam(page + 1, size, sort);
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.domain.Pageable#previousOrFirst()
     */
    public Pageable previousOrFirst() {
        return hasPrevious() ? new PageParam(page - 1, size, sort) : this;
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.domain.Pageable#first()
     */
    public Pageable first() {
        return new PageParam(0, size, sort);
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof PageParam)) {
            return false;
        }

        PageParam that = (PageParam) obj;

        boolean pageEqual = this.page == that.page;
        boolean sizeEqual = this.size == that.size;

        boolean sortEqual = this.sort == null ? that.sort == null : this.sort.equals(that.sort);

        return pageEqual && sizeEqual && sortEqual;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {

        int result = 17;

        result = 31 * result + page;
        result = 31 * result + size;
        result = 31 * result + (null == sort ? 0 : sort.hashCode());

        return result;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return String.format("Page request [number: %d, size %d, sort: %s]", page, size,
                sort == null ? null : sort.toString());
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }
}
