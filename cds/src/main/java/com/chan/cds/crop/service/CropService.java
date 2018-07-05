package com.chan.cds.crop.service;

import java.util.List;

import com.chan.cds.common.entity.Crop;
import com.chan.cds.crop.dto.CropRegisterationDto;

public interface CropService {
	
	public void insertCrop(CropRegisterationDto cropRegisterationDto);

	public List<Crop> getAllCrop();
	
	public int deleteByPrimaryKey(Integer cropId);
	
	public Crop getCropByPrimaryKey(Integer cropId);
}
