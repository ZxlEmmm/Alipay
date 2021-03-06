package com.aistar.pojo;

import java.io.Serializable;

public class Customer implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column customer.customer_id
     *
     * @mbggenerated Mon Mar 16 18:29:59 CST 2020
     */
    private Integer customerId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column customer.customer_name
     *
     * @mbggenerated Mon Mar 16 18:29:59 CST 2020
     */
    private String customerName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table customer
     *
     * @mbggenerated Mon Mar 16 18:29:59 CST 2020
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column customer.customer_id
     *
     * @return the value of customer.customer_id
     *
     * @mbggenerated Mon Mar 16 18:29:59 CST 2020
     */
    public Integer getCustomerId() {
        return customerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column customer.customer_id
     *
     * @param customerId the value for customer.customer_id
     *
     * @mbggenerated Mon Mar 16 18:29:59 CST 2020
     */
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column customer.customer_name
     *
     * @return the value of customer.customer_name
     *
     * @mbggenerated Mon Mar 16 18:29:59 CST 2020
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column customer.customer_name
     *
     * @param customerName the value for customer.customer_name
     *
     * @mbggenerated Mon Mar 16 18:29:59 CST 2020
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table customer
     *
     * @mbggenerated Mon Mar 16 18:29:59 CST 2020
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", customerId=").append(customerId);
        sb.append(", customerName=").append(customerName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}