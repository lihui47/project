package com.woniu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniu.domin.Identifity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.woniu.vo.IdentifityVo;
import com.woniu.vo.PageVO;
import com.woniu.vo.ToIdentifityVo;

import java.util.List;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author team
 * @since 2020-12-22
 */
public interface IdentifityService extends IService<IdentifityVo> {
    //查询所有
    //Page<IdentifityVo> queryAll(PageVO pageVO);
    Page<IdentifityVo> getPageAll(Page<IdentifityVo> identifityVoPage);

    //条件查询
    Page<IdentifityVo> queryConditional(IdentifityVo identifityVo);

    //插入鉴定，去鉴定
    int insertIdentify(IdentifityVo identifityVo);

    //根据ID查询鉴定信息
    List<IdentifityVo> getIdentifityVo(IdentifityVo identifityVo);

    //修改鉴定信息
    int updateIdentifity(Identifity identifity);
}
