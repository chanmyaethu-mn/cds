package com.chan.cds.crop.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

public class CropModificationDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "Please provide name as english.")
	private String nameEn;

	private String nameMm;

	private String bioName;

	private String description;

	/**
	 * @return the nameEn
	 */
	public String getNameEn() {
		return nameEn;
	}

	/**
	 * @param nameEn the nameEn to set
	 */
	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	/**
	 * @return the nameMm
	 */
	public String getNameMm() {
		return nameMm;
	}

	/**
	 * @param nameMm the nameMm to set
	 */
	public void setNameMm(String nameMm) {
		this.nameMm = nameMm;
	}

	/**
	 * @return the bioName
	 */
	public String getBioName() {
		return bioName;
	}

	/**
	 * @param bioName the bioName to set
	 */
	public void setBioName(String bioName) {
		this.bioName = bioName;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CropRegisterationDto [nameEn=" + nameEn + ", nameMm=" + nameMm + ", bioName=" + bioName
				+ ", description=" + description + "]";
	}

}
