package com.chan.cds.crop.dao;

import java.util.List;

import com.chan.cds.common.entity.Crop;

public interface CropDao {
	
	public int insert(Crop crop);
	
	public int deleteByPrimaryKey(Integer cropId);
	
	public List<Crop> getAllCrop();
}
