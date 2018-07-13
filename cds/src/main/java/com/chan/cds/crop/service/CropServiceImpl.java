package com.chan.cds.crop.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chan.cds.common.entity.Crop;
import com.chan.cds.common.entity.CropExample;
import com.chan.cds.common.mapper.CropMapper;
import com.chan.cds.crop.dao.CropDao;
import com.chan.cds.crop.dto.CropModificationDto;
import com.chan.cds.crop.dto.CropRegisterationDto;

@Service
@Transactional
public class CropServiceImpl implements CropService {

	@Autowired
	private CropDao cropDao;

	@Autowired
	private CropMapper cropMapper;

	@Override
	public void insertCrop(CropRegisterationDto cropRegisterationDto) {
		// TODO Auto-generated method stub

		Crop crop = new Crop();
		crop.setNameEn(cropRegisterationDto.getNameEn());
		crop.setNameMm(cropRegisterationDto.getNameMm());
		crop.setBioName(cropRegisterationDto.getBioName());
		crop.setDescription(cropRegisterationDto.getDescription());
		crop.setCreatedBy("Testing");
		crop.setCreatedDate(new Date());
		cropDao.insert(crop);
	}

	@Override
	public List<Crop> getAllCrop() {
		return cropDao.getAllCrop();
	}

	@Override
	public int deleteByPrimaryKey(Integer cropId) {
		return cropDao.deleteByPrimaryKey(cropId);
	}

	@Override
	public Crop getCropByPrimaryKey(Integer cropId) {

		Crop crop = cropMapper.selectByPrimaryKey(cropId);

		return crop;
	}

	@Override
	public void updateCrop(CropModificationDto cropModificationDto) {
		// TODO Auto-generated method stub
		Crop crop = new Crop();
		crop.setCropId(cropModificationDto.getCropId());
		crop.setNameEn(cropModificationDto.getNameEn());
		crop.setNameMm(cropModificationDto.getNameMm());
		crop.setBioName(cropModificationDto.getBioName());
		crop.setDescription(cropModificationDto.getDescription());
		crop.setUpdatedBy("Testing");
		crop.setUpdatedDate(new Date());
		
		/*CropExample cropExample = new CropExample();
		cropExample.createCriteria().andCropIdEqualTo(cropModificationDto.getCropId());
		
		cropMapper.updateByExample(crop, cropExample);*/
		
		cropMapper.updateByPrimaryKeySelective(crop);
	}

}
