package com.epam.hnyp.task1.subtask1;

/**
 * Represents abstract wind instrument
 * @author Oleksandr_Hnyp
 *
 */
public abstract class WindInstrument extends MusicInstrument {
	private static final String DEFAULT_MATERIAL = "bronze";
	
	private String material;
	
	/**
	 * Creates default wind instrument
	 */
	public WindInstrument() {
		this.material = DEFAULT_MATERIAL;
	}
	
	/**
	 * Creates wind instrument with params
	 * @param vendor
	 * @param year
	 * @param material
	 */
	public WindInstrument(String vendor, int year, String material) {
		super(vendor, year);
		this.material = material;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (super.equals(obj)) {
			if (obj instanceof WindInstrument) {
				WindInstrument wi = (WindInstrument)obj;
				if (wi.material.equals(this.material)) {
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return super.hashCode() + material.hashCode();
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(super.toString()).append(", wind instrument, ").append("material ").append(material);
		return str.toString(); 
	}
}
