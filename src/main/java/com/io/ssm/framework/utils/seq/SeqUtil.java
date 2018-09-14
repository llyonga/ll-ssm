package com.io.ssm.framework.utils.seq;

import com.io.ssm.framework.enumtype.SeqEnum;
import com.io.ssm.framework.exception.SSMException;
import com.io.ssm.framework.utils.SpringContextUtil;
import com.io.ssm.framework.utils.StringUtils;
import com.io.ssm.framework.utils.UserUtil;
import com.io.ssm.module.domain.seq.CmSeq;
import com.io.ssm.module.domain.seq.CmSeqMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @description: 序列工具类
 * @author: llyong
 * @date: 2018/9/14
 * @time: 12:27
 * @version: 1.0
 */
public class SeqUtil {

    private static Logger LOGGER = LoggerFactory.getLogger(SeqUtil.class);

    public synchronized static String getSeq(SeqEnum seqEnum) throws Exception {
        String seq = "";
        CmSeqMapper seqMapper = SpringContextUtil.getBean("cmSeqMapper");
        CmSeq cmSeq = seqMapper.selectByPrimaryKey(seqEnum.getCode());
        if (StringUtils.isEmpty(cmSeq.getCurSeqNo())) {
            seq = String.valueOf(cmSeq.getMinValue()+1);
            cmSeq.setCurSeqNo(Long.valueOf(seq));
        } else {
            if (cmSeq.getMaxValue().longValue() < cmSeq.getCurSeqNo().longValue()) {
                throw new SSMException("序列号seq超出指定范围");
            }
            seq = String.valueOf(cmSeq.getCurSeqNo());
            cmSeq.setCurSeqNo(cmSeq.getCurSeqNo()+1);
        }
        seq = StringUtils.isEmpty(cmSeq.getSeqHead()) ? seq : cmSeq.getSeqHead()+seq;
        cmSeq.setModifier(UserUtil.getUser().getUsername());
        cmSeq.setModifyTime(new Date());
        seqMapper.updateByPrimaryKeySelective(cmSeq);
        return seq;
    }
}
