package com.chan.cds.crop.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.chan.cds.common.entity.Crop;
import com.chan.cds.common.mapper.CropMapper;
import com.chan.cds.crop.mapper.CropLocalMapper;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class CropDaoImpl implements CropDao {

	@Autowired
	private CropMapper cropMapper;
	
	@Autowired
	private CropLocalMapper cropLocalMapper;
	
	@Override
	public List<Crop> getAllCrop() {
		return cropLocalMapper.getAllCrop();
	}

	@Override
	public int insert(Crop crop) {
		return cropMapper.insert(crop);
	}

	@Override
	public int deleteByPrimaryKey(Integer cropId) {
		return cropMapper.deleteByPrimaryKey(cropId);
	}

}
