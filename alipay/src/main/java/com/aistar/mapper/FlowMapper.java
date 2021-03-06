package com.aistar.mapper;

import com.aistar.pojo.Flow;
import com.aistar.pojo.FlowExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FlowMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table flow
     *
     * @mbggenerated Mon Mar 16 21:44:38 CST 2020
     */
    int countByExample(FlowExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table flow
     *
     * @mbggenerated Mon Mar 16 21:44:38 CST 2020
     */
    int deleteByExample(FlowExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table flow
     *
     * @mbggenerated Mon Mar 16 21:44:38 CST 2020
     */
    int deleteByPrimaryKey(String flowId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table flow
     *
     * @mbggenerated Mon Mar 16 21:44:38 CST 2020
     */
    int insert(Flow record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table flow
     *
     * @mbggenerated Mon Mar 16 21:44:38 CST 2020
     */
    int insertSelective(Flow record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table flow
     *
     * @mbggenerated Mon Mar 16 21:44:38 CST 2020
     */
    List<Flow> selectByExample(FlowExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table flow
     *
     * @mbggenerated Mon Mar 16 21:44:38 CST 2020
     */
    Flow selectByPrimaryKey(String flowId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table flow
     *
     * @mbggenerated Mon Mar 16 21:44:38 CST 2020
     */
    int updateByExampleSelective(@Param("record") Flow record, @Param("example") FlowExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table flow
     *
     * @mbggenerated Mon Mar 16 21:44:38 CST 2020
     */
    int updateByExample(@Param("record") Flow record, @Param("example") FlowExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table flow
     *
     * @mbggenerated Mon Mar 16 21:44:38 CST 2020
     */
    int updateByPrimaryKeySelective(Flow record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table flow
     *
     * @mbggenerated Mon Mar 16 21:44:38 CST 2020
     */
    int updateByPrimaryKey(Flow record);
}