package com.pinyougou.sellergoods.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbBrandMapper;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.pojo.TbBrandExample;
import com.pinyougou.pojo.TbBrandExample.Criteria;
import com.pinyougou.sellergoods.service.BrandService;

import entity.PageResult;

@Service
public class BrandServiceImpl implements BrandService {
	@Autowired
	private TbBrandMapper brandMapper;

	@Override
	public List<TbBrand> findAll() {
		// TODO Auto-generated method stub
		return brandMapper.selectByExample(null);
	}

	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNum, pageSize);// 分页
		Page<TbBrand> page = (Page<TbBrand>) brandMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	@Override
	public void add(TbBrand brand) throws Exception {
		// TODO Auto-generated method stub
		brandMapper.insert(brand);
	}

	/**
	 * 修改
	 */
	@Override
	public void update(TbBrand brand) {
		brandMapper.updateByPrimaryKey(brand);
	}

	/**
	 * 根据ID获取实体
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public TbBrand findOne(Long id) {
		return brandMapper.selectByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Long[] ids) {
		for (Long id : ids) {
			brandMapper.deleteByPrimaryKey(id);
		}
	}
	@Override
	public PageResult findPage(TbBrand brand, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);		
		TbBrandExample example=new TbBrandExample();
		Criteria criteria = example.createCriteria();		
		if(brand!=null){
			if(brand.getName()!=null && brand.getName().length()>0){
				criteria.andNameLike("%"+brand.getName()+"%");
			}
			if(brand.getFirstChar()!=null && brand.getFirstChar().length()>0){
				criteria.andFirstCharEqualTo(brand.getFirstChar());
			}		
		}		
		Page<TbBrand> page= (Page<TbBrand>)brandMapper.selectByExample(example);	
		return new PageResult(page.getTotal(), page.getResult());
	}

	@Override
	public List<Map> selectOptionList() {
		// TODO Auto-generated method stub
		return brandMapper.selectOptionList();
	}

}
