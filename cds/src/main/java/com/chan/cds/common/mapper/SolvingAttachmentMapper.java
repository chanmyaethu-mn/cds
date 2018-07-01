package com.chan.cds.common.mapper;

import com.chan.cds.common.entity.SolvingAttachment;
import com.chan.cds.common.entity.SolvingAttachmentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface SolvingAttachmentMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table solving_attachment
     *
     * @mbggenerated
     */
    int countByExample(SolvingAttachmentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table solving_attachment
     *
     * @mbggenerated
     */
    int deleteByExample(SolvingAttachmentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table solving_attachment
     *
     * @mbggenerated
     */
    int insert(SolvingAttachment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table solving_attachment
     *
     * @mbggenerated
     */
    int insertSelective(SolvingAttachment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table solving_attachment
     *
     * @mbggenerated
     */
    List<SolvingAttachment> selectByExampleWithRowbounds(SolvingAttachmentExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table solving_attachment
     *
     * @mbggenerated
     */
    List<SolvingAttachment> selectByExample(SolvingAttachmentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table solving_attachment
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") SolvingAttachment record, @Param("example") SolvingAttachmentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table solving_attachment
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") SolvingAttachment record, @Param("example") SolvingAttachmentExample example);
}